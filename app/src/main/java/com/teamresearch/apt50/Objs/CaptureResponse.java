package com.teamresearch.apt50.Objs;

public class CaptureResponse {

    public Captureresponse CaptureResponse;
    public class Captureresponse {
        public String Error;
        public String ErrorMsg;
        public String TransactionID;
        public String TransactionTimeStamp;
        public Captureresponse(String error,String errorMsg,String transactionID,String transactionTimeStamp)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;
            this.TransactionID=transactionID;
            this.TransactionTimeStamp=transactionTimeStamp;
        }
        public Captureresponse(String error,String errorMsg)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;

        }

    }
    public CaptureResponse(String error,String errorMsg,String transactionID,String transactionTimeStamp)
    {
        Captureresponse captureresponse=new Captureresponse(error,errorMsg,transactionID,transactionTimeStamp);

        this.CaptureResponse=captureresponse;

    }
    public CaptureResponse(String error,String errorMsg)
    {
        Captureresponse captureresponse=new Captureresponse(error,errorMsg);

        this.CaptureResponse=captureresponse;

    }
    public CaptureResponse()
    {

    }

    /**
     * Created by Me on 2/8/2018.
     */


}
