package kz.smartrecs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(schema = "public", name = "account_transactions")
@Data
@NoArgsConstructor
public class AccountTransactions {

    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name="account_number")
    private Long accountNumber;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name="transaction_dt")
    private Date transactionDt;

    @Column(name = "transaction_summary")
    private String transactionSummary;

    @Column(name="transaction_type")
    private String transactionType;

    @Column(name = "transaction_amt")
    private int transactionAmt;

    @Column(name = "closing_balance")
    private int closingBalance;
}
