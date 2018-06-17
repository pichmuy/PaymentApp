package com.teamresearch.apt50.Objs;

import com.google.gson.annotations.SerializedName;

public class CaptureRequest {

    @SerializedName("Capture")
    public Capture Capture;

    public class Capture {
        @SerializedName("Amount")
        public String Amount;
        @SerializedName("TransactionID")
        public String TransactionID;
        public Capture(String amount,String transactionID)
        {
            this.Amount=amount;
            this.TransactionID=transactionID;
        }
    }
    public CaptureRequest()
    {

    }

    public CaptureRequest(String amount, String transactionID)
    {
        Capture capture=new Capture(amount,transactionID);
        this.Capture=capture;
    }

    public String getAmount()
    {
        this.Capture.Amount=(this.Capture.Amount==null?"0":this.Capture.Amount);
        return this.Capture.Amount;
    }
}
