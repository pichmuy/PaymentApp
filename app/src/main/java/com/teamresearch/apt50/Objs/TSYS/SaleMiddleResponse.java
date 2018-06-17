package com.teamresearch.apt50.Objs.TSYS;

/**
 * Created by Me on 2/14/2018.
 */

public class SaleMiddleResponse {
    public saleResponse SaleResponse;

    public class saleResponse
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
        public String addressVerificationCode;
        public String cvvVerificationCode;
        public String cardType;
        public String maskedCardNumber;
        public String commercialCard;
        public String firstName;
        public String lastName;
        public String customerReceipt;
        public String merchantReceipt;
        public String expirationDate;
    }
    public String getstatus()                           { return this.SaleResponse.status;}
    public String getresponseCode()                     { return this.SaleResponse.responseCode;}
    public String getresponseMessage()                  { return this.SaleResponse.responseMessage;}
    public String getauthCode()                         { return this.SaleResponse.authCode;}
    public String gethostReferenceNumber()              { return this.SaleResponse.hostReferenceNumber;}
    public String gettaskID()                           { return this.SaleResponse.taskID;}
    public String gettransactionID()                    { return this.SaleResponse.transactionID;}
    public String gettransactionTimestamp()             { return this.SaleResponse.transactionTimestamp;}
    public String gettransactionAmount()                { return this.SaleResponse.transactionAmount;}
    public String getprocessedAmount()                  { return this.SaleResponse.processedAmount;}
    public String gettotalAmount()
    {
        this.SaleResponse.totalAmount = (this.SaleResponse.totalAmount == null ? "0.00" : this.SaleResponse.totalAmount);
        return this.SaleResponse.totalAmount;

    }
    public String gettip()
    {
        this.SaleResponse.tip = (this.SaleResponse.tip == null ? "0.00" : this.SaleResponse.tip);
        return this.SaleResponse.tip;
    }
    public String getaddressVerificationCode()          { return this.SaleResponse.addressVerificationCode;}
    public String getcvvVerificationCode()              { return this.SaleResponse.cvvVerificationCode;}
    public String getcardType()                         { return this.SaleResponse.cardType;}
    public String getmaskedCardNumber()                 { return this.SaleResponse.maskedCardNumber;}
    public String getcommercialCard()                   { return this.SaleResponse.commercialCard;}
    public String getfirstName()                        { return this.SaleResponse.firstName;}
    public String getlastName()                         { return this.SaleResponse.lastName;}
    public String getcustomerReceipt()                  { return this.SaleResponse.customerReceipt;}
    public String getmerchantReceipt()                  { return this.SaleResponse.merchantReceipt;}
    public String getexpirationDate()                   { return this.SaleResponse.expirationDate;}
}
