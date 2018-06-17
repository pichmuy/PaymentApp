package com.teamresearch.apt50.Objs;

public  class ReturnResponse {

    public Returnresponse ReturnResponse;

    public class Returnresponse {
        public String CardType;
        public String Error;
        public String ErrorMsg;
        public String Status;
        public String ExpirationDate;
        public String MaskedCardNumber;
        public double ReturnAmount;
        public String transactionID;
        public String TransactionTimeStamp;
        public String Command;
        public String Ref;

        public Returnresponse(String cardType,String error,String errorMsg,String ref,String expirationDate,String maskedCardNumber,
                              double returnAmount,String transactionID,String transactionTimeStamp){
            this.CardType=cardType;
            this.Error=error;
            this.ErrorMsg=errorMsg;
            this.ExpirationDate=expirationDate;
            this.MaskedCardNumber=maskedCardNumber;
            this.ReturnAmount=returnAmount;
            this.transactionID=transactionID;
            this.TransactionTimeStamp=transactionTimeStamp;
            this.Ref=ref;
        }

        public Returnresponse(String error,String errorMsg){

            this.Error=error;
            this.ErrorMsg=errorMsg;

        }
        public Returnresponse(String status)
        {
            this.Status=status;
        }
        public Returnresponse(String error,String errorMsg,String command,String ref,double totalAmount)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;
            this.Command=command;
            this.Ref=ref;
            this.ReturnAmount=totalAmount;
        }
    }
    public ReturnResponse(String cardType,String error,String errorMsg,String ref,String expirationDate,String maskedCardNumber,
                          double returnAmount,String transactionID,String transactionTimeStamp)
    {
        Returnresponse returnresponse=new Returnresponse(cardType,error,errorMsg,ref,expirationDate,maskedCardNumber,
                returnAmount, transactionID,transactionTimeStamp);
        this.ReturnResponse=returnresponse;

    }

    public ReturnResponse(String error,String errorMsg)
    {
        Returnresponse returnresponse=new Returnresponse(error,errorMsg);
        this.ReturnResponse=returnresponse;
    }
    public ReturnResponse()
    {
        Returnresponse returnresponse=new Returnresponse("Initiated");
        this.ReturnResponse=returnresponse;
    }
    public ReturnResponse(String error,String errorMsg,String command,String ref,double returnAmount)
    {
        Returnresponse returnresponse=new Returnresponse(error,errorMsg,command,ref,returnAmount);
        this.ReturnResponse=returnresponse;
    }
}
