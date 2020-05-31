package com.newLife.domain;

public final class Views {
    public interface IdUsernameEmailNameClinicAddress {}
    public interface FullClinic extends IdUsernameEmailNameClinicAddress{}

    public interface IdUsernameEmailFirstNameSurnamePosition {}
    public interface FullDoctor extends IdUsernameEmailFirstNameSurnamePosition {}

    public interface IdUsernameEmailFirstNameSurname {}
    public interface FullPatient extends IdUsernameEmailFirstNameSurname {}
}
