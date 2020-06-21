insert into clinic (id, username, password, email, address, city, name_clinic, active)
    values (1, 'admin', 'gostkurt', 'admin@gmail.com', 'AdminAddress', 'Slovyansk', 'AdminClinic', true);
insert into clinic_role (clinic_id, roles)
    values (1, 'ADMIN');