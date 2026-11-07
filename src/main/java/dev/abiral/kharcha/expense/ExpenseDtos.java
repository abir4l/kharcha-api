package dev.abiral.kharcha.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public final class ExpenseDtos {

    private ExpenseDtos() {
    }

    /**
     * v1: the amount is split equally among splitAmongMemberIds.
     * Later: add a "splitType" (EQUAL, EXACT, PERCENT) and per-member amounts.
     */
    public record CreateExpenseRequest(
            @NotNull Long paidByMemberId,
            @NotBlank @Size(max = 255) String description,
            @NotNull @Positive BigDecimal amount,
            @NotBlank @Pattern(regexp = "[A-Z]{3}", message = "must be a 3-letter ISO currency code") String currency,
            @NotNull LocalDate expenseDate,
            @NotEmpty List<@NotNull Long> splitAmongMemberIds,
            @Size(max = 64) String idempotencyKey) {
    }

    public record SplitResponse(Long memberId, BigDecimal shareAmount) {
    }

    public record ExpenseResponse(Long id, Long paidByMemberId, String description, BigDecimal amount,
                                  String currency, LocalDate expenseDate, List<SplitResponse> splits) {

        /** Call inside a transaction: reads the lazy splits collection. */
        static ExpenseResponse from(Expense e) {
            List<SplitResponse> splits = e.getSplits().stream()
                    .map(s -> new SplitResponse(s.getMember().getId(), s.getShareAmount()))
                    .toList();
            return new ExpenseResponse(e.getId(), e.getPaidBy().getId(), e.getDescription(), e.getAmount(),
                    e.getCurrency(), e.getExpenseDate(), splits);
        }
    }
}
