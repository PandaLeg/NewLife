create table child (
    id int8 not null,
    name_child varchar(255) not null,
    state varchar(255),
    patient_id int8,
    primary key (id)
);

alter table if exists child
    add constraint child_patient_fk foreign key (patient_id) references patient;