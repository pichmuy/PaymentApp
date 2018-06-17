package com.teamresearch.apt50.Objs;

public class TipAdjustResponse {

    public TipAdjustresponse TipAdjustResponse;
    public class TipAdjustresponse {
        public String Error;
        public String ErrorMsg;
        public String TotalAmount;
        public String Tip;
        public String TransactionID;
        public String TransactionTimeStamp;
        public TipAdjustresponse(String error,String errorMsg,String totalAmount,String tip,String transactionID,
                                 String transactionTimeStamp)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;
            this.TotalAmount=totalAmount;
            this.Tip=tip;
            this.TransactionID=transactionID;
            this.TransactionTimeStamp=transactionTimeStamp;
        }
        public TipAdjustresponse(String error,String errorMsg)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;
        }
    }

    public TipAdjustResponse(String error,String errorMsg,String totalAmount,String tip,String transactionID,
                             String transactionTimeStamp)
    {
        TipAdjustresponse tipAdjustresponse=new TipAdjustresponse(error,errorMsg,totalAmount,tip,transactionID,transactionTimeStamp);
        this.TipAdjustResponse=tipAdjustresponse;
    }
    public TipAdjustResponse(String error,String errorMsg)
    {
        TipAdjustresponse tipAdjustresponse=new TipAdjustresponse(error,errorMsg);
        this.TipAdjustResponse=tipAdjustresponse;
    }
}
