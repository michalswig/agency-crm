package com.michalswig.agencycrm.modules.crm.repository;

import com.michalswig.agencycrm.modules.crm.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
