package dev.emnl.empregbackend.controller;


import dev.emnl.empregbackend.dto.AttendanceDto;
import dev.emnl.empregbackend.model.Attendance;
import dev.emnl.empregbackend.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v2/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/create")
    public ResponseEntity<Attendance> createAttendance(@RequestBody @Valid Attendance attendance){

        return new ResponseEntity<Attendance>(
                attendanceService.createAttendance(attendance), HttpStatus.OK
        );
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<AtCrtendanceDto>> fetchAttendance(@PathVariable String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date day = format.parse(date);
        return new ResponseEntity<List<AttendanceDto>>(
                attendanceService.fetchAttendance(day), HttpStatus.OK
        );
    }

    @GetMapping("/getIds/{date}")
    public ResponseEntity<List<Integer>> getEmployeeIds(@PathVariable String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date day = format.parse(date);
        return new ResponseEntity<List<Integer>>(
                attendanceService.getEmployeeIds(day), HttpStatus.OK
        );
    }
}
