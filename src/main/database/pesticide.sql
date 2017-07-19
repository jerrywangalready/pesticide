drop table if exists demo;

drop table if exists p_menu;

drop table if exists p_resource;

drop table if exists p_resource_role;

drop table if exists p_role;

drop table if exists p_role_user;

drop table if exists s_code_list;

drop table if exists s_model;

drop table if exists s_object;

drop table if exists s_object_users;

drop table if exists s_properties;

drop table if exists s_users;

drop table if exists s_verison;

drop table if exists t_bug;

drop table if exists t_code_list;

drop table if exists t_development;

drop table if exists t_file;

drop table if exists t_modification;

drop table if exists t_publish;

drop table if exists t_push;

drop table if exists t_record;

drop table if exists t_task;

drop table if exists t_testing;

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

/*==============================================================*/
/* Table: p_menu                                                */
/*==============================================================*/
create table p_menu
(
   uuid                 varchar(32) not null comment '主键',
   menu_id              varchar(50),
   menu_name            varchar(300),
   primary key (uuid)
);

alter table p_menu comment '菜单表';

/*==============================================================*/
/* Table: p_resource                                            */
/*==============================================================*/
create table p_resource
(
   uuid                 varchar(32) not null comment '主键',
   primary key (uuid)
);

alter table p_resource comment '资源表';

/*==============================================================*/
/* Table: p_resource_role                                       */
/*==============================================================*/
create table p_resource_role
(
   uuid                 varchar(32) not null comment '主键',
   primary key (uuid)
);

alter table p_resource_role comment '资源角色表';

/*==============================================================*/
/* Table: p_role                                                */
/*==============================================================*/
create table p_role
(
   uuid                 varchar(32) not null comment '主键',
   role_id              varchar(50) comment '角色编号',
   role_name            varchar(300) comment '角色',
   primary key (uuid)
);

alter table p_role comment '角色表';

/*==============================================================*/
/* Table: p_role_user                                           */
/*==============================================================*/
create table p_role_user
(
   uuid                 varchar(32) not null,
   role_id              varchar(50),
   username             varchar(50),
   object_code          varchar(32),
   primary key (uuid)
);

alter table p_role_user comment '人员角色关系表';

/*==============================================================*/
/* Table: s_code_list                                           */
/*==============================================================*/
create table s_code_list
(
   uuid                 varchar(32) not null comment '主键',
   code_type            varchar(32) comment '编码类型',
   code_name            varchar(32) comment '编码名称',
   code_key             varchar(32) comment '编码',
   code_value           varchar(100) comment '值',
   order_code           varchar(4) comment '派讯编码',
   isenable             varchar(2) comment '是否启用',
   primary key (uuid)
);

alter table s_code_list comment '系统码表';

/*==============================================================*/
/* Table: s_model                                               */
/*==============================================================*/
create table s_model
(
   uuid                 varchar(32) not null comment '主键',
   model_code           varchar(32) comment '模块编码',
   model_name           varchar(100) comment '模块名称',
   object_code          varchar(32) comment '项目编码',
   isenable             varchar(2) comment '是否启用',
   primary key (uuid)
);

alter table s_model comment '模块';

/*==============================================================*/
/* Table: s_object                                              */
/*==============================================================*/
create table s_object
(
   uuid                 varchar(32) not null comment '主键',
   object_code          varchar(32) comment '项目编号',
   object_name          varchar(100) comment '项目名称',
   isenable             varchar(2) comment '是否启用',
   create_time          datetime comment '创建时间',
   primary key (uuid)
);

alter table s_object comment '项目';

/*==============================================================*/
/* Table: s_object_users                                        */
/*==============================================================*/
create table s_object_users
(
   uuid                 varchar(32) not null comment '主键',
   object_code          varchar(32) comment '项目编号',
   username             varchar(50) comment '登录账号',
   role_code            varchar(2) comment '角色编号',
   create_time          datetime comment '创建时间',
   primary key (uuid)
);

alter table s_object_users comment '项目人员关系表';

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

/*==============================================================*/
/* Table: s_users                                               */
/*==============================================================*/
create table s_users
(
   uuid                 varchar(32) not null comment '主键',
   username             varchar(50) comment '登录账号',
   password             varchar(50) comment '密码',
   nickname             varchar(50) comment '昵称',
   isenable             varchar(2) comment '启用状态',
   create_time          datetime comment '创建时间',
   primary key (uuid)
);

alter table s_users comment '用户';

/*==============================================================*/
/* Table: s_verison                                             */
/*==============================================================*/
create table s_verison
(
   uuid                 varchar(32) not null comment '主键',
   version_code         varchar(32) comment '版本号',
   publish_date         date comment '上线时间',
   object_code          varchar(32) comment '项目编号',
   is_complete          varchar(2) comment '发布完成',
   primary key (uuid)
);

