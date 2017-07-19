INSERT INTO pesticide.t_code_list (uuid, code_type, code_name, code_key, code_value, order_code, isenable) VALUES ('issueType1', 'issueType', '任务类型', 'T', 'Task', '1', '1');
INSERT INTO pesticide.t_code_list (uuid, code_type, code_name, code_key, code_value, order_code, isenable) VALUES ('issueType2', 'issueType', '任务类型', 'B', 'Bug', '2', '1');

drop table if exists t_file;

/*==============================================================*/
/* Table: t_file                                                */
/*==============================================================*/
create table t_file
(
  uuid                 varchar(32) not null comment '主键',
  business_id          varchar(32) comment '业务主键',
  issue_type           varchar(32) comment '任务类型',
  file_name            varchar(200) comment '文件名称',
  file_type            varchar(300) comment '文件类型',
  file_size            varchar(13) comment '文件大小',
  create_user          varchar(32) comment '创建人员',
  create_time          datetime comment '创建时间',
  isenable             varchar(2) comment '是否在用',
  primary key (uuid)
);

alter table t_file comment '附件表';
