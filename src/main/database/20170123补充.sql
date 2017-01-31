ALTER TABLE s_version ADD object_code VARCHAR(32) NULL;
ALTER TABLE s_version MODIFY object_code VARCHAR(32) COMMENT '项目编号';