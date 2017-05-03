drop table if exists t_record;

/*==============================================================*/
/* Table: t_record                                              */
/*==============================================================*/
create table t_record
(
  uuid                 varchar(32) not null comment '主键',
  business_code        varchar(32) comment '业务编码',
  operate_time         date comment '操作时间',
  operator             varchar(32) comment '操作人',
  operate_detail       varchar(200) comment '操作详情',
  is_enable            varchar(2) comment '是否有效',
  REMARK               varchar(1500) comment '备注',
  primary key (uuid)
);

alter table t_record comment '业务记录表';
