create table message (
    id int8 not null,
    title varchar(255) not null,
    description varchar(255) not null,
    patient_id int8,
    clinic_id int8,
    primary key (id)
);

alter table if exists message
    add constraint message_patient_fk foreign key (patient_id) references patient;

alter table if exists message
    add constraint message_clinic_fk foreign key (clinic_id) references clinic;