package com.teamresearch.apt50.Objs;

public class VoidResponse {

    public voidResponse VoidResponse;
    public class voidResponse {
        public String Error;
        public String ErrorMsg;
        public String VoidedAmount;
        public String TransactionID;
        public String TransactionTimeStamp;

        public voidResponse(String error,String errorMsg,String voidedAmount,String transactionID,String transactionTimeStamp){

            this.Error=error;
            this.ErrorMsg=errorMsg;
            this.VoidedAmount=voidedAmount;
            this.TransactionID=transactionID;
            this.TransactionTimeStamp=transactionTimeStamp;
        }

        public voidResponse(String error,String errorMsg){

            this.Error=error;
            this.ErrorMsg=errorMsg;

        }
    }

    public VoidResponse(String error,String errormsg,String voidedAmount,String transactionID,String transactionTimeStamp){
        voidResponse response=new voidResponse(error,errormsg,voidedAmount,transactionID,transactionTimeStamp);
        this.VoidResponse=response;
    }

    public VoidResponse(String error,String errormsg){
        voidResponse response=new voidResponse(error,errormsg);
        this.VoidResponse=response;
    }

}
