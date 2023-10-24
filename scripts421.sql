alter table student
add constraint student_age_constraint check(age > 16);


alter table student
add constraint student_name_constraint unique(name);

alter table student
alter column name set not null;


alter table faculty
add constraint faculty_name_and_color_constraint unique(name, color);


alter table student
alter column age set default 20;