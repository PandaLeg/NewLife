create table clinic_patients(
    clinic_id int8 not null references clinic,
    patient_id int8 not null references patient,
    primary key (clinic_id, patient_id)
);