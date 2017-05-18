ALTER TABLE pesticide.t_task ADD update_time DATETIME NULL;
ALTER TABLE pesticide.t_bug ADD update_time DATETIME NULL;
ALTER TABLE pesticide.t_task MODIFY update_time DATETIME COMMENT '修改时间';
ALTER TABLE pesticide.t_bug MODIFY update_time DATETIME COMMENT '修改时间';

CREATE OR REPLACE VIEW v_issue AS
  SELECT
    t_task.uuid         AS uuid,
    t_task.task_code    AS CODE,
    t_task.model_code   AS model_code,
    t_task.title        AS title,
    'T'                 AS ISSUE_TYPE,
    t_task.principal    AS principal,
    t_task.state        AS state,
    t_task.object_code  AS object_code,
    t_task.version_code AS version_code,
    t_task.priority     AS priority,
    t_task.create_time  AS create_time,
    t_task.create_user  AS create_user,
    t_task.update_time  AS update_time
  FROM t_task
  UNION ALL
  SELECT
    t_bug.uuid         AS uuid,
    t_bug.bug_code     AS CODE,
    t_bug.model_code   AS model_code,
    t_bug.title        AS title,
    'B'                AS ISSUE_TYPE,
    t_bug.principal    AS principal,
    t_bug.state        AS state,
    t_bug.object_code  AS object_code,
    t_bug.version_code AS version_code,
    t_bug.priority     AS priority,
    t_bug.create_time  AS create_time,
    t_bug.create_user  AS create_user,
    t_bug.update_time  AS update_time
  FROM t_bug;
