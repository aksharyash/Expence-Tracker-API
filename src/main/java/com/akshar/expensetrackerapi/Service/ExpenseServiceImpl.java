package com.akshar.expensetrackerapi.Service;

import com.akshar.expensetrackerapi.Entity.Expense;
import com.akshar.expensetrackerapi.Exceptions.ResourceNotFoundException;
import com.akshar.expensetrackerapi.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Page<Expense> getAllExpenses(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent())
        {
            return expense.get();
        }
        else throw new ResourceNotFoundException("Expense is not found for the id : " + id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id, Expense expense) {
        Expense existanceExpense = getExpenseById(id);
        existanceExpense.setName(expense.getName() != null ? expense.getName() : existanceExpense.getName());
        existanceExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existanceExpense.getDescription());
        existanceExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existanceExpense.getCategory());
        existanceExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existanceExpense.getAmount());
        existanceExpense.setDate(expense.getDate() != null ? expense.getDate() : existanceExpense.getDate());
        return expenseRepository.save(existanceExpense);
    }

    @Override
    public List<Expense> readByCategory(String category, Pageable pageable) {
        return expenseRepository.findByCategory(category,pageable).toList();
    }
}
