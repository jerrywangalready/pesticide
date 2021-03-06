create OR REPLACE view v_issue as
  SELECT
    `pesticide`.`t_task`.`uuid`         AS `uuid`,
    `pesticide`.`t_task`.`task_code`    AS `CODE`,
    `pesticide`.`t_task`.`model_code`   AS `model_code`,
    `pesticide`.`t_task`.`title`        AS `title`,
    'T'                                 AS `ISSUE_TYPE`,
    `pesticide`.`t_task`.`principal`    AS `principal`,
    `pesticide`.`t_task`.`state`        AS `state`,
    `pesticide`.`t_task`.`object_code`  AS `object_code`,
    `pesticide`.`t_task`.`version_code` AS `version_code`,
    `pesticide`.`t_task`.`priority`     AS `priority`,
    `pesticide`.`t_task`.`create_time`  AS `create_time`,
    `pesticide`.`t_task`.`create_user`  AS `create_user`,
    `pesticide`.`t_task`.`update_time`  AS `update_time`,
    ''                                  AS `bug_level`,
    `pesticide`.`t_task`.`is_read`      AS `is_read`,
    `pesticide`.`t_task`.`is_display`   AS `is_display`
  FROM `pesticide`.`t_task`
  UNION ALL SELECT
              `pesticide`.`t_bug`.`uuid`         AS `uuid`,
              `pesticide`.`t_bug`.`bug_code`     AS `CODE`,
              `pesticide`.`t_bug`.`model_code`   AS `model_code`,
              `pesticide`.`t_bug`.`title`        AS `title`,
              'B'                                AS `ISSUE_TYPE`,
              `pesticide`.`t_bug`.`principal`    AS `principal`,
              `pesticide`.`t_bug`.`state`        AS `state`,
              `pesticide`.`t_bug`.`object_code`  AS `object_code`,
              `pesticide`.`t_bug`.`version_code` AS `version_code`,
              `pesticide`.`t_bug`.`priority`     AS `priority`,
              `pesticide`.`t_bug`.`create_time`  AS `create_time`,
              `pesticide`.`t_bug`.`create_user`  AS `create_user`,
              `pesticide`.`t_bug`.`update_time`  AS `update_time`,
              `pesticide`.`t_bug`.`bug_level`    AS `bug_level`,
              `pesticide`.`t_bug`.`is_read`      AS `is_read`,
              `pesticide`.`t_bug`.`is_display`   AS `is_display`
            FROM `pesticide`.`t_bug`;

