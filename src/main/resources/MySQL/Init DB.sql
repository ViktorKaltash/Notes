Create table accounts (
account_id int not null auto_increment,
account_name varchar(16) not null,
email varchar(48) not null,
auth_code varchar(10) not null,
reg_date date not null,

constraint pk_account_id primary key (account_id)
);

create table notes (
note_id int not null auto_increment,
account_id int not null,
note_text text,
note_label text not null,
creation_date date not null,
modify_date date not null,
color varchar(6),

constraint pk_note_id primary key (note_id),
constraint fk_account_id foreign key  (account_id)
references accounts (account_id)
);