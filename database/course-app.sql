/*
create table course_info
(
	id int unsigned not null auto_increment primary key,
	name varchar(20) not null
);
*/

/*
create table student_info
(
	id varchar(10) not null primary key
);
*/

/*
create table student_attended_courses
(
	student_id varchar(10) not null,
	course_id int unsigned not null,
	
	foreign key(student_id) references student_info(id),
	foreign key(course_id) references course_info(id),
	primary key(student_id, course_id)
);
*/

/*
#select student 1512190417's attended courses
select name from course_info where course_info.id in (
	select course_id from student_attended_courses where (student_id='1512190417')
 );
 */
 
 
 #course rate, if < 0 , this course unscored. 
 #alter table student_attended_courses add rate int default -1;
 
 #change to char to save space
 #alter table student_attended_courses modify rate varchar(5) not null default "";





