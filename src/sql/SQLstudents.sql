-- 1
SELECT s.first_name, s.last_name 
  FROM student AS s, student_result AS sr
  WHERE s.id = sr.student_id
  AND sr.result > 2
ORDER BY sr.result DESC;


-- 2 все сдавшие хотя бы один экзамен на 4 или 5
SELECT COUNT(DISTINCT s.id) FROM student AS s, student_result AS sr
  WHERE s.id = sr.student_id
  and sr.result > 3;

-- 2 все сдавшие экзамены только на 4 или 5
SELECT COUNT(DISTINCT s.id)
  FROM student AS s 
  JOIN student_result AS sr ON s.id = sr.student_id
  WHERE 3 < ALL(
  SELECT result FROM student_result
  WHERE student_result.student_id = s.id
  )
  ORDER BY sr.result DESC;



-- 3 Правильно выполнить запрос мешают null в student_result  и отличия в оценках у студента с id 2
SELECT *
  from student_result AS sr 
  WHERE NOT EXISTS (
  SELECT * FROM exam_result AS er
  WHERE er.result = sr.result  
  AND sr.student_id = er.student_id
  );

 
-- 4
SELECT avg(sr.result) FROM student_result sr 
  JOIN training_course AS tc 
    ON sr.training_course_id = tc.id
  WHERE tc.name = 'RDBMS' 
ORDER BY tc.id;


-- 5 
  /*Таблицы student_result и exam_result неправильно заполнены. В одной записи в колонке training_course_id = 4 не может соответствовать exam_id = 3, 
  т.к. согласно таблицу exam training_course_id = 4 соответствует id = 4, 3 -> 3 и т.д. Выборку нужно было бы делать 
  по имени курса Math (наиболее соответствующее имя для Теории графов), но т.к. вместо training_course_id = 3 в таблице student_result все training_course_id = 4
  поэтому делаю выборку по Athletic, что соответствует training_course_id = 4.
  */
      -- 5.1
SELECT s.first_name, s.last_name FROM student AS s 
  WHERE NOT EXISTS(
  SELECT sr.student_id FROM student_result sr 
  JOIN training_course AS tc 
    on sr.training_course_id = tc.id 
  WHERE tc.name = 'Athletic'
    and s.id = sr.student_id
  );
    -- 5.2
SELECT s.first_name, s.last_name FROM student AS s 
  where s.id not in (
  SELECT sr.student_id FROM student_result sr 
  JOIN training_course AS tc 
    on sr.training_course_id = tc.id 
  WHERE tc.name = 'Athletic'
    and s.id = sr.student_id
  );

  -- 6 
  SELECT tc.teacher_id FROM training_course AS tc
    GROUP BY tc.teacher_id
    HAVING count(tc.name) > 2;

  -- 7 
 SELECT er.student_id, s.last_name, count(er.exam_id) FROM exam_result AS er
  JOIN student AS s ON er.student_id = s.id
  GROUP BY er.student_id, er.exam_id
  HAVING count(er.exam_id) > 1;


-- 8
SELECT s.first_name, s.last_name FROM student AS s
  JOIN student_result AS sr on s.id = sr.student_id
  GROUP BY s.first_name, s.last_name
  ORDER BY sum(sr.result) desc LIMIT 5;

  -- 9
SELECT t.last_name FROM student_result sr 
  JOIN training_course AS tc 
    on sr.training_course_id = tc.id
  JOIN techer AS t where tc.teacher_id = t.id
GROUP BY t.last_name
ORDER BY sum(sr.result) desc LIMIT 1;
