package dev.abiral.kharcha.expense;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.abiral.kharcha.expense.ExpenseDtos.CreateExpenseRequest;
import dev.abiral.kharcha.expense.ExpenseDtos.ExpenseResponse;
import dev.abiral.kharcha.group.GroupService;
import dev.abiral.kharcha.member.MemberRepository;

@Service
@Transactional(readOnly = true)
public class ExpenseService {

    private final ExpenseRepository expenses;
    private final MemberRepository members;
    private final GroupService groupService;

    public ExpenseService(ExpenseRepository expenses, MemberRepository members, GroupService groupService) {
        this.expenses = expenses;
        this.members = members;
        this.groupService = groupService;
    }

    public List<ExpenseResponse> list(Long groupId) {
        groupService.require(groupId);
        return expenses.findByGroupIdOrderByExpenseDateDescIdDesc(groupId).stream()
                .map(ExpenseResponse::from)
                .toList();
    }

    /**
     * YOUR TURN (core logic). Suggested steps:
     *
     * 1. Idempotency: if req.idempotencyKey() is present and an expense with that key exists,
     *    return it instead of creating a new one.
     * 2. Load the group (groupService.require) and the payer. The payer must belong to this group,
     *    otherwise throw IllegalArgumentException (becomes a 400).
     * 3. Load every member in splitAmongMemberIds. All must exist and belong to this group.
     *    Reject duplicate ids.
     * 4. Split the amount equally using BigDecimal. Pick a scale per currency
     *    (JPY = 0 decimals, most others = 2; see java.util.Currency#getDefaultFractionDigits).
     *    Divide with RoundingMode.DOWN, then give the leftover cents to the first member(s),
     *    so the shares always add up exactly to the amount. Never use double for money.
     * 5. new Expense(...), call addSplit(...) for each share, save, and return ExpenseResponse.from(saved).
     *
     * Interview angle: be ready to explain why the leftover-cent handling and the idempotency key matter.
     */
    @Transactional
    public ExpenseResponse create(Long groupId, CreateExpenseRequest req) {
        throw new UnsupportedOperationException("TODO: implement ExpenseService.create");
    }
}
