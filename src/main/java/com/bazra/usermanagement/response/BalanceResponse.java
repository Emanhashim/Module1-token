package com.bazra.usermanagement.response;

import java.math.BigDecimal;
import java.util.Date;

public class BalanceResponse {
    private BigDecimal amount;
    private String message;
    
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public BalanceResponse( BigDecimal amount, String message) {
        
        
        this.amount = amount;
        this.message= message;
       }
    public BalanceResponse(String message) {
        this.message=message;
    }
}
