package com.teamresearch.apt50.Objs.TSYS;

/**
 * Created by Me on 2/14/2018.
 */

public class VoidMiddleRequest {
    public voids Void;
    public class voids
    {
        public String deviceID;
        public String transactionKey;
        public String transactionID;
        public voids(String deviceID,String transactionKey,String transactionID)
        {
            this.deviceID=deviceID;
            this.transactionKey=transactionKey;
            this.transactionID=transactionID;
        }
    }
    public VoidMiddleRequest(String deviceID,String transactionKey,String transactionID)
    {
        voids voids=new voids(deviceID,transactionKey,transactionID);
        this.Void=voids;
    }

    public String getdeviceID()         { return this.Void.deviceID;}
    public String gettransactionKey()   { return this.Void.transactionKey;}
    public String gettransactionID()    { return  this.Void.transactionID;}
}
