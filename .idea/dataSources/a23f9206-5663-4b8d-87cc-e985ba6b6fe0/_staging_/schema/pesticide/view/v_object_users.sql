CREATE OR REPLACE VIEW v_object_users AS
  SELECT
    SOU.username    AS USERNAME,
    SU.nickname     AS NICKNAME,
    SOU.object_code AS OBJECT_CODE
  FROM
    pesticide.s_object_users SOU LEFT JOIN pesticide.s_users SU ON ((SOU.username = SU.username));
