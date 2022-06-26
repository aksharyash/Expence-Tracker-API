package com.akshar.expensetrackerapi.Controller;

import com.akshar.expensetrackerapi.Entity.Expense;
import com.akshar.expensetrackerapi.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping(value = "/expenses")
    public List<Expense> getAllExpenses(Pageable pageable){
        return expenseService.getAllExpenses(pageable).toList();
    }

    @GetMapping(value = "/expenses/{id}")
    public Expense getExpenseById(@PathVariable Long id){
        return expenseService.getExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/expenses")
    public void deleteExpenseById(@RequestParam Long id){
        expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
        return expenseService.saveExpenseDetails(expense);
    }

    @PutMapping(value = "/expenses/{id}")
    public Expense updateExpenseDetails(@PathVariable Long id, @RequestBody Expense expense){
        return expenseService.updateExpenseDetails(id, expense);
    }

    @GetMapping(value = "/expenses/category")
    public List<Expense> getExpenseByCategory(@RequestParam String category, Pageable pageable){
        return expenseService.readByCategory(category,pageable);
    }
}
