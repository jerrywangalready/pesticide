/*==============================================================*/
/* Table: s_properties                                          */
/*==============================================================*/
create table s_properties
(
  p_key                varchar(100) not null comment 'key',
  p_value              varchar(400) comment 'value',
  primary key (p_key)
);

alter table s_properties comment '配置信息表';