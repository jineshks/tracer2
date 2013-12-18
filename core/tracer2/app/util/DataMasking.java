
/**
 *
 */
package util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

//import play.Logger;
import scala.util.Random;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Manzarul.Haque
 *         this api perform data encryption
 *         and decryption  task.
 */
public class DataMasking {
    private static final String className = DataMasking.class.getName();

   private static final String ALGORITHM = "AES";
    private static final int ITERATIONS = 2;
    private static final byte[] keyValue =
            new byte[]{'T', 'R','a','C','e','R','2','i','n','T','e','R','n','a','l','p'};

    /**
     * this method is used to decrypt email first four character is dummy
     * and before @ three character is dummy and before @ real email is reverse
     * example my real email is manzarul07@gmail.com so it comes as  test70luraznamqwa@gmail.com
     * dummy data is  test , qwa and  70luraznam (reverse)
     *
     * @param email
     * @return
     */
    public static String decryptEmail(String email) {
    	TrackLogger.debug("getting email as=="+ email, className);
        String tempEmail[] = email.split("[@]");
        String userName = tempEmail[0].substring(4, tempEmail[0].length() - 3);
        StringBuffer stringBuffer = new StringBuffer(userName);
        userName = stringBuffer.reverse().toString();
        TrackLogger.debug("email is=====>" + userName + "@" + tempEmail[1] ,className);
        return userName + "@" + tempEmail[1];
    }

    /**
     * this method is used to decrypt password
     *
     * @param password
     * @return
     */
    public static String decryptPassword(String password) {
    	TrackLogger.debug("password befoer decrypt is============>" + password ,className);
        String pass = new StringBuffer(password.substring(3, password.length() - 1)).reverse().toString();
        TrackLogger.debug("password is============>" + pass ,className);
        return pass;
    }

    /**
     * this method is used to decrypt user phone number
     * phone number encoding..
     * split the 10 numbers into 5 groups of 2 numbers each, Eg 1135887192 into 11 35 88 71 92
     * after that we get original number like this  (start position from 0) 2 4 1 0 3
     * so original number is  8892351171
     *
     * @param phone
     * @return
     */
    public static String decryptPhone(String phone) {
        String arr[] = new String[5];
        TrackLogger.debug("getting phone number==="+phone, className);
        int j = 0;
        for (int i = 0; i < phone.length(); i++) {
            arr[j++] = phone.substring(i, ++i + 1);
        }
        String phoneNo = arr[2] + arr[4] + arr[1] + arr[0] + arr[3];
        return phoneNo;
    }

    /**
     * split the 10 numbers into 5 groups of 2 numbers each, Eg 8892351171 into 88 92 35 11 71
     * change the positions of groups to 3 2 0 4 1 so the number becomes
     * 1135887192 send this   number to back end.
     *
     * @param phone //1135887192
     * @return
     */
    public static String encryptPhone(String phone) {
        String arr[] = new String[5];
        String phoneNo = "";
        try {
        int j = 0;
        for (int i = 0; i < phone.length(); i++) {
            arr[j++] = phone.substring(i, ++i + 1);
        }
         phoneNo = arr[3] + arr[2] + arr[0] + arr[4] + arr[1];
        } catch(Exception e){
        	TrackLogger.error(e.getMessage(), className);
        }
        return phoneNo;
    }
   /**
    * this method is used to encrypt email
    * @param email
    * @return String
    */
    public static String encryptEmail(String email) {
        String tempEmail[] = email.split("@");
        String reversePart = new StringBuffer(tempEmail[0]).reverse().toString();
        char[] alphNum = "0123abcdefghiJ456789KLZOPABMNmnQRSTtUVvusrqTasd01pwe".toCharArray();
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            builder.append(alphNum[random.nextInt(alphNum.length)]);
        }
        reversePart = builder.toString() + reversePart;
        builder = null;
        builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            builder.append(alphNum[random.nextInt(alphNum.length)]);
        }
        reversePart = reversePart + builder.toString();
        email = reversePart + "@" + tempEmail[1];
        return email;
    }

    /**
     * this method is used to decrypt visa or master card number
     * sending format is
     * Visa & MastreCard = make 4 groups and order is 2130
     *
     * @param cardNo
     * @return
     */
    public static String decryptVisaMasterCard(String cardNo) {
        String array[] = new String[4];
        int k = 0;
        for (int i = 0; i < cardNo.length(); i++) {
            array[k++] = cardNo.substring(i, i + 4);
            i = i + 3;
        }
        String decryptedCardNo = array[3] + array[1] + array[0] + array[2];
        return decryptedCardNo;
    }

    /**
     * this method is used to decrypt card which is encrypted by mobile
     * American Express - make 3 Groups(15/5) and order is 120
     *
     * @param cardNo
     * @return
     */
    public static String decryptAmericanExp(String cardNo) {
        String array[] = new String[3];
        int k = 0;
        for (int i = 0; i < cardNo.length(); i++) {
            array[k++] = cardNo.substring(i, i + 5);
            i = i + 4;
        }
        String decryptedCardNo = array[2] + array[0] + array[1];
        return decryptedCardNo;

    }
     
    /**
     * this method is used to encrypt  the password.
     * @param value String password
     * @param salt 
     * @return encrypted password.
     * @throws Exception
     */
    public static String encrypt(String value, String salt) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);

        String valueToEnc = null;
        String eValue = value;
        for (int i = 0; i < ITERATIONS; i++) {
            valueToEnc = salt + eValue;
            byte[] encValue = c.doFinal(valueToEnc.getBytes());
            eValue = new BASE64Encoder().encode(encValue);
        }
        return eValue;
    }
    
    /**
     * this method is used to decrypt password. 
     * @param value encrypted password.
     * @param salt
     * @return decrypted password.
     * @throws Exception
     */
    public static String decrypt(String value, String salt) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);

        String dValue = null;
        String valueToDecrypt = value.trim();
        for (int i = 0; i < ITERATIONS; i++) {
            byte[] decordedValue = new BASE64Decoder().decodeBuffer(valueToDecrypt);
            byte[] decValue = c.doFinal(decordedValue);
            dValue = new String(decValue).substring(salt.length());
            valueToDecrypt = dValue;
        }
        return dValue;
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }

    public static void main(String asa[]) throws Exception {
     System.out.println(decrypt("xLqko4HkGh10bCltSVw9Vc+Tc4AaVk6vpOfW15Dll1DFVDVzlWTDU+okzOTiIc8Wvwv7HYMNEQ21tAt+DMl69FGRG+LOnBvRSkd8r2HZfOM=", Constants.SALT));
    	
    //	System.out.println(encrypt("test", Constants.SALT));
    }


}
