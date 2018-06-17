package com.teamresearch.apt50.Objs;

public class ReferenceResponse {

    public Referenceresponse ReferenceResponse;

    class Referenceresponse {
        public String CardType;
        public int Error;
        public String ErrorMsg;
        public String ExpirationDate;
        public String MaskedCardNumber;
        public String ReturnAmount;
        public String TransactionID;
        public String TransactionTimeStamp;
     public Referenceresponse(String cardType,int error,String errorMsg,String expirationDate,String maskedCardNumber,
                              String returnAmount,String transactionID,String transactionTimeStamp)
     {
         this.CardType=cardType;
         this.Error=error;
         this.ErrorMsg=errorMsg;
         this.ExpirationDate=expirationDate;
         this.MaskedCardNumber=maskedCardNumber;
         this.ReturnAmount=returnAmount;
         this.TransactionID=transactionID;
         this.TransactionTimeStamp=transactionTimeStamp;
     }
    }
}

