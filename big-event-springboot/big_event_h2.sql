-- 用户表
create table sys_user
(
    id          int primary key auto_increment comment 'ID',
    username    varchar(20) not null unique comment '用户名',
    password    varchar(32) comment '密码',
    nickname    varchar(10)  default '' comment '昵称',
    email       varchar(128) default '' comment '邮箱',
    user_pic    varchar(128) default '' comment '头像',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '修改时间'
);

-- 分类表
create table sys_category
(
    id             int primary key auto_increment comment 'ID',
    category_name  varchar(32) not null comment '分类名称',
    category_alias varchar(32) not null comment '分类别名',
    create_user    int         not null comment '创建人ID',
    create_time    datetime    not null comment '创建时间',
    update_time    datetime    not null comment '修改时间'
);

-- 文章表
create table sys_article
(
    id          int primary key auto_increment comment 'ID',
    title       varchar(30)    not null comment '文章标题',
    content     varchar(10000) not null comment '文章内容',
    cover_img   varchar(128)   not null comment '文章封面',
    state       varchar(3) default '草稿' comment '文章状态: 只能是[已发布] 或者 [草稿]',
    category_id int comment '文章分类ID',
    create_user int            not null comment '创建人ID',
    create_time datetime       not null comment '创建时间',
    update_time datetime       not null comment '修改时间'
)