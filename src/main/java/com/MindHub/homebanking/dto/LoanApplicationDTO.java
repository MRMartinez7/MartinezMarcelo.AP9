package com.MindHub.homebanking.dto;

import com.MindHub.homebanking.models.Account;
import com.MindHub.homebanking.models.ClientLoan;
import com.MindHub.homebanking.models.Loan;

public class LoanApplicationDTO {
    private long loanId;
    private String toAccountNumber;
    private double amount;
    private Integer payments;

    public LoanApplicationDTO() {
    }

    public LoanApplicationDTO(ClientLoan clientLoan, String toAccountNumber) {
        this.loanId = clientLoan.getLoan().getId();
        this.toAccountNumber = toAccountNumber;
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
    }

    public long getLoanId() {
        return loanId;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }
}
