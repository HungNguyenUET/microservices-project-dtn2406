package vti.dtn.admin_service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import vti.dtn.admin_service.dto.DepartmentDTO;

import java.util.List;

@FeignClient(name = "department-service", path = "${department-service.path}")
public interface DepartmentFeignClient {

    @GetMapping
    List<DepartmentDTO> getAllDepartments();

}
