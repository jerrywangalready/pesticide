drop table if exists t_publish;

/*==============================================================*/
/* Table: t_publish                                             */
/*==============================================================*/
create table t_publish
(
   uuid                 varchar(32) not null comment '主键',
   businessId           varchar(32) comment '业务主键',
   model_code           varchar(32) comment '送测模块编码',
   model_name           varchar(32) comment '送测模块名称',
   push_time            date comment '送测时间',
   push_user            varchar(32) comment '送测人员',
   version              varchar(32) comment '版本',
   state                varchar(2) comment '发布状态',
   publish_time         date comment '发布时间',
   publish_version      varchar(32) comment '发布版本号',
   primary key (uuid)
);

alter table t_publish comment '送测模块';
