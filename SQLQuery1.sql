create database QuizWeb
use QuizWeb
create table tblUsers(
	email varchar(200) primary key,
	name varchar(100),
	role varchar(100),
	password varchar(500),
	status varchar(50)
)
create table tblSubjects(
	id varchar(200) primary key,
	name varchar(200),
	num_quiz int,
)
create table tblQuizes(
	id varchar(200) primary key,
	num_question int,
	time_take time,
	create_date datetime,
	from_date datetime,
	to_date datetime,
	subject_id varchar(200),
	create_by varchar(200)
	foreign key (create_by) references tblUsers(email),
	foreign key (subject_id) references tblSubjects(id),
)
create table tblQuestionsResource (
	id varchar(200) primary key,
	question_content varchar(200),
	quiz_id varchar(200),
	status varchar(20),
	foreign key(quiz_id) references tblQuizes(id)
)
create table tblAnsweresResource(
	id varchar(200) primary key,
	question_id varchar(200),
	answer_title varchar(1),
	answer_content varchar(200),
	is_correct bit,
	foreign key (question_id) references tblQuestionsResource(id) 
)
create table tblQuizDo(
	id varchar(200) primary key,
	do_by varchar(200),
	create_date datetime,
	quiz_id varchar(200),
	score decimal(2,2) ,
	right_correct int,
	foreign key(quiz_id) references tblQuizes(id),
	foreign key(do_by) references tblUsers(email),
	
)
create table tblQuestions(
	id varchar(200) primary key,
	question_content varchar(200),
	quiz_do_id varchar(200),	
	status varchar(20),
	foreign key (quiz_do_id) references tblQuizDo(id),
)
create table tblAnsweres(
	id varchar(200) primary key,
	question_id varchar(200),
	answer_title varchar(1),
	answer_content varchar(200),
	is_correct bit,
	answer_choosed bit,
	foreign key (question_id) references tblQuestions(id),
	
)