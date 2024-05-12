package dev.emnl.empregbackend.service.implement;

import dev.emnl.empregbackend.dto.AttendanceDto;
import dev.emnl.empregbackend.model.Attendance;
import dev.emnl.empregbackend.model.Employee;
import dev.emnl.empregbackend.repository.AttendanceRepository;
import dev.emnl.empregbackend.repository.EmployeeRepository;
import dev.emnl.empregbackend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<AttendanceDto> fetchAttendance(Date date) {
        List<Attendance> attendances = attendanceRepository.findAllByDate(date);
        return mapToDto(attendances);
    }


    @Override
    public Attendance createAttendance(Attendance tempAttendance) {

        Attendance attendance = mapToEntity(tempAttendance);

        attendanceRepository.save(attendance);

        mapToEmployee(attendance, attendance.getEmpId());

        return attendance;
    }

    @Override
    public List<Integer> getEmployeeIds(Date day) {

        List<Integer> attIds = attendanceRepository.findEmpIdByDate(day);

        return findRemainingIds(attIds);
    }

    private Attendance mapToEntity(Attendance tempAttendance) {
        Attendance attendance = new Attendance();

        attendance.setEmpId(tempAttendance.getEmpId());
        attendance.setDate(tempAttendance.getDate());
        attendance.setWork(tempAttendance.getWork());
        attendance.setOvertime(tempAttendance.getOvertime());
        attendance.setAdvance(tempAttendance.getAdvance());

        return attendance;
    }

    private List<AttendanceDto> mapToDto(List<Attendance> attendances){

        List<AttendanceDto> attendanceDtos = new ArrayList<>();

        for (Attendance attendance : attendances) {

            AttendanceDto attendanceDto = new AttendanceDto();

            Integer empId = attendance.getEmpId();
            attendanceDto.setId(empId);

            String name = employeeRepository.findNameByEmpId(empId);
            attendanceDto.setName(name);

            attendanceDto.setWork(attendance.getWork());
            attendanceDto.setOvertime(attendance.getOvertime());
            attendanceDto.setAdvance(attendance.getAdvance());

            attendanceDtos.add(attendanceDto);
        }
        return attendanceDtos;
    }

    private void mapToEmployee(Attendance attendance, Integer empId) {
        Employee employee = employeeRepository.findByEmpId(empId).get();
        List<Attendance> attendances = employee.getAttendances();
        attendances.add(attendance);
        employee.setAttendances(attendances);
        employeeRepository.save(employee);
    }

    private List<Integer> findRemainingIds(List<Integer> attIds) {

        List<Employee> employees = employeeRepository.findAll();

        List<Integer> empIds = new ArrayList<>();
        for(Employee emp: employees){
            empIds.add(emp.getEmpId());
        }

        List<Integer> result = new ArrayList<>();
        for (Integer empId : empIds) {
            if (!(attIds.contains(empId))) {
                result.add(empId);
            }
        }
        return result;
    }
}