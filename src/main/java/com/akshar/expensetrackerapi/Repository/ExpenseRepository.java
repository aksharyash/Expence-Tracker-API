package com.akshar.expensetrackerapi.Repository;

import com.akshar.expensetrackerapi.Entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>  {

    @Override
    Page<Expense> findAll(Pageable pageable);

    Page<Expense> findByCategory(String category, Pageable pageable);
}