alter table s_verison comment '版本';

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
   deadline             datetime comment '截止时间',
   state                varchar(2) comment '状态',
   is_read              varchar(2) comment '是否已读',
   is_enable            varchar(2) comment '是否启用',
   create_time          datetime comment '创建时间',
   create_user          varchar(32) comment '创建人',
   update_time          datetime comment '修改时间',
   primary key (uuid)
);

alter table t_bug comment 'bug';

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
   order_code           varchar(4) comment '排序编码',
   isenable             varchar(2) comment '是否启用',
   primary key (uuid)
);

alter table t_code_list comment '项目码表';

/*==============================================================*/
/* Table: t_development                                         */
/*==============================================================*/
create table t_development
(
   uuid                 varchar(32) not null comment '主键',
   development_code     varchar(32) comment '开发任务编号',
   title                varchar(300) comment '主题',
   description          varchar(4000) comment '开发任务描述',
   develop_user         varchar(100) comment '开发人员',
   test_user            varchar(100) comment '测试人员',
   create_user          varchar(100) comment '创建人员',
   create_date          date comment '创建时间',
   finish_time          date comment '预计开发完成时间',
   version_code         varchar(32) comment '版本号',
   state                varchar(2) comment '状态',
   model_code           varchar(32) comment '模块编码',
   isread               varchar(2) comment '是否已查看',
   primary key (uuid)
);

alter table t_development comment '开发任务';

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

/*==============================================================*/
/* Table: t_modification                                        */
/*==============================================================*/
create table t_modification
(
   uuid                 varchar(32) not null comment '主键',
   modification_code    varchar(32) comment '修改编码',
   title                varchar(300) comment '主题',
   description          varchar(4000) comment '描述',
   modification_user    varchar(100) comment '修改人员',
   create_user          varchar(100) comment '创建人员',
   create_time          date comment '创建时间',
   testing_code         varchar(32) comment '测试任务编码',
   finish_time          date comment '预计完成时间',
   version_code         varchar(32) comment '版本号',
   state                varchar(2) comment '状态',
   model_code           varchar(32) comment '模块编码',
   isread               varchar(2) comment '是否已查看',
   primary key (uuid)
);

alter table t_modification comment '修改任务';

/*==============================================================*/
/* Table: t_publish                                             */
/*==============================================================*/
create table t_publish
(
   uuid                 varchar(32) not null comment '主键',
   business_id          varchar(32) comment '业务主键',
   model_code           varchar(32) comment '送测模块编码',
   model_name           varchar(32) comment '送测模块名称',
   push_time            datetime comment '送测时间',
   push_user            varchar(32) comment '送测人员',
   version              varchar(32) comment '版本',
   state                varchar(2) comment '发布状态',
   publish_time         datetime comment '发布时间',
   publish_version      varchar(32) comment '发布版本号',
   primary key (uuid)
);

alter table t_publish comment '送测模块';

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
   create_time          datetime comment '创建时间',
   push_state           varchar(2) comment '推送状态',
   push_version         varchar(32) comment '推送版本',
   push_user            varchar(32) comment '推送人员',
   push_time            datetime comment '推送时间',
   isenable             varchar(2) comment '是否有效',
   primary key (uuid)
);

alter table t_push comment '推送任务';

/*==============================================================*/
/* Table: t_record                                              */
/*==============================================================*/
create table t_record
(
   uuid                 varchar(32) not null comment '主键',
   business_id          varchar(32) comment '业务编码',
   operate_time         datetime comment '操作时间',
   operator             varchar(32) comment '操作人',
   operate_detail       varchar(200) comment '操作详情',
   is_enable            varchar(2) comment '是否有效',
   remark               varchar(1500) comment '备注',
   primary key (uuid)
);

alter table t_record comment '业务记录表';

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
   deadline             datetime comment '截止时间',
   state                varchar(2) comment '状态',
   is_read              varchar(2) comment '是否已读',
   is_enable            varchar(2) comment '是否启用',
   create_time          datetime comment '创建时间',
   create_user          varchar(32) comment '创建人',
   update_time          datetime comment '修改时间',
   primary key (uuid)
);

alter table t_task comment '任务';

/*==============================================================*/
/* Table: t_testing                                             */
/*==============================================================*/
create table t_testing
(
   uuid                 varchar(32) not null comment '主键',
   test_code            varchar(32) comment '测试编码',
   title                varchar(300) comment '主题',
   description          varchar(4000) comment '描述',
   test_user            varchar(100) comment '测试人员',
   create_user          varchar(100) comment '创建人员',
   create_time          date comment '创建时间',
   development_code     varchar(32) comment '开发任务编码',
   state                varchar(2) comment '状态',
   model_code           varchar(32) comment '模块编码',
   isread               varchar(2) comment '是否已查看',
   primary key (uuid)
);

alter table t_testing comment '测试任务';
