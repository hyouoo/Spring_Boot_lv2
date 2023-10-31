package com.sparta.lv2.repository;

import com.sparta.lv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsBySsn(String ssn);
    boolean existsByPhone(String phone);
}
