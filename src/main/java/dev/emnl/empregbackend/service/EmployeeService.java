package dev.emnl.empregbackend.service;


import dev.emnl.empregbackend.dto.EmployeeDto;
import dev.emnl.empregbackend.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<EmployeeDto> getEmployees();

    public Optional<Employee> getEmployee(Integer empId);

    public Employee createEmployee(EmployeeDto employeeDto);

    public Boolean checkEmployeeExistence(Integer empId);
}