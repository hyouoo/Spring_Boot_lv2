package com.sparta.lv2.repository;

import com.sparta.lv2.entity.Book;
import com.sparta.lv2.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findAllByBorrowingIsTrueOrderByBorrowDateAsc();


}
