create table doctors_patients(
    doctor_id int8 not null references doctor,
    patient_id int8 not null references patient,
    primary key (doctor_id, patient_id)
);