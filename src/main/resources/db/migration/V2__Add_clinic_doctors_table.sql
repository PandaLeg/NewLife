create table clinic_doctors(
    clinic_id int8 not null references clinic,
    doctor_id int8 not null references doctor,
    primary key (clinic_id, doctor_id)
);