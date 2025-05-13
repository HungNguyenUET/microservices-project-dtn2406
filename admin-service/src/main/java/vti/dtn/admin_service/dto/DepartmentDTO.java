package vti.dtn.admin_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DepartmentDTO {
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
}
