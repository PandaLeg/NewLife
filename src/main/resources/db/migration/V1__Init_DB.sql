create sequence hibernate_sequence start 1 increment 1;

create table clinic (
    id int8 not null,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    address varchar(255) not null,
    city varchar(255) not null,
    name_clinic varchar(255) not null,
    phone varchar(255),
    clinic_picture varchar(255),
    active boolean not null,
    last_visit timestamp,
    primary key (id)
);

create table clinic_role (
    clinic_id int8 not null,
    roles varchar(255)
);

create table doctor (
    id int8 not null,
    active boolean not null,
    email varchar(255) not null,
    experience varchar(255),
    first_name varchar(255) not null,
    surname varchar(255) not null,
    last_visit timestamp,
    password varchar(255) not null,
    position varchar(255) not null,
    username varchar(255) not null,
    doctor_picture varchar(255),
    clinic_id int8,
    primary key (id)
);

create table doctor_role (
    doctor_id int8 not null,
    roles varchar(255)
);

create table patient (
    id int8 not null,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    first_name varchar(255) not null,
    surname varchar(255) not null,
    patient_picture varchar(255),
    active boolean not null,
    last_visit timestamp,
    clinic_id int8,
    primary key (id)
);

create table patient_role (
    patient_id int8 not null,
    roles varchar(255)
);

create table request (
    id int8 not null,
    message varchar(255) not null,
    clinic_id int8,
    doctor_id int8,
    patient_id int8,
    primary key (id)
);

alter table if exists clinic_role
    add constraint clinic_role_clinic_fk foreign key (clinic_id) references clinic;

alter table if exists doctor_role
    add constraint doctor_role_doctor_fk foreign key (doctor_id) references doctor;

alter table if exists patient_role
    add constraint patient_role_patient_fk foreign key (patient_id) references patient;

alter table if exists doctor
    add constraint doctor_clinic_fk foreign key (clinic_id) references clinic;

alter table if exists patient
    add constraint patient_clinic_fk foreign key (clinic_id) references clinic;

alter table if exists request
    add constraint request_clinic_fk foreign key (clinic_id) references clinic;

alter table if exists request
    add constraint request_doctor_fk foreign key (doctor_id) references doctor;

alter table if exists request
    add constraint request_patient_fk foreign key (patient_id) references patient;