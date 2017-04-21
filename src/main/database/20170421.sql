
/*==============================================================*/
/* Table: t_push                                                */
/*==============================================================*/
create table t_push
(
   uuid                 varchar(32) not null comment '主键',
   business_id          varchar(32) comment '业务主键',
   issue_type           varchar(32) comment '任务类型',
   model_code           varchar(32) comment '模块编码',
   description          varchar(400) comment '描述',
   create_user          varchar(32) comment '创建人员',
   create_time          date comment '创建时间',
   push_state           varchar(2) comment '推送状态',
   push_version         varchar(32) comment '推送版本',
   push_user            varchar(32) comment '推送人员',
   push_time            date comment '推送时间',
   isenable             varchar(2) comment '是否有效',
   primary key (uuid)
);

alter table t_push comment '推送任务';


/*==============================================================*/
/* Table: t_file                                                */
/*==============================================================*/
create table t_file
(
   uuid                 varchar(32) not null comment '主键',
   business_id          varchar(32) comment '业务主键',
   issue_type           varchar(32) comment '任务类型',
   file_name            varchar(200) comment '文件名称',
   file_type            varchar(32) comment '文件类型',
   create_user          varchar(32) comment '创建人员',
   create_time          date comment '创建时间',
   isenable             varchar(2) comment '是否在用',
   primary key (uuid)
);

alter table t_file comment '附件表';
