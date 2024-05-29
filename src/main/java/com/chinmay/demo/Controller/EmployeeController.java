package com.chinmay.demo.Controller;

import com.chinmay.demo.EmployeeApiApplication;
import com.chinmay.demo.Repository.EmployeeRepository;
import com.chinmay.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees (){
        List<Employee> empList = new ArrayList<>();
        employeeRepository.findAll().forEach(empList::add);

        return  new ResponseEntity<List<Employee>>(empList, HttpStatus.CREATED);
    }

    @PostMapping("/employees")
    public String createNewEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return "Employee Created in Employee Database";
    }

    @GetMapping("/employees/{empid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid){
        Optional<Employee> emp = employeeRepository.findById(empid);
        if(emp.isPresent()){
            return  new ResponseEntity<Employee>(emp.get(), HttpStatus.FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/employees/{empid}")
    public String updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee){
        Optional<Employee> emp = employeeRepository.findById(empid);
        if(emp.isPresent()){
            Employee existEmp = emp.get();
            existEmp.setEmp_age(employee.getEmp_age());
            existEmp.setEmp_city(employee.getEmp_city());
            existEmp.setEmp_name(employee.getEmp_name());
            existEmp.setEmp_city(employee.getEmp_city());
            employeeRepository.save(existEmp);
            return  "User updated successfully!";
        }else {
            return "User not exist";
        }
    }


    @DeleteMapping("/employees/{empid}")
    public String deleteEmployeeById(@PathVariable long empid){
        employeeRepository.deleteById(empid);
        return "Employee Deleted Successfully";
    }
}
