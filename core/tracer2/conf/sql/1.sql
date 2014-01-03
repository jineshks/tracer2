# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table complexity (
  id                        bigint auto_increment not null,
  category                  varchar(255),
  constraint pk_complexity primary key (id))
;

create table mile_stone (
  id                        bigint auto_increment not null,
  mile_stone_name           varchar(255),
  mile_stone_status         varchar(255),
  created                   datetime,
  ended                     datetime,
  project_id                bigint,
  constraint pk_mile_stone primary key (id))
;

create table phase (
  id                        bigint auto_increment not null,
  phase_name                varchar(255),
  constraint pk_phase primary key (id))
;

create table privilege (
  id                        bigint auto_increment not null,
  role_privileges_id        bigint not null,
  privilege_name            varchar(255),
  constraint pk_privilege primary key (id))
;

create table project (
  id                        bigint auto_increment not null,
  project_name              varchar(255),
  description               varchar(255),
  created                   datetime,
  updated                   datetime,
  visibility_id             bigint,
  created_by_id             bigint,
  constraint pk_project primary key (id))
;

create table role (
  id                        bigint auto_increment not null,
  role_name                 varchar(255),
  constraint pk_role primary key (id))
;

create table role_privilege (
  id                        bigint auto_increment not null,
  role_id                   bigint,
  constraint pk_role_privilege primary key (id))
;

create table user_session (
  id                        bigint auto_increment not null,
  created                   datetime,
  updated                   datetime,
  session_id                varchar(255),
  user_id                   bigint,
  constraint pk_user_session primary key (id))
;

create table severity (
  id                        bigint auto_increment not null,
  category                  varchar(255),
  constraint pk_severity primary key (id))
;

create table ticket (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  description               varchar(255),
  created                   datetime,
  updated                   datetime,
  phase_id                  bigint,
  ticket_status             varchar(255),
  owner_id                  bigint,
  creater_id                bigint,
  mile_stone_id             bigint,
  severity_id               bigint,
  complexity_id             bigint,
  project_id                bigint,
  type_id                   bigint,
  estimated_hours           double,
  actul_hours               double,
  progress                  integer,
  constraint pk_ticket primary key (id))
;

create table types (
  id                        bigint auto_increment not null,
  ticket_type               varchar(255),
  constraint pk_types primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  email                     varchar(255),
  phone                     varchar(255),
  pwd                       varchar(255),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;

create table user_project (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  project_id                bigint,
  constraint pk_user_project primary key (id))
;

create table user_role (
  id                        bigint auto_increment not null,
  user_role_id              bigint,
  user_id                   bigint,
  constraint pk_user_role primary key (id))
;

create table visibility (
  id                        bigint auto_increment not null,
  visibility_type           varchar(255),
  constraint pk_visibility primary key (id))
;

create table comment (
  id                        integer auto_increment not null,
  comment_text              varchar(255),
  created                   datetime,
  user_id                   bigint,
  ticket_id                 bigint,
  constraint pk_comment primary key (id))
;

alter table mile_stone add constraint fk_mile_stone_project_1 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_mile_stone_project_1 on mile_stone (project_id);
alter table privilege add constraint fk_privilege_role_privilege_2 foreign key (role_privileges_id) references role_privilege (id) on delete restrict on update restrict;
create index ix_privilege_role_privilege_2 on privilege (role_privileges_id);
alter table project add constraint fk_project_visibility_3 foreign key (visibility_id) references visibility (id) on delete restrict on update restrict;
create index ix_project_visibility_3 on project (visibility_id);
alter table project add constraint fk_project_createdBy_4 foreign key (created_by_id) references user (id) on delete restrict on update restrict;
create index ix_project_createdBy_4 on project (created_by_id);
alter table role_privilege add constraint fk_role_privilege_role_5 foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_role_privilege_role_5 on role_privilege (role_id);
alter table user_session add constraint fk_user_session_user_6 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_session_user_6 on user_session (user_id);
alter table ticket add constraint fk_ticket_phase_7 foreign key (phase_id) references phase (id) on delete restrict on update restrict;
create index ix_ticket_phase_7 on ticket (phase_id);
alter table ticket add constraint fk_ticket_owner_8 foreign key (owner_id) references user (id) on delete restrict on update restrict;
create index ix_ticket_owner_8 on ticket (owner_id);
alter table ticket add constraint fk_ticket_creater_9 foreign key (creater_id) references user (id) on delete restrict on update restrict;
create index ix_ticket_creater_9 on ticket (creater_id);
alter table ticket add constraint fk_ticket_mileStone_10 foreign key (mile_stone_id) references mile_stone (id) on delete restrict on update restrict;
create index ix_ticket_mileStone_10 on ticket (mile_stone_id);
alter table ticket add constraint fk_ticket_severity_11 foreign key (severity_id) references severity (id) on delete restrict on update restrict;
create index ix_ticket_severity_11 on ticket (severity_id);
alter table ticket add constraint fk_ticket_complexity_12 foreign key (complexity_id) references complexity (id) on delete restrict on update restrict;
create index ix_ticket_complexity_12 on ticket (complexity_id);
alter table ticket add constraint fk_ticket_project_13 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_ticket_project_13 on ticket (project_id);
alter table ticket add constraint fk_ticket_type_14 foreign key (type_id) references types (id) on delete restrict on update restrict;
create index ix_ticket_type_14 on ticket (type_id);
alter table user_project add constraint fk_user_project_user_15 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_project_user_15 on user_project (user_id);
alter table user_project add constraint fk_user_project_project_16 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_user_project_project_16 on user_project (project_id);
alter table user_role add constraint fk_user_role_userRole_17 foreign key (user_role_id) references user_role (id) on delete restrict on update restrict;
create index ix_user_role_userRole_17 on user_role (user_role_id);
alter table user_role add constraint fk_user_role_user_18 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_role_user_18 on user_role (user_id);
alter table comment add constraint fk_comment_user_19 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_comment_user_19 on comment (user_id);
alter table comment add constraint fk_comment_ticket_20 foreign key (ticket_id) references ticket (id) on delete restrict on update restrict;
create index ix_comment_ticket_20 on comment (ticket_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table complexity;

drop table mile_stone;

drop table phase;

drop table privilege;

drop table project;

drop table role;

drop table role_privilege;

drop table user_session;

drop table severity;

drop table ticket;

drop table types;

drop table user;

drop table user_project;

drop table user_role;

drop table visibility;

drop table comment;

SET FOREIGN_KEY_CHECKS=1;

