package com.michalswig.agencycrm.modules.crm.repository;

import com.michalswig.agencycrm.modules.crm.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
