package dev.abiral.kharcha.balance;

import java.math.BigDecimal;
import java.util.List;

public final class BalanceDtos {

    private BalanceDtos() {
    }

    /** Positive net = the group owes this member. Negative = this member owes the group. */
    public record MemberBalance(Long memberId, String memberName, String currency, BigDecimal net) {
    }

    /** One suggested payment produced by the "simplify debts" step. */
    public record SuggestedTransfer(Long fromMemberId, Long toMemberId, String currency, BigDecimal amount) {
    }

    public record BalancesResponse(List<MemberBalance> balances, List<SuggestedTransfer> suggestedTransfers) {
    }
}
