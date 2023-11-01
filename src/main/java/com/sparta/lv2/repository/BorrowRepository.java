package com.sparta.lv2.repository;

import com.sparta.lv2.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findAllByOrderByBorrowDateAsc();

    List<Borrow> findAllByBorrowingIsTrueOrderByBorrowDateAsc();

    List<Borrow> findAllByUserIdOrderByBorrowDateAsc(Long userId);

    List<Borrow> findAllByBorrowingIsTrueAndUserIdOrderByBorrowDateAsc(Long userId);
}
