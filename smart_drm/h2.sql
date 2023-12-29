-- ----------------------------
-- 用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user
(
    user_id         int  not null auto_increment comment '用户ID',
    dept_id         int   default null comment '部门ID',
    login_name      varchar(30) not null comment '登录账号',
    user_name       varchar(30)  default '' comment '用户昵称',
    user_type       varchar(2)   default '00' comment '用户类型（00系统用户 01注册用户）',
    email           varchar(50)  default '' comment '用户邮箱',
    phonenumber     varchar(11)  default '' comment '手机号码',
    sex             char(1)      default '0' comment '用户性别（0男 1女 2未知）',
    avatar          varchar(100) default '' comment '头像路径',
    password        varchar(50)  default '' comment '密码',
    salt            varchar(20)  default '' comment '盐加密',
    status          char(1)      default '0' comment '帐号状态（0正常 1停用）',
    del_flag        char(1)      default '0' comment '删除标志（0代表存在 2代表删除）',
    login_ip        varchar(128) default '' comment '最后登录IP',
    login_date      datetime comment '最后登录时间',
    pwd_update_date datetime comment '密码最后更新时间',
    create_by       varchar(64)  default '' comment '创建者',
    create_time     datetime comment '创建时间',
    update_by       varchar(64)  default '' comment '更新者',
    update_time     datetime comment '更新时间',
    remark          varchar(500) default null comment '备注',
    primary key (user_id)
);

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into sys_user
values (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '29c67a30398638269fe600f73a054934',
        '111111', '0', '0', '127.0.0.1', sysdate(), sysdate(), 'admin', sysdate(), '', null, '管理员');
insert into sys_user
values (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '8e6d98b90472783cc73c17047ddccf36', '222222',
        '0', '0', '127.0.0.1', sysdate(), sysdate(), 'admin', sysdate(), '', null, '测试员');

-- ----------------------------
-- 角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role
(
    role_id     int   not null auto_increment comment '角色ID',
    role_name   varchar(30)  not null comment '角色名称',
    role_key    varchar(100) not null comment '角色权限字符串',
    role_sort   int       not null comment '显示顺序',
    data_scope  char(1)      default '1' comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    status      char(1)      not null comment '角色状态（0正常 1停用）',
    del_flag    char(1)      default '0' comment '删除标志（0代表存在 2代表删除）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    PRIMARY KEY (role_id)
);

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into sys_role
values ('1', '超级管理员', 'admin', 1, 1, '0', '0', 'admin', sysdate(), '', null, '超级管理员');
insert into sys_role
values ('2', '普通角色', 'common', 2, 2, '0', '0', 'admin', sysdate(), '', null, '普通角色');

-- ----------------------------
-- 用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role
(
    user_id int not null comment '用户ID',
    role_id int not null comment '角色ID',
    primary key (user_id, role_id)
);

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role
values ('1', '1');
insert into sys_user_role
values ('2', '2');

-- ----------------------------
-- 部门表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept
(
    dept_id     int not null auto_increment comment '部门id',
    parent_id   int  default 0 comment '父部门id',
    ancestors   varchar(50) default '' comment '祖级列表',
    dept_name   varchar(30) default '' comment '部门名称',
    order_num   int      default 0 comment '显示顺序',
    leader      varchar(20) default null comment '负责人',
    phone       varchar(11) default null comment '联系电话',
    email       varchar(50) default null comment '邮箱',
    status      char(1)     default '0' comment '部门状态（0正常 1停用）',
    del_flag    char(1)     default '0' comment '删除标志（0代表存在 2代表删除）',
    create_by   varchar(64) default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) default '' comment '更新者',
    update_time datetime comment '更新时间',
    primary key (dept_id)
);

