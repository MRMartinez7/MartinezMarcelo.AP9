package com.MindHub.homebanking.dto;

import com.MindHub.homebanking.models.ClientLoan;


public class ClientLoanDTO {
    private Long id;
    private Long  loanId;
    private String name;
    private double amount ;
    private Integer payments;


    public ClientLoanDTO(ClientLoan clientLoan) {
        id = clientLoan.getId();
        loanId = clientLoan.getLoan().getId();
        name = clientLoan.getLoan().getName();
        amount = clientLoan.getAmount();
        payments = clientLoan.getPayments();
    }

    public Long getId() {
        return id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public String getName() {
        return name;
    }


}

