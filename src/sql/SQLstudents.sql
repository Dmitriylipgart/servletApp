-- 1
SELECT s.first_name, s.last_name 
  FROM student as s, student_result as sr
  where s.id = sr.student_id
  and sr.result > 2
order by sr.result DESC;


-- 2 все сдавшие хотя бы один экзамен на 4 или 5
SELECT COUNT(DISTINCT s.id) FROM student as s, student_result as sr
  where s.id = sr.student_id
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


SELECT * from student_result
  
order by student_id;

SELECT * from exam_result
order by student_id;
SELECT * from training_course;
 SELECT * from exam;

SELECT * from techer;

SELECT *
  from student_result as sr 
  where NOT EXISTS (
  SELECT * FROM exam_result as er
  where er.result = sr.result  
  AND sr.student_id = er.student_id
  );

 
-- 4
SELECT avg(sr.result) from student_result sr 
  JOIN training_course as tc 
    on sr.training_course_id = tc.id
  where tc.name = 'RDBMS' 
ORDER BY tc.id;


-- 5 
  /*Таблицы student_result и exam_result неправильно заполнены. В одной записи в колонке training_course_id = 4 не может соответствовать exam_id = 3, 
  т.к. согласно таблицу exam training_course_id = 4 соответствует id = 4, 3 -> 3 и т.д. Выборку нужно было бы делать 
  по имени курса Math (наиболее соответствующее имя для Теории графов), но т.к. вместо training_course_id = 3 в таблице student_result все training_course_id = 4
  поэтому делаю выборку по Athletic, что соответствует training_course_id = 4.
  */
      -- 5.1
SELECT s.first_name, s.last_name from student as s 
  where not EXISTS(
  SELECT sr.student_id from student_result sr 
  JOIN training_course as tc 
    on sr.training_course_id = tc.id 
  where tc.name = 'Athletic'
    and s.id = sr.student_id
  );
    -- 5.2
SELECT s.first_name, s.last_name from student as s 
  where s.id not in (
  SELECT sr.student_id from student_result sr 
  JOIN training_course as tc 
    on sr.training_course_id = tc.id 
  where tc.name = 'Athletic'
    and s.id = sr.student_id
  );

  -- 6 
  select tc.teacher_id from training_course AS tc
    group by tc.teacher_id
    HAVING count(tc.name) > 2;

  -- 7 
 SELECT er.student_id, s.last_name, count(er.exam_id) FROM exam_result as er
  JOIN student AS s ON er.student_id = s.id
  GROUP by er.student_id, er.exam_id
  HAVING count(er.exam_id) > 1;


-- 8
SELECT s.first_name, s.last_name FROM student AS s
  JOIN student_result as sr on s.id = sr.student_id
  GROUP by s.first_name, s.last_name
  order by sum(sr.result) desc LIMIT 5;

  -- 9
SELECT t.last_name from student_result sr 
  JOIN training_course as tc 
    on sr.training_course_id = tc.id
  JOIN techer as t where tc.teacher_id = t.id
GROUP by t.last_name
order by sum(sr.result) desc LIMIT 1;
