package dev.abiral.kharcha.expense;

import java.math.BigDecimal;

import dev.abiral.kharcha.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/** One member's share of one expense. */
@Entity
@Table(name = "expense_splits")
public class ExpenseSplit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "share_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal shareAmount;

    protected ExpenseSplit() {
        // for JPA
    }

    ExpenseSplit(Expense expense, Member member, BigDecimal shareAmount) {
        this.expense = expense;
        this.member = member;
        this.shareAmount = shareAmount;
    }

    public Long getId() { return id; }
    public Expense getExpense() { return expense; }
    public Member getMember() { return member; }
    public BigDecimal getShareAmount() { return shareAmount; }
}
