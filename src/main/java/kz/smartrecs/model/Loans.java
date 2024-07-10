package kz.smartrecs.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="loans")
@Data
@NoArgsConstructor
public class Loans {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "loan_number")
    private Integer loanNumber;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name="start_dt")
    private Date startDt;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private int totalLoan;

    @Column(name = "amount_paid")
    private int amountPaid;

    @Column(name = "outstanding_amount")
    private int outstandingAmount;

    @Column(name = "create_dt")
    private String createDt;
}
