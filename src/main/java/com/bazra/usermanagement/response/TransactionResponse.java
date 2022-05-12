package com.bazra.usermanagement.response;

import java.util.List;

import com.bazra.usermanagement.model.*;
public class TransactionResponse {
    private List<Transaction> transaction;
    private String message;
    public List<Transaction> getTransaction() {
        return transaction;
    }
    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public TransactionResponse(String message) {
        this.message= message;
        
    }

}
