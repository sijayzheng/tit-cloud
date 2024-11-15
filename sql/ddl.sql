drop table if exists gen_table;
create table gen_table
(
    id            bigint      not null auto_increment comment 'id',
    table_name    varchar(50) not null comment '表名',
    table_comment varchar(200) default null comment '表备注',
    class_name    varchar(100) default '' comment '实体类名称',
    template_type varchar(1)   default 'L' comment '模板类型',
    package_name  varchar(100) default '' comment '包路径',
    module_name   varchar(30)  default '' comment '模块名',
    business_name varchar(30) comment '生成业务名',
    function_name varchar(50) comment '生成功能名',
    author        varchar(50)  default 'Sijay' comment '作者',
    gen_type      char(1)      default 'Z' comment '生成方式',
    gen_path      varchar(200) default '/' comment '生成路径',
    super_class   varchar(100) default '' comment '父类',
    menu_id       bigint       default null comment '所属菜单',
    create_dept   bigint       default 1 comment '创建部门',
    creator       bigint       default 1 comment '创建者',
    create_time   datetime     default now() comment '创建时间',
    updater       bigint       default null comment '更新者',
    update_time   datetime     default null comment '更新时间',
    version       bigint       default 1 comment '版本号',
    primary key (id)
) comment '表信息表';
drop table if exists gen_table_column;
create table gen_table_column
(
    id             bigint      not null auto_increment comment 'id',
    table_id       bigint      not null comment '表',
    column_name    varchar(50) not null comment '列名',
    column_comment varchar(200) default null comment '列注释',
    data_type      varchar(50) not null comment '列类型',
    length         int          default null comment '长度',
    java_type      varchar(100) default '' comment '字段类型',
    field_name     varchar(100) default '' comment '字段名称',
    primary_key    boolean      default false comment '是否主键',
    required       boolean      default false comment '是否必填',
    unique_key     boolean      default false comment '是否唯一',
    addable        boolean      default true comment '是否可添加',
    editable       boolean      default true comment '是否可编辑',
    super_column   boolean      default false comment '是否父类字段',
    excel_column   boolean      default false comment '是否是导入导出字段',
    visible        boolean      default true comment '是否显示',
    queryable      boolean      default false comment '是否可查询',
    query_type     varchar(20)  default 'EQ' comment '查询方式',
    input_type     varchar(20)  default 'INPUT' comment '输入类型',
    dict_type      varchar(200) default '' comment '数据字典',
    sort           int          default null comment '排序',
    create_dept    bigint       default 1 comment '创建部门',
    creator        bigint       default 1 comment '创建者',
    create_time    datetime     default now() comment '创建时间',
    updater        bigint       default null comment '更新者',
    update_time    datetime     default null comment '更新时间',
    version        bigint       default 1 comment '版本号',
    primary key (id)
) comment '列信息表';
drop table if exists sys_menu;
create table sys_menu
(
    id          bigint      not null auto_increment comment 'id',
    parent_id   bigint      not null default 0 comment '上级',
    name        varchar(50) not null comment '菜单名称',
    type        char(1)              default '' comment '菜单类型',
    path        varchar(200)         default null comment '路径',
    component   varchar(255)         default null comment '组件路径',
    query_param varchar(255)         default null comment '路由参数',
    perms       varchar(100)         default null comment '权限标识',
    icon        varchar(100)         default null comment '图标',
    sort        int                  default 0 comment '排序',
    link        boolean              default false comment '是否为外链',
    cache       boolean              default true comment '是否缓存',
    visible     boolean              default true comment '显示状态',
    enabled     boolean              default true comment '是否启用',
    create_dept bigint               default 1 comment '创建部门',
    creator     bigint               default 1 comment '创建者',
    create_time datetime             default now() comment '创建时间',
    updater     bigint               default null comment '更新者',
    update_time datetime             default null comment '更新时间',
    deleted     boolean              default false comment '是否删除',
    version     bigint               default 1 comment '版本号',
    primary key (id)
) comment '菜单信息表';
drop table if exists sys_module;
create table sys_module
(
    id          bigint      not null auto_increment comment 'id',
    name        varchar(50) not null comment '模块名称',
    menu_ids    json        null comment '菜单',
    create_dept bigint   default 1 comment '创建部门',
    creator     bigint   default 1 comment '创建者',
    create_time datetime default now() comment '创建时间',
    updater     bigint   default null comment '更新者',
    update_time datetime default null comment '更新时间',
    primary key (id)
) comment '系统模块表';
drop table if exists sys_role;
create table sys_role
(
    id          bigint      not null auto_increment comment 'id',
    name        varchar(50) not null comment '角色名称',
    code        varchar(50) not null unique comment '角色编码',
    menu_ids    json     default '[]' comment '菜单',
    sort        int      default 0 comment '排序',
    create_dept bigint   default 1 comment '创建部门',
    creator     bigint   default 1 comment '创建者',
    create_time datetime default now() comment '创建时间',
    updater     bigint   default null comment '更新者',
    update_time datetime default null comment '更新时间',
    deleted     boolean  default false comment '是否删除',
    version     bigint   default 1 comment '版本号',
    primary key (id)
) comment '角色信息表';
drop table if exists sys_dept;
create table sys_dept
(
    id          bigint      not null auto_increment comment 'id',
    parent_id   bigint      not null default 0 comment '上级',
    name        varchar(50) not null comment '部门名称',
    leader      bigint               default null comment '部门负责人',
    phone       varchar(11)          default null comment '部门电话',
    sort        int                  default 0 comment '排序',
    create_dept bigint               default 1 comment '创建部门',
    creator     bigint               default 1 comment '创建者',
    create_time datetime             default now() comment '创建时间',
    updater     bigint               default null comment '更新者',
    update_time datetime             default null comment '更新时间',
    deleted     boolean              default false comment '是否删除',
    version     bigint               default 1 comment '版本号',
    primary key (id)
) comment '部门信息表';
drop table if exists sys_post;
create table sys_post
(
    id          bigint      not null auto_increment comment 'id',
    name        varchar(50) not null comment '岗位名称',
    code        varchar(50) not null unique comment '岗位编码',
    sort        int      default 0 comment '排序',
    create_dept bigint   default 1 comment '创建部门',
    creator     bigint   default 1 comment '创建者',
    create_time datetime default now() comment '创建时间',
    updater     bigint   default null comment '更新者',
    update_time datetime default null comment '更新时间',
    deleted     boolean  default false comment '是否删除',
    version     bigint   default 1 comment '版本号',
    primary key (id)
) comment '岗位信息表';
drop table if exists sys_user;
create table sys_user
(
    id              bigint       not null auto_increment comment 'id',
    username        varchar(50)  not null unique comment '用户名',
    phone           varchar(11)  default null unique comment '手机号',
    email           varchar(100) default null unique comment '邮箱',
    password        varchar(128) not null comment '密码',
    dept_id         bigint       default null comment '部门',
    role_ids        json         default '[]' comment '角色',
    post_ids        json         default '[]' comment '岗位',
    enable          boolean      default true comment '是否启用',
    sort            int          default 0 comment '排序',
    last_login_time datetime     default null comment '最后登录时间',
    create_dept     bigint       default 1 comment '创建部门',
    creator         bigint       default 1 comment '创建者',
    create_time     datetime     default now() comment '创建时间',
    updater         bigint       default null comment '更新者',
    update_time     datetime     default null comment '更新时间',
    deleted         boolean      default false comment '是否删除',
    version         bigint       default 1 comment '版本号',
    primary key (id)
) comment '登录用户表';
drop table if exists sys_user_info;
create table sys_user_info
(
    id          bigint      not null auto_increment comment 'id',
    user_id     bigint      not null comment '用户',
    name        varchar(50) not null comment '姓名',
    gender      varchar(1)   default 'U' comment '性别',
    birthday    date         default null comment '生日',
    avatar      varchar(200) default null comment '头像',
    province    bigint       default null comment '省',
    city        bigint       default null comment '市',
    area        bigint       default null comment '区',
    address     varchar(200) default null comment '详细地址',
    remark      varchar(200) default null comment '备注',
    create_dept bigint       default 1 comment '创建部门',
    creator     bigint       default 1 comment '创建者',
    create_time datetime     default now() comment '创建时间',
    updater     bigint       default null comment '更新者',
    update_time datetime     default null comment '更新时间',
    deleted     boolean      default false comment '是否删除',
    version     bigint       default 1 comment '版本号',
    primary key (id)
) comment '用户信息表';
drop table if exists sys_notice;
create table sys_notice
(
    id          bigint       not null auto_increment comment 'id',
    title       varchar(50)  not null comment '标题',
    content     varchar(500) not null comment '内容',
    type        varchar(10)  not null comment '类型',
    status      varchar(10)  not null comment '状态',
    create_dept bigint   default 1 comment '创建部门',
    creator     bigint   default 1 comment '创建者',
    create_time datetime default now() comment '创建时间',
    updater     bigint   default null comment '更新者',
    update_time datetime default null comment '更新时间',
    deleted     boolean  default false comment '是否删除',
    version     bigint   default 1 comment '版本号',
    primary key (id)
) comment '通知公告表';
drop table if exists sys_config;
create table sys_config
(
    id          bigint      not null auto_increment comment 'id',
    name        varchar(50) not null comment '配置名称',
    code        varchar(50) not null unique comment '配置编码',
    value       varchar(50) not null comment '配置值',
    sort        int      default 0 comment '排序',
    create_dept bigint   default 1 comment '创建部门',
    creator     bigint   default 1 comment '创建者',
    create_time datetime default now() comment '创建时间',
    updater     bigint   default null comment '更新者',
    update_time datetime default null comment '更新时间',
    deleted     boolean  default false comment '是否删除',
    version     bigint   default 1 comment '版本号',
    primary key (id)
) comment '系统配置表';
drop table if exists sys_dict_type;
create table sys_dict_type
(
    id          bigint not null auto_increment comment 'id',
    name        varchar(100) default '' comment '字典名称',
    code        varchar(100) default '' comment '字典编码',
    internal    boolean      default false comment '内置',
    create_dept bigint       default 1 comment '创建部门',
    creator     bigint       default 1 comment '创建者',
    create_time datetime     default now() comment '创建时间',
    updater     bigint       default null comment '更新者',
    update_time datetime     default null comment '更新时间',
    primary key (id),
    unique (code)
) comment = '字典类型表';
drop table if exists sys_dict_data;
create table sys_dict_data
(
    id           bigint       not null auto_increment comment 'id',
    type_code    varchar(100) not null comment '字典类型',
    label        varchar(100) default '' comment '标签',
    value        varchar(100) default '' comment '值',
    sort         int          default 0 comment '字典排序',
    css_class    varchar(100) default null comment '样式属性',
    display_type varchar(100) default null comment '回显样式',
    defaults     boolean      default false comment '是否默认',
    create_dept  bigint       default 1 comment '创建部门',
    creator      bigint       default 1 comment '创建者',
    create_time  datetime     default now() comment '创建时间',
    updater      bigint       default null comment '更新者',
    update_time  datetime     default null comment '更新时间',
    primary key (id)
) comment = '字典数据表';

