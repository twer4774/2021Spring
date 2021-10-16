package com.fastcampus.validation.dto;

import com.fastcampus.validation.annotation.YearMonth;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class User {

    @NotBlank
    private String name;

    @Max(value = 90)
    private int age;

    @Email
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message= "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    @JsonProperty("phone_number")
    private String phoneNumber;

//    @Size(min = 6, max = 6)
    @YearMonth
    private String reqYearMonth; //yyyyMM

    //Assert 어노테이션 인식하기 위해 is를 앞에 붙여야 한다.
    //YearMonth 어노테이션으로 변경됨
   /* @AssertTrue
    public boolean isReqYearMonthValidation(){
        this.reqYearMonth = getReqYearMonth()+"01";
        try{
            LocalDate localDate = LocalDate.parse(this.reqYearMonth, DateTimeFormatter.ofPattern("yyyyMM"));
        } catch(Exception e) {
            return false;
        }
        return true;
    }*/


//    @Valid
//    private List<Car> cars;
//
//
//
//    public List<Car> getCars() {
//        return cars;
//    }
//
//    public void setCars(List<Car> cars) {
//        this.cars = cars;
//    }

    public String getReqYearMonth() {
        return reqYearMonth;
    }

    public void setReqYearMonth(String reqYearMonth) {
        this.reqYearMonth = reqYearMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqYearMonth='" + reqYearMonth + '\'' +
//                ", cars=" + cars +
                '}';
    }
}
