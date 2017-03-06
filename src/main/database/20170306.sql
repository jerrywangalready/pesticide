ALTER TABLE pesticide.t_modification ADD development_uuid VARCHAR(32) NULL;
ALTER TABLE pesticide.t_modification MODIFY development_uuid VARCHAR(32) COMMENT '开发任务uuid';
ALTER TABLE pesticide.t_modification ADD development_code VARCHAR(32) NULL;
ALTER TABLE pesticide.t_modification ADD testing_uuid VARCHAR(32) NULL;
ALTER TABLE pesticide.t_modification MODIFY testing_uuid VARCHAR(32) COMMENT '测试任务uuid';
ALTER TABLE pesticide.t_modification MODIFY development_code VARCHAR(32) COMMENT '开发任务编码';
ALTER TABLE pesticide.t_modification
  MODIFY COLUMN development_uuid VARCHAR(32) COMMENT '开发任务uuid' AFTER create_time,
  MODIFY COLUMN development_code VARCHAR(32) COMMENT '开发任务编码' AFTER development_uuid,
  MODIFY COLUMN testing_uuid VARCHAR(32) COMMENT '测试任务uuid' AFTER development_code;
ALTER TABLE pesticide.t_testing ADD development_uuid VARCHAR(32) NULL;
ALTER TABLE pesticide.t_testing MODIFY development_uuid VARCHAR(32) COMMENT '开发任务uuid';
ALTER TABLE pesticide.t_testing
  MODIFY COLUMN development_uuid VARCHAR(32) COMMENT '开发任务uuid' AFTER create_time;