drop table if exists t_record;

/*==============================================================*/
/* Table: t_record                                              */
/*==============================================================*/
create table t_record
(
   uuid                 varchar(32) not null,
   business_code        varchar(32),
   operate_time         date,
   operator             varchar(32),
   operate_detail       varchar(200),
   is_enable            varchar(2),
   primary key (uuid)
);

alter table t_record comment '业务记录表';

drop table if exists t_task;

/*==============================================================*/
/* Table: t_task                                                */
/*==============================================================*/
create table t_task
(
   uuid                 varchar(32) not null comment '主键',
   task_code            varchar(32) comment '任务编码',
   object_code          varchar(32) comment '项目编码',
   model_code           varchar(32) comment '模块编码',
   title                varchar(100) comment '标题',
   description          varchar(4000) comment '描述',
   principal            varchar(32) comment '负责人',
   parent_code          varchar(32) comment '父级编码',
   version_code         varchar(32) comment '所属版本',
   priority             varchar(32) comment '优先级',
   working_day          varchar(32) comment '工时',
   deadline             date comment '截止时间',
   state                varchar(2) comment '状态',
   is_read              varchar(2) comment '是否已读',
   is_enable            varchar(2) comment '是否启用',
   create_time          date comment '创建时间',
   primary key (uuid)
);

alter table t_task comment '任务';

drop table if exists t_bug;

/*==============================================================*/
/* Table: t_bug                                                 */
/*==============================================================*/
create table t_bug
(
   uuid                 varchar(32) not null comment '主键',
   bug_code             varchar(32) comment 'bug编码',
   object_code          varchar(32) comment '项目编码',
   model_code           varchar(32) comment '模块编码',
   title                varchar(100) comment '标题',
   description          varchar(4000) comment '描述',
   principal            varchar(32) comment '负责人',
   parent_code          varchar(32) comment '父级编码',
   version_code         varchar(32) comment '所属版本',
   priority             varchar(32) comment '优先级',
   bug_level            varchar(32) comment '严重级别',
   result               varchar(32) comment '解决结果',
   deadline             date comment '截止时间',
   state                varchar(2) comment '状态',
   is_read              varchar(2) comment '是否已读',
   is_enable            varchar(2) comment '是否启用',
   create_time          date comment '创建时间',
   primary key (uuid)
);

alter table t_bug comment 'bug';

