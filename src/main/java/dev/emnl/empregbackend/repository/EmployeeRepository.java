package dev.emnl.empregbackend.repository;

import dev.emnl.empregbackend.model.Employee;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {
    public Optional<Employee> findByEmpId(Integer empId);

    public String findNameByEmpId(Integer empId);

    public Boolean existsByEmpId(Integer empId);
}
