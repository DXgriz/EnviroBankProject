package com.enviro.assessments.grad001.vuyaningcobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.enviro.assessments.grad001.vuyaningcobo.entity.AccountDetails;

public interface AccountDetailsInterface extends JpaRepository<AccountDetails,Integer> {
    
}
