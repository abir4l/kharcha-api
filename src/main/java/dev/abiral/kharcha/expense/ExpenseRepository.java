package dev.abiral.kharcha.expense;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    /** Loads splits in the same query, avoiding N+1 when mapping the list. */
    @EntityGraph(attributePaths = "splits")
    List<Expense> findByGroupIdOrderByExpenseDateDescIdDesc(Long groupId);

    Optional<Expense> findByIdempotencyKey(String idempotencyKey);
}