drop table if exists region_data;
create table region_data
(
    id          bigint      not null auto_increment comment 'id',
    parent_id   bigint      not null default 0 comment '上级',
    name        varchar(50) not null comment '名称',
    code        varchar(50) not null unique comment '编码',
    level       int                  default 0 comment '级别',
    sort        int                  default 0 comment '排序',
    create_dept bigint               default 1 comment '创建部门',
    creator     bigint               default 1 comment '创建者',
    create_time datetime             default now() comment '创建时间',
    updater     bigint               default null comment '更新者',
    update_time datetime             default null comment '更新时间',
    deleted     boolean              default false comment '是否删除',
    version     bigint               default 1 comment '版本号',
    primary key (id)
) comment '行政区划数据表';

drop table if exists log_login;
create table log_login
(
    id         bigint      not null auto_increment comment 'id',
    user_id    bigint      not null comment '用户',
    ip         varchar(50) not null comment 'ip',
    login_time datetime    not null comment '登录时间',
    primary key (id)
) comment '登录日志表';
drop table if exists log_business;
create table log_business
(
    id               bigint not null auto_increment comment 'id',
    user_id          bigint        default null comment '用户',
    ip               varchar(50)   default '' comment 'ip',
    method           varchar(200)  default '' comment '方法',
    params           varchar(2000) default '' comment '参数',
    request_type     varchar(10)   default '' comment '请求类型',
    request_url      varchar(100)  default '' comment '请求url',
    return_result    varchar(2000) default '' comment '返回信息',
    business_name    varchar(50)   default '' comment '业务名称',
    operation_type   varchar(50)   default '' comment '操作类型',
    operation_result varchar(4)    default '' comment '操作结果',
    error_message    varchar(2000) default '' comment '错误信息',
    operation_time   datetime      default now() comment '操作时间',
    primary key (id)
) comment '业务日志表';
