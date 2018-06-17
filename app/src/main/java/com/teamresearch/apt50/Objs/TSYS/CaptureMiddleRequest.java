package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Me on 2/14/2018.
 */

public class CaptureMiddleRequest {
    public capture Capture;
    public class capture
    {
        @SerializedName("deviceID")
        public String deviceID;
        @SerializedName("transactionKey")
        public String transactionKey;
        @SerializedName("transactionAmount")
        public String transactionAmount;
        @SerializedName("tip")
        public String tip;
        public String transactionID;
        public String orderNotes;
        public capture(String deviceID,String transactionKey,String transactionAmount,String tip,String transactionID,
                       String orderNotes)
        {
            this.deviceID=deviceID;
            this.transactionKey=transactionKey;
            this.transactionAmount=transactionAmount;
            this.tip=tip;
            this.transactionID=transactionID;
            this.orderNotes=orderNotes;
        }
    }
    public CaptureMiddleRequest(String deviceID,String transactionKey,String transactionAmount,String tip,String transactionID,
                                String orderNotes)
    {
        capture captures=new capture(deviceID,transactionKey,transactionAmount,tip,transactionID,orderNotes);
        this.Capture=captures;
    }
    public String getdeviceID()             { return this.Capture.deviceID;}
    public String gettransactionKey()       { return this.Capture.transactionKey;}
    public String gettransactionAmount()    { return this.Capture.transactionAmount;}
    public String gettip()                  { return this.Capture.tip;}
    public String gettransactionID()        { return this.Capture.transactionID;}
    public String getorderNotes()           { return this.Capture.orderNotes;}
}
