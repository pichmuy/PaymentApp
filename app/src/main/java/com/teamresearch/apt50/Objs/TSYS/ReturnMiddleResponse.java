package com.teamresearch.apt50.Objs.TSYS;

/**
 * Created by Me on 2/13/2018.
 */

public class ReturnMiddleResponse {
    public returnResponse ReturnResponse;

    public class returnResponse
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
        public String returnedAmount;
        public String cardType;
        public String maskedCardNumber;
        public String customerReceipt;
        public String merchantReceipt;
        public String expirationDate;
    }
    public String getstatus()                   { return this.ReturnResponse.status;}
    public String getresponseCode()             { return this.ReturnResponse.responseCode;}
    public String getresponseMessage()          { return this.ReturnResponse.responseMessage;}
    public String getauthCode()                 { return this.ReturnResponse.authCode;}
    public String gethostReferenceNumber()      { return this.ReturnResponse.hostReferenceNumber;}
    public String gettaskID()                   { return this.ReturnResponse.taskID;}
    public String gettransactionID()            { return this.ReturnResponse.transactionID;}
    public String gettransactionTimestamp()     { return this.ReturnResponse.transactionTimestamp;}
    public String gettransactionAmount()        { return this.ReturnResponse.transactionAmount;}
    public String getreturnedAmount()
    {
        this.ReturnResponse.returnedAmount = (this.ReturnResponse.returnedAmount == null ? "0.00" : this.ReturnResponse.returnedAmount);
        return this.ReturnResponse.returnedAmount;

    }
    public String getcardType()                 { return this.ReturnResponse.cardType;}
    public String getmaskedCardNumber()         { return this.ReturnResponse.maskedCardNumber;}
    public String getcustomerReceipt()          { return this.ReturnResponse.customerReceipt;}
    public String getmerchantReceipt()          { return this.ReturnResponse.merchantReceipt;}
    public String getexpirationDate()           { return this.ReturnResponse.expirationDate;}

}
