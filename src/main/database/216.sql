CREATE OR REPLACE VIEW v_object_users AS
  SELECT
    SOU.username    AS USERNAME,
    SU.nickname     AS NICKNAME,
    SOU.object_code AS OBJECT_CODE
  FROM
    pesticide.s_object_users SOU LEFT JOIN pesticide.s_users SU ON ((SOU.username = SU.username));
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