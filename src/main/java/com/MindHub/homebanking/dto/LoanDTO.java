package com.MindHub.homebanking.dto;

import com.MindHub.homebanking.models.Loan;

import javax.persistence.ElementCollection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LoanDTO {
    private Long id;
    private String name;
    private double maxAmount;
    @ElementCollection
    private List<Integer> payments;
    private Set<ClientLoanDTO> loans;

    public LoanDTO(Loan loan) {
        id = loan.getId();
        name = loan.getName();
        maxAmount = loan.getMaxAmount();
        payments  = loan.getPayments();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public Set<ClientLoanDTO> getLoans() {
        return loans;
    }

}
