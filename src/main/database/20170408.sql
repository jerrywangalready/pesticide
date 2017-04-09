CREATE OR REPLACE VIEW V_ISSUE AS
  SELECT uuid,
         task_code CODE,
         model_code,
         title,
         'T' ISSUE_TYPE,
         principal,
         state,
         object_code,
         version_code,
         priority,
         create_time
    FROM t_task
  UNION ALL
  SELECT uuid,
         bug_code CODE,
         model_code,
         title,
         'B' ISSUE_TYPE,
         principal,
         state,
         object_code,
         version_code,
         priority,
         create_time
    FROM t_bug