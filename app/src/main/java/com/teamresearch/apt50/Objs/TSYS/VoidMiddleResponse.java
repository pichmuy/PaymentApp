package com.teamresearch.apt50.Objs.TSYS;

/**
 * Created by Me on 2/14/2018.
 */

public class VoidMiddleResponse {
    public voidresponse VoidResponse;

    public class voidresponse
    {
        public String status;
        public String responseCode;
        public String responseMessage;
        public String authCode;
        public String hostReferenceNumber;
        public String taskID;
        public String transactionID;
        public String transactionTimestamp;
        public String orderNumber;
        public String externalReferenceID;
        public String voidedAmount;
        public String cardType;
        public String maskedCardNumber;
        public String customerReceipt;
        public String merchantReceipt;

    }

    public String getstatus()                   { return this.VoidResponse.status;}
    public String getresponseCode()             { return this.VoidResponse.responseCode;}
    public String getresponseMessage()          { return this.VoidResponse.responseMessage;}
    public String getauthCode()                 { return this.VoidResponse.authCode;}
    public String gethostReferenceNumber()      { return this.VoidResponse.hostReferenceNumber;}
    public String gettaskID()                   { return this.VoidResponse.taskID;}
    public String gettransactionID()            { return this.VoidResponse.transactionID;}
    public String gettransactionTimestamp()     { return this.VoidResponse.transactionTimestamp;}
    public String getorderNumber()              { return this.VoidResponse.orderNumber;}
    public String getexternalReferenceID()      { return this.VoidResponse.externalReferenceID;}
    public String getvoidedAmount()
    {
        this.VoidResponse.voidedAmount=(this.VoidResponse.voidedAmount==null?"0":this.VoidResponse.voidedAmount);
        return this.VoidResponse.voidedAmount;
    }
    public String getcardType()                 { return this.VoidResponse.cardType;}
    public String getmaskedCardNumber()         { return this.VoidResponse.maskedCardNumber;}
    public String getcustomerReceipt()          { return this.VoidResponse.customerReceipt;}
    public String getmerchantReceipt()          { return this.VoidResponse.merchantReceipt;}
}
