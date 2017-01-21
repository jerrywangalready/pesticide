drop table if exists demo;

/*==============================================================*/
/* Table: demo                                                  */
/*==============================================================*/
create table demo
(
   uuid                 varchar(32) not null comment '主键',
   code                 varchar(50) comment '编码',
   name                 varchar(50) comment '名称',
   type                 varchar(50) comment '类型',
   isenable             varchar(2) comment '启用状态',
   primary key (uuid)
);

alter table demo comment 'demo';
