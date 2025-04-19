package vti.dtn.department_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vti.dtn.department_service.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
    // Custom query methods can be defined here if needed
}
