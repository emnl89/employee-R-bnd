package dev.emnl.empregbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceDto {
    private Integer id;
    private String name;
    private Integer work;
    private Integer overtime;
    private Integer advance;
}
