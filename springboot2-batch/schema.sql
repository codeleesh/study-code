CREATE TABLE API_TRN_JOB_LST (
    id bigint not null auto_increment,
    class_method varchar(50),
    target_url varchar(255),
    http_method varchar(10),
    http_header varchar(255),
    http_body varchar(255),
    trans_flag char,
    created_date datetime,
    created_user_id varchar(15),
    modified_date datetime,
    modified_user_id varchar(15),
    primary key (id)
) engine=InnoDB;

CREATE TABLE API_TRN_JOB_LST_HSTR (
    id bigint not null auto_increment,
    class_method varchar(50),
    target_url varchar(255),
    http_method varchar(10),
    http_header varchar(255),
    http_body varchar(255),
    trans_flag char,
    created_date datetime,
    created_user_id varchar(15),
    modified_date datetime,
    modified_user_id varchar(15),
    primary key (id)
) engine=InnoDB;
