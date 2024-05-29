package com.chinmay.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empid;

    @Column(name = "emp_name")
    private String emp_name;

    @Column(name = "emp_salary")
    private Float emp_salary;

    @Column(name = "emp_age")
    private int emp_age;

    @Column(name = "emp_city")
    private String emp_city;

    public Employee() {

    }

    public Employee(Long empid, String emp_name, Float emp_salary, int emp_age, String emp_city) {
        this.empid = empid;
        this.emp_name = emp_name;
        this.emp_salary = emp_salary;
        this.emp_age = emp_age;
        this.emp_city = emp_city;
    }
}
