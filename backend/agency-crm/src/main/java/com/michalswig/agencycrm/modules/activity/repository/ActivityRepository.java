package com.michalswig.agencycrm.modules.activity.repository;

import com.michalswig.agencycrm.modules.activity.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}

