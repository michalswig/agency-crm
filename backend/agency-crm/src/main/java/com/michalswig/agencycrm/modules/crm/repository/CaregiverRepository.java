package com.michalswig.agencycrm.modules.crm.repository;

import com.michalswig.agencycrm.modules.crm.domain.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaregiverRepository extends JpaRepository<Caregiver, Long> {
}

