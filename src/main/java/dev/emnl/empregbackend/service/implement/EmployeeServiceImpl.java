package dev.emnl.empregbackend.service.implement;

import dev.emnl.empregbackend.dto.EmployeeDto;
import dev.emnl.empregbackend.model.Attendance;
import dev.emnl.empregbackend.model.Employee;
import dev.emnl.empregbackend.repository.EmployeeRepository;
import dev.emnl.empregbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for(Employee employee: employees){
            EmployeeDto employeeDto = mapToDto(employee);
            employeeDtos.add(employeeDto);
        }

        return employeeDtos;
    }

    @Override
    public Optional<Employee> getEmployee(Integer empId) {
        Employee employee =  employeeRepository.findByEmpId(empId).get();
        List<Attendance> attendances = employee.getAttendances();
        insertionSort(attendances, attendances.size());
        employee.setAttendances(attendances);

        return Optional.of(employeeRepository.save(employee));
    }

    private void insertionSort(List<Attendance> attendances, int size) {
        if(size <= 1){
            return;
        }
        insertionSort(attendances, size - 1);

        Attendance last = attendances.get(size - 1);
        int j = size - 2;

        while(j >= 0 && attendances.get(j).getDate().after(last.getDate())){
            Collections.swap(attendances, j + 1, j);
            j--;
        }
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        return employeeRepository.save(employee);
    }

    @Override
    public Boolean checkEmployeeExistence(Integer empId) {
        return employeeRepository.existsByEmpId(empId);
    }

    private Employee mapToEntity(EmployeeDto employeeDto){
        Employee employee = new Employee();

        employee.setEmpId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setWageRate(employeeDto.getWageRate());
        employee.setOvertimeRate(employeeDto.getOvertimeRate());
        employee.setDueAmount(employeeDto.getDueAmount());

        return employee;
    }

    private EmployeeDto mapToDto(Employee employee) {

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getEmpId());
        employeeDto.setName(employee.getName());
        employeeDto.setWageRate(employee.getWageRate());
        employeeDto.setOvertimeRate(employee.getOvertimeRate());
        employeeDto.setDueAmount(employee.getDueAmount());

        return employeeDto;
    }
}