-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into sys_dept
values (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept
values (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept
values (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept
values (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '',
        null);
insert into sys_dept
values (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '',
        null);
insert into sys_dept
values (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '',
        null);
insert into sys_dept
values (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '',
        null);
insert into sys_dept
values (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '',
        null);
insert into sys_dept
values (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '',
        null);
insert into sys_dept
values (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '',
        null);

-- ----------------------------
-- 菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu
(
    menu_id     int  not null auto_increment comment '菜单ID',
    menu_name   varchar(50) not null comment '菜单名称',
    parent_id   int   default 0 comment '父菜单ID',
    order_num   int       default 0 comment '显示顺序',
    url         varchar(200) default '#' comment '请求地址',
    target      varchar(20)  default '' comment '打开方式（menuItem页签 menuBlank新窗口）',
    menu_type   char(1)      default '' comment '菜单类型（M目录 C菜单 F按钮）',
    visible     char(1)      default 0 comment '菜单状态（0显示 1隐藏）',
    is_refresh  char(1)      default 1 comment '是否刷新（0刷新 1不刷新）',
    perms       varchar(100) default null comment '权限标识',
    icon        varchar(100) default '#' comment '菜单图标',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default '' comment '备注',
    primary key (menu_id)
);

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into sys_menu
values ('1', '系统管理', '0', '1', '#', '', 'M', '0', '1', '', 'fa fa-gear', 'admin', sysdate(), '', null,
        '系统管理目录');
insert into sys_menu
values ('2', '系统监控', '0', '2', '#', '', 'M', '0', '1', '', 'fa fa-video-camera', 'admin', sysdate(), '', null,
        '系统监控目录');
insert into sys_menu
values ('3', '系统工具', '0', '3', '#', '', 'M', '0', '1', '', 'fa fa-bars', 'admin', sysdate(), '', null,
        '系统工具目录');
insert into sys_menu
values ('4', '若依官网', '0', '4', 'http://ruoyi.vip', 'menuBlank', 'C', '0', '1', '', 'fa fa-location-arrow', 'admin',
        sysdate(), '', null, '若依官网地址');
-- 二级菜单
insert into sys_menu
values ('100', '用户管理', '1', '1', '/system/sysUser', '', 'C', '0', '1', 'system:sysUser:view', 'fa fa-sysUser-o', 'admin',
        sysdate(), '', null, '用户管理菜单');
insert into sys_menu
values ('101', '角色管理', '1', '2', '/system/role', '', 'C', '0', '1', 'system:role:view', 'fa fa-sysUser-secret',
        'admin', sysdate(), '', null, '角色管理菜单');
insert into sys_menu
values ('102', '菜单管理', '1', '3', '/system/menu', '', 'C', '0', '1', 'system:menu:view', 'fa fa-th-list', 'admin',
        sysdate(), '', null, '菜单管理菜单');
insert into sys_menu
values ('103', '部门管理', '1', '4', '/system/dept', '', 'C', '0', '1', 'system:dept:view', 'fa fa-outdent', 'admin',
        sysdate(), '', null, '部门管理菜单');
insert into sys_menu
values ('104', '岗位管理', '1', '5', '/system/post', '', 'C', '0', '1', 'system:post:view', 'fa fa-address-card-o',
        'admin', sysdate(), '', null, '岗位管理菜单');
insert into sys_menu
values ('105', '字典管理', '1', '6', '/system/dict', '', 'C', '0', '1', 'system:dict:view', 'fa fa-bookmark-o', 'admin',
        sysdate(), '', null, '字典管理菜单');
insert into sys_menu
values ('106', '参数设置', '1', '7', '/system/config', '', 'C', '0', '1', 'system:config:view', 'fa fa-sun-o', 'admin',
        sysdate(), '', null, '参数设置菜单');
insert into sys_menu
values ('107', '通知公告', '1', '8', '/system/notice', '', 'C', '0', '1', 'system:notice:view', 'fa fa-bullhorn',
        'admin', sysdate(), '', null, '通知公告菜单');
insert into sys_menu
values ('108', '日志管理', '1', '9', '#', '', 'M', '0', '1', '', 'fa fa-pencil-square-o', 'admin', sysdate(), '', null,
        '日志管理菜单');
insert into sys_menu
values ('109', '在线用户', '2', '1', '/monitor/online', '', 'C', '0', '1', 'monitor:online:view', 'fa fa-sysUser-circle',
        'admin', sysdate(), '', null, '在线用户菜单');
insert into sys_menu
values ('110', '定时任务', '2', '2', '/monitor/job', '', 'C', '0', '1', 'monitor:job:view', 'fa fa-tasks', 'admin',
        sysdate(), '', null, '定时任务菜单');
insert into sys_menu
values ('111', '数据监控', '2', '3', '/monitor/data', '', 'C', '0', '1', 'monitor:data:view', 'fa fa-bug', 'admin',
        sysdate(), '', null, '数据监控菜单');
insert into sys_menu
values ('112', '服务监控', '2', '4', '/monitor/server', '', 'C', '0', '1', 'monitor:server:view', 'fa fa-server',
        'admin', sysdate(), '', null, '服务监控菜单');
insert into sys_menu
values ('113', '缓存监控', '2', '5', '/monitor/cache', '', 'C', '0', '1', 'monitor:cache:view', 'fa fa-cube', 'admin',
        sysdate(), '', null, '缓存监控菜单');
insert into sys_menu
values ('114', '表单构建', '3', '1', '/tool/build', '', 'C', '0', '1', 'tool:build:view', 'fa fa-wpforms', 'admin',
        sysdate(), '', null, '表单构建菜单');
insert into sys_menu
values ('115', '代码生成', '3', '2', '/tool/gen', '', 'C', '0', '1', 'tool:gen:view', 'fa fa-code', 'admin', sysdate(),
        '', null, '代码生成菜单');
insert into sys_menu
values ('116', '系统接口', '3', '3', '/tool/swagger', '', 'C', '0', '1', 'tool:swagger:view', 'fa fa-gg', 'admin',
        sysdate(), '', null, '系统接口菜单');
-- 三级菜单
insert into sys_menu
values ('500', '操作日志', '108', '1', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view',
        'fa fa-address-book', 'admin', sysdate(), '', null, '操作日志菜单');
insert into sys_menu
values ('501', '登录日志', '108', '2', '/monitor/logininfor', '', 'C', '0', '1', 'monitor:logininfor:view',
        'fa fa-file-image-o', 'admin', sysdate(), '', null, '登录日志菜单');
-- 用户管理按钮
insert into sys_menu
values ('1000', '用户查询', '100', '1', '#', '', 'F', '0', '1', 'system:sysUser:list', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1001', '用户新增', '100', '2', '#', '', 'F', '0', '1', 'system:sysUser:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1002', '用户修改', '100', '3', '#', '', 'F', '0', '1', 'system:sysUser:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1003', '用户删除', '100', '4', '#', '', 'F', '0', '1', 'system:sysUser:remove', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1004', '用户导出', '100', '5', '#', '', 'F', '0', '1', 'system:sysUser:export', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1005', '用户导入', '100', '6', '#', '', 'F', '0', '1', 'system:sysUser:import', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1006', '重置密码', '100', '7', '#', '', 'F', '0', '1', 'system:sysUser:resetPwd', '#', 'admin', sysdate(), '',
        null, '');
-- 角色管理按钮
insert into sys_menu
values ('1007', '角色查询', '101', '1', '#', '', 'F', '0', '1', 'system:role:list', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1008', '角色新增', '101', '2', '#', '', 'F', '0', '1', 'system:role:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1009', '角色修改', '101', '3', '#', '', 'F', '0', '1', 'system:role:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1010', '角色删除', '101', '4', '#', '', 'F', '0', '1', 'system:role:remove', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1011', '角色导出', '101', '5', '#', '', 'F', '0', '1', 'system:role:export', '#', 'admin', sysdate(), '', null,
        '');
-- 菜单管理按钮
insert into sys_menu
values ('1012', '菜单查询', '102', '1', '#', '', 'F', '0', '1', 'system:menu:list', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1013', '菜单新增', '102', '2', '#', '', 'F', '0', '1', 'system:menu:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1014', '菜单修改', '102', '3', '#', '', 'F', '0', '1', 'system:menu:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1015', '菜单删除', '102', '4', '#', '', 'F', '0', '1', 'system:menu:remove', '#', 'admin', sysdate(), '', null,
        '');
-- 部门管理按钮
insert into sys_menu
values ('1016', '部门查询', '103', '1', '#', '', 'F', '0', '1', 'system:dept:list', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1017', '部门新增', '103', '2', '#', '', 'F', '0', '1', 'system:dept:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1018', '部门修改', '103', '3', '#', '', 'F', '0', '1', 'system:dept:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1019', '部门删除', '103', '4', '#', '', 'F', '0', '1', 'system:dept:remove', '#', 'admin', sysdate(), '', null,
        '');
-- 岗位管理按钮
insert into sys_menu
values ('1020', '岗位查询', '104', '1', '#', '', 'F', '0', '1', 'system:post:list', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1021', '岗位新增', '104', '2', '#', '', 'F', '0', '1', 'system:post:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1022', '岗位修改', '104', '3', '#', '', 'F', '0', '1', 'system:post:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1023', '岗位删除', '104', '4', '#', '', 'F', '0', '1', 'system:post:remove', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1024', '岗位导出', '104', '5', '#', '', 'F', '0', '1', 'system:post:export', '#', 'admin', sysdate(), '', null,
        '');
-- 字典管理按钮
insert into sys_menu
values ('1025', '字典查询', '105', '1', '#', '', 'F', '0', '1', 'system:dict:list', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1026', '字典新增', '105', '2', '#', '', 'F', '0', '1', 'system:dict:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1027', '字典修改', '105', '3', '#', '', 'F', '0', '1', 'system:dict:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1028', '字典删除', '105', '4', '#', '', 'F', '0', '1', 'system:dict:remove', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1029', '字典导出', '105', '5', '#', '', 'F', '0', '1', 'system:dict:export', '#', 'admin', sysdate(), '', null,
        '');
-- 参数设置按钮
insert into sys_menu
values ('1030', '参数查询', '106', '1', '#', '', 'F', '0', '1', 'system:config:list', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1031', '参数新增', '106', '2', '#', '', 'F', '0', '1', 'system:config:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1032', '参数修改', '106', '3', '#', '', 'F', '0', '1', 'system:config:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1033', '参数删除', '106', '4', '#', '', 'F', '0', '1', 'system:config:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1034', '参数导出', '106', '5', '#', '', 'F', '0', '1', 'system:config:export', '#', 'admin', sysdate(), '',
        null, '');
-- 通知公告按钮
insert into sys_menu
values ('1035', '公告查询', '107', '1', '#', '', 'F', '0', '1', 'system:notice:list', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1036', '公告新增', '107', '2', '#', '', 'F', '0', '1', 'system:notice:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1037', '公告修改', '107', '3', '#', '', 'F', '0', '1', 'system:notice:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1038', '公告删除', '107', '4', '#', '', 'F', '0', '1', 'system:notice:remove', '#', 'admin', sysdate(), '',
        null, '');
-- 操作日志按钮
insert into sys_menu
values ('1039', '操作查询', '500', '1', '#', '', 'F', '0', '1', 'monitor:operlog:list', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1040', '操作删除', '500', '2', '#', '', 'F', '0', '1', 'monitor:operlog:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1041', '详细信息', '500', '3', '#', '', 'F', '0', '1', 'monitor:operlog:detail', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1042', '日志导出', '500', '4', '#', '', 'F', '0', '1', 'monitor:operlog:export', '#', 'admin', sysdate(), '',
        null, '');
-- 登录日志按钮
insert into sys_menu
values ('1043', '登录查询', '501', '1', '#', '', 'F', '0', '1', 'monitor:logininfor:list', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1044', '登录删除', '501', '2', '#', '', 'F', '0', '1', 'monitor:logininfor:remove', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1045', '日志导出', '501', '3', '#', '', 'F', '0', '1', 'monitor:logininfor:export', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1046', '账户解锁', '501', '4', '#', '', 'F', '0', '1', 'monitor:logininfor:unlock', '#', 'admin', sysdate(),
        '', null, '');
-- 在线用户按钮
insert into sys_menu
values ('1047', '在线查询', '109', '1', '#', '', 'F', '0', '1', 'monitor:online:list', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1048', '批量强退', '109', '2', '#', '', 'F', '0', '1', 'monitor:online:batchForceLogout', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1049', '单条强退', '109', '3', '#', '', 'F', '0', '1', 'monitor:online:forceLogout', '#', 'admin', sysdate(),
        '', null, '');
-- 定时任务按钮
insert into sys_menu
values ('1050', '任务查询', '110', '1', '#', '', 'F', '0', '1', 'monitor:job:list', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1051', '任务新增', '110', '2', '#', '', 'F', '0', '1', 'monitor:job:add', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1052', '任务修改', '110', '3', '#', '', 'F', '0', '1', 'monitor:job:edit', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1053', '任务删除', '110', '4', '#', '', 'F', '0', '1', 'monitor:job:remove', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1054', '状态修改', '110', '5', '#', '', 'F', '0', '1', 'monitor:job:changeStatus', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1055', '任务详细', '110', '6', '#', '', 'F', '0', '1', 'monitor:job:detail', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1056', '任务导出', '110', '7', '#', '', 'F', '0', '1', 'monitor:job:export', '#', 'admin', sysdate(), '', null,
        '');
-- 代码生成按钮
insert into sys_menu
values ('1057', '生成查询', '115', '1', '#', '', 'F', '0', '1', 'tool:gen:list', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu
values ('1058', '生成修改', '115', '2', '#', '', 'F', '0', '1', 'tool:gen:edit', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu
values ('1059', '生成删除', '115', '3', '#', '', 'F', '0', '1', 'tool:gen:remove', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1060', '预览代码', '115', '4', '#', '', 'F', '0', '1', 'tool:gen:preview', '#', 'admin', sysdate(), '', null,
        '');
insert into sys_menu
values ('1061', '生成代码', '115', '5', '#', '', 'F', '0', '1', 'tool:gen:code', '#', 'admin', sysdate(), '', null, '');
