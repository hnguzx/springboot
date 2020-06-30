DROP table t_user

create table t_user(
   id int(12) not null auto_increment COMMENT '用户ID',
   user_name varchar(20) not null,
   sex varchar(3) not null default 1 check(sex in(1,2)),
   note varchar(250) null,
   primary key(id)
);

#创建索引

alter table t_user comment '用户基本信息表';
alter table t_user modify column id int(12) not null auto_increment comment '用户id';
alter table t_user modify column user_name varchar(20) not null comment '用户名';
alter table t_user modify column sex varchar(3) not null comment '性别,1-男 2-女';
alter table t_user modify column note varchar(256) null comment '备注';
alter table t_user modify column note varchar(256) comment '备注';


