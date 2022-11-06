drop table if exists SCHEDULES CASCADE;
drop table if exists TASK_TYPES CASCADE;
drop table if exists TASKS CASCADE;
drop table if exists TASKS_INSTANCES CASCADE;

drop sequence if exists s_SCHEDULES;
drop sequence if exists s_TASKS;
drop sequence if exists s_TASKS_INSTANCES;
create sequence s_SCHEDULES start with 1 increment by 50;
create sequence s_TASKS start with 1 increment by 50;
create sequence s_TASKS_INSTANCES start with 1 increment by 50;
create table SCHEDULES (id bigint not null, name varchar(255), seconds bigint, primary key (id));
create table TASK_TYPES (id bigint not null, name varchar(255), primary key (id));
create table TASKS (id bigint not null, code varchar(255), name varchar(255), next_run timestamp, schedule_id bigint, task_type_id bigint, primary key (id));
create table TASKS_INSTANCES (id bigint not null, code varchar(255), name varchar(255), task_id bigint, task_type_id bigint,  scheduled_time timestamp, started_time timestamp,
queued_time timestamp, finished_time timestamp, status bigint, result text, primary key (id));
alter table TASKS add constraint FK_TASKS_SCHEDULES foreign key (schedule_id) references SCHEDULES;
alter table TASKS add constraint FK_TASKS_TASKS_TYPES foreign key (task_type_id) references TASK_TYPES;
alter table TASKS_INSTANCES add constraint FK_TASK_INSTANCES_TASKS foreign key (task_id) references TASKS;
commit;