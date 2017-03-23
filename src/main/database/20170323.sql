ALTER TABLE pesticide.t_development ADD parent_code VARCHAR(32) NULL;
ALTER TABLE pesticide.t_development
  MODIFY COLUMN parent_code VARCHAR(32) COMMENT '父级编码' AFTER test_user;
ALTER TABLE pesticide.t_testing DROP development_code;
ALTER TABLE pesticide.t_testing DROP development_uuid;
ALTER TABLE pesticide.t_testing ADD parent_code VARCHAR(32) NULL;
ALTER TABLE pesticide.t_testing
  MODIFY COLUMN parent_code VARCHAR(32) COMMENT '父级编码' AFTER create_time;
ALTER TABLE pesticide.t_development ADD parent_type VARCHAR(2) NULL;
ALTER TABLE pesticide.t_development
  MODIFY COLUMN parent_code VARCHAR(32) COMMENT '父级编码' AFTER uuid,
  MODIFY COLUMN parent_type VARCHAR(2) COMMENT '父级类型' AFTER parent_code;
ALTER TABLE pesticide.t_testing ADD parent_type VARCHAR(2) NULL;
ALTER TABLE pesticide.t_testing
  MODIFY COLUMN parent_code VARCHAR(32) COMMENT '父级编码' AFTER uuid,
  MODIFY COLUMN parent_type VARCHAR(2) COMMENT '父级类型' AFTER parent_code;
ALTER TABLE pesticide.t_modification DROP testing_code;
ALTER TABLE pesticide.t_modification DROP development_uuid;
ALTER TABLE pesticide.t_modification DROP development_code;
ALTER TABLE pesticide.t_modification DROP testing_uuid;
ALTER TABLE pesticide.t_modification ADD parent_type VARCHAR(2) NULL;
ALTER TABLE pesticide.t_modification ADD parent_code VARCHAR(32) NULL;
ALTER TABLE pesticide.t_modification
  MODIFY COLUMN parent_code VARCHAR(32) COMMENT '父级编码' AFTER uuid,
  MODIFY COLUMN parent_type VARCHAR(2) COMMENT '父级类型' AFTER parent_code;
ALTER TABLE pesticide.t_testing DROP test_code;