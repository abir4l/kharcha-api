package dev.abiral.kharcha.balance;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.abiral.kharcha.balance.BalanceDtos.BalancesResponse;

@Service
@Transactional(readOnly = true)
public class BalanceService {

    /**
     * YOUR TURN (core logic + the interview-worthy algorithm).
     *
     * Part 1, net balances (per member, per currency; no FX conversion in v1):
     *   net = (total this member paid) - (total of this member's split shares)
     *   Settlements later: from-member's net goes up, to-member's net goes down.
     *   Tip: a JPQL query with SUM + GROUP BY, or load expenses with splits and fold in Java.
     *
     * Part 2, simplify debts (per currency):
     *   Put creditors (net > 0) and debtors (net < 0) into two max-heaps by absolute amount.
     *   Repeatedly match the largest debtor with the largest creditor, transfer min(|debt|, credit),
     *   and push back whoever still has a remainder. Produces at most (n - 1) transfers.
     *   Compare with compareTo, never equals, for BigDecimal.
     *
     * Then wire up a BalanceController: GET /api/groups/{groupId}/balances
     */
    public BalancesResponse getBalances(Long groupId) {
        throw new UnsupportedOperationException("TODO: implement BalanceService.getBalances");
    }
}
