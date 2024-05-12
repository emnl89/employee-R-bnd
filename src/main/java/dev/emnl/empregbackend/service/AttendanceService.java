package dev.emnl.empregbackend.service;

import dev.emnl.empregbackend.dto.AttendanceDto;
import dev.emnl.empregbackend.model.Attendance;

import java.util.Date;
import java.util.List;

public interface AttendanceService {
    public List<AttendanceDto> fetchAttendance(Date date);

    public Attendance createAttendance(Attendance attendance);

    public List<Integer> getEmployeeIds(Date day);
}