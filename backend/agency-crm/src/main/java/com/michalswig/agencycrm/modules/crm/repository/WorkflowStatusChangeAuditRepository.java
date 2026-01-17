package com.michalswig.agencycrm.modules.crm.repository;

import com.michalswig.agencycrm.modules.crm.domain.WorkflowStatusChangeAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowStatusChangeAuditRepository extends JpaRepository<WorkflowStatusChangeAudit, Long> {
}

