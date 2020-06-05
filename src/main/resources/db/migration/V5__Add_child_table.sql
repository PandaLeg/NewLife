create table child (
    id int8 not null,
    name_child varchar(255) not null,
    state varchar(255),
    age varchar(100) not null,
    height varchar(255),
    weight varchar(255),
    temperature varchar(255),
    pulse varchar(255),
    pressure varchar(255),
    patient_id int8,
    primary key (id)
);

alter table if exists child
    add constraint child_patient_fk foreign key (patient_id) references patient;