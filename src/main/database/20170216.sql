ALTER TABLE pesticide.t_development ADD is_urgency VARCHAR(2) NULL;
ALTER TABLE pesticide.t_development MODIFY is_urgency VARCHAR(2) COMMENT '是否紧急';
ALTER TABLE pesticide.t_modification ADD bug_level VARCHAR(2) NULL;
ALTER TABLE pesticide.t_modification MODIFY bug_level VARCHAR(2) COMMENT 'bug类型';
ALTER TABLE pesticide.t_modification ADD is_urgency VARCHAR(2) NULL;
ALTER TABLE pesticide.t_modification MODIFY is_urgency VARCHAR(2) COMMENT '是否紧急';
ALTER TABLE pesticide.t_testing ADD is_urgency VARCHAR(2) NULL;
ALTER TABLE pesticide.t_testing MODIFY is_urgency VARCHAR(2) COMMENT '是否紧急';