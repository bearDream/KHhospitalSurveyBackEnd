package com.example.questionnaire.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patient", schema = "hospital_survey", catalog = "")
public class Patient {
    private int id;
    private String patientName;
    private int gender;
    private String phone;
    private String birthday;
    private Integer departmentId;
    private Integer hospitalId;
    private String idNumber;
    private Integer status;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "patient_name")
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Basic
    @Column(name = "gender")
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "birthday")
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "department_id")
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "hospital_id")
    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    @Basic
    @Column(name = "id_number")
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient that = (Patient) o;
        return id == that.id &&
                gender == that.gender &&
                Objects.equals(patientName, that.patientName) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(departmentId, that.departmentId) &&
                Objects.equals(hospitalId, that.hospitalId) &&
                Objects.equals(idNumber, that.idNumber) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientName, gender, phone, birthday, departmentId, hospitalId, idNumber, status);
    }
}
