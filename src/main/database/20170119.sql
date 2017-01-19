drop table if exists t_code_list;

/*==============================================================*/
/* Table: t_code_list                                           */
/*==============================================================*/
create table t_code_list
(
   uuid                 varchar(32) not null comment '主键',
   code_type            varchar(32) comment '编码类型',
   code_name            varchar(32) comment '编码名称',
   code_key             varchar(32) comment '编码',
   code_value           varchar(100) comment '值',
   isenable             varchar(2) comment '是否启用',
   primary key (uuid)
);

alter table t_code_list comment '项目码表';
