drop table if exists p_menu;

drop table if exists p_resource;

drop table if exists p_resource_role;

drop table if exists p_role;

drop table if exists s_code_list;

drop table if exists s_model;

drop table if exists s_object;

drop table if exists s_object_users;

drop table if exists s_users;

drop table if exists s_verison;

drop table if exists t_development;

drop table if exists t_modification;

drop table if exists t_testing;

/*==============================================================*/
/* Table: p_menu                                                */
/*==============================================================*/
create table p_menu;

alter table p_menu comment '菜单表';

/*==============================================================*/
/* Table: p_resource                                            */
/*==============================================================*/
create table p_resource;

alter table p_resource comment '资源表';

/*==============================================================*/
/* Table: p_resource_role                                       */
/*==============================================================*/
create table p_resource_role;

alter table p_resource_role comment '资源角色表';

/*==============================================================*/
/* Table: p_role                                                */
/*==============================================================*/
create table p_role;

alter table p_role comment '角色表';

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
   isenable             varchar(2) comment '是否启用',
   primary key (uuid)
);

alter table s_code_list comment '码表';

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
   create_time          date comment '创建时间',
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
   create_time          date comment '创建时间',
   primary key (uuid)
);

alter table s_object_users comment '项目人员关系表';

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
   create_time          date comment '创建时间',
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
   primary key (uuid)
);

alter table s_verison comment '版本';

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
   "read"               varchar(2) comment '是否已查看',
   primary key (uuid)
);

alter table t_development comment '开发任务';

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
   "read"               varchar(2) comment '是否已查看',
   primary key (uuid)
);

alter table t_modification comment '修改任务';

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
   "read"               varchar(2) comment '是否已查看',
   primary key (uuid)
);

alter table t_testing comment '测试任务';
