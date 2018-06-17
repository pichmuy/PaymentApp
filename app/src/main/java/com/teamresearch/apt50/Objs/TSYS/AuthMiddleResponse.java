package com.teamresearch.apt50.Objs.TSYS;

/**
 * Created by Me on 2/13/2018.
 */

public class AuthMiddleResponse {
    public authResponse AuthResponse;

    public class authResponse
    {
        public String status;
        public String responseCode;
        public String responseMessage;
        public String authCode;
        public String hostReferenceNumber;
        public String taskID;
        public String transactionID;
        public String transactionTimestamp;
        public String transactionAmount;
        public String processedAmount;
        public String totalAmount;
        public String tip;
        public String salesTax;
        public String addressVerificationCode;
        public String cvvVerificationCode;
        public String cardType;
        public String maskedCardNumber;
        public String token;
        public String expirationDate;
        public String commercialCard;
        public String transactionIntegrityClassification;
        public String firstName;
        public String lastName;
        public String customerReceipt;
        public String merchantReceipt;
        public String splitTenderID;


    }


    public String getstatus()                       { return this.AuthResponse.status;}
    public String getresponseCode()                 { return this.AuthResponse.responseCode;}
    public String getresponseMessage()              { return this.AuthResponse.responseMessage;}
    public String getauthCode()                     { return this.AuthResponse.authCode;}
    public String gethostReferenceNumber()          { return this.AuthResponse.hostReferenceNumber;}
    public String gettaskID()                       { return this.AuthResponse.taskID;}
    public String gettransactionID()                { return this.AuthResponse.transactionID;}
    public String gettransactionTimestamp()         { return this.AuthResponse.transactionTimestamp;}
    public String gettransactionAmount()            { return this.AuthResponse.transactionAmount;}
    public String getprocessedAmount()              { return this.AuthResponse.processedAmount;}
    public String gettotalAmount() {
        this.AuthResponse.totalAmount = (this.AuthResponse.totalAmount == null ? "0.00" : this.AuthResponse.totalAmount);
        return this.AuthResponse.totalAmount;
    }
    public String gettip()
    {
        this.AuthResponse.tip=(this.AuthResponse.tip==null?"0.00":this.AuthResponse.tip);
        return this.AuthResponse.tip;
    }
    public String getaddressVerificationCode()      { return this.AuthResponse.addressVerificationCode;}
    public String getcvvVerificationCode()          { return this.AuthResponse.cvvVerificationCode;}
    public String getcardType()                     { return this.AuthResponse.cardType;}
    public String getmaskedCardNumber()             { return this.AuthResponse.maskedCardNumber;}
    public String getcommercialCard()               { return this.AuthResponse.commercialCard;}
    public String getfirstName()                    { return this.AuthResponse.firstName;}
    public String getlastName()                     { return this.AuthResponse.lastName;}
    public String getcustomerReceipt()              { return this.AuthResponse.customerReceipt;}
    public String getmerchantReceipt()              { return this.AuthResponse.merchantReceipt;}
    public String getexpirationDate()               { return this.AuthResponse.expirationDate;}
}
