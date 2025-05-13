package vti.dtn.admin_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import vti.dtn.admin_service.dto.AccountDTO;
import vti.dtn.admin_service.dto.DepartmentDTO;
import vti.dtn.admin_service.feignclient.DepartmentFeignClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {
    @Value("${account-service.url}")
    private String accountUrl;

    private final RestClient restClient;
    private final DepartmentFeignClient departmentFeignClient;

    public List<AccountDTO> getAccounts() {
        return restClient.get()
                .uri(accountUrl)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public List<DepartmentDTO> getDepartments() {
        return departmentFeignClient.getAllDepartments();
    }

}
