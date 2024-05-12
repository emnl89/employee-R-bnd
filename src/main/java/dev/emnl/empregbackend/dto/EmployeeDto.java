package dev.emnl.empregbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Integer id;
    private String name;
    private Integer wageRate;
    private Integer overtimeRate;
    private Integer dueAmount;
}