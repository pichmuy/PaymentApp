package com.teamresearch.apt50.Objs.TSYS;

/**
 * Created by Me on 2/14/2018.
 */

public class CaptureMiddleResponse {
    public captureresponse CaptureResponse;
    public class captureresponse{

        public String status;
        public String responseCode;
        public String responseMessage;
        public String taskID;
        public String transactionID;
        public String transactionTimestamp;
        public String transactionAmount;
        public String totalAmount;
        public String tip;
        public String customerReceipt;
        public String merchantReceipt;
    }


    public String getstatus()                   {return this.CaptureResponse.status;}
    public String getresponseCode()             {return this.CaptureResponse.responseCode;}
    public String getresponseMessage()          {return this.CaptureResponse.responseMessage;}
    public String gettaskID()                   {return this.CaptureResponse.taskID;}
    public String gettransactionID()            {return this.CaptureResponse.transactionID;}
    public String gettransactionTimestamp()     {return this.CaptureResponse.transactionTimestamp;}
    public String gettransactionAmount()        {return this.CaptureResponse.transactionAmount;}
    public String gettotalAmount()              {return this.CaptureResponse.totalAmount;}
    public String gettip()
    {
        this.CaptureResponse.tip=(this.CaptureResponse.tip==null?"0":this.CaptureResponse.tip);
        return this.CaptureResponse.tip;
    }
    public String getcustomerReceipt()          {return this.CaptureResponse.customerReceipt;}
    public String getmerchantReceipt()          {return this.CaptureResponse.merchantReceipt;}

}
