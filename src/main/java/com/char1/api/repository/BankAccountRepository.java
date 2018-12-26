package com.char1.api.repository;

import com.char1.api.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    BankAccount findById(int id);
}
