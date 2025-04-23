package com.jbtech.chit_fund.repository;

import com.jbtech.chit_fund.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}