package com.pfe.back.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.pfe.back.entities.AccountOperation;



public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {

}

