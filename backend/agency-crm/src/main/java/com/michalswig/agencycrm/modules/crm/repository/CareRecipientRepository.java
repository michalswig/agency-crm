package com.michalswig.agencycrm.modules.crm.repository;

import com.michalswig.agencycrm.modules.crm.domain.CareRecipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareRecipientRepository extends JpaRepository<CareRecipient, Long> {
}
