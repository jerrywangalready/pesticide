ALTER TABLE pesticide.t_publish CHANGE businessId business_id VARCHAR(32) COMMENT '业务主键';
ALTER TABLE pesticide.t_record CHANGE business_code business_id VARCHAR(32) COMMENT '业务编码';