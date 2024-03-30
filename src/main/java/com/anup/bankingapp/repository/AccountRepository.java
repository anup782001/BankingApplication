package com.anup.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anup.bankingapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, 
Long>  {

}
