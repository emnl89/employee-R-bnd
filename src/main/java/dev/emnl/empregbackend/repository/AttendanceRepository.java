package dev.emnl.empregbackend.repository;

import dev.emnl.empregbackend.model.Attendance;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends MongoRepository<Attendance, ObjectId> {
    public List<Attendance> findAllByDate(Date date);

    public List<Integer> findEmpIdByDate(Date day);
}