DROP table student;

create table student(
   id int(12) not null auto_increment COMMENT '用户ID',
   user_name varchar(20) not null,
   note varchar(250) null,
   primary key(id)
);

#创建索引

alter table t_user modify column id int(12) not null auto_increment comment '用户id';
alter table t_user modify column user_name varchar(20) not null comment '用户名';
alter table t_user modify column note varchar(256) null comment '备注';


