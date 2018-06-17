package com.teamresearch.apt50.Objs.TSYS;

/**
 * Created by Me on 2/19/2018.
 */

public class TipAdjustMiddleRequest {

    public Tipadjustment TipAdjustment;
    public class Tipadjustment{

        public String deviceID;
        public String transactionKey;
        public String tip;
        public String transactionID;
        public String developerID;

        public Tipadjustment(String deviceID,String transactionKey,String tip,String transactionID,String developerID)
        {
            this.deviceID=deviceID;
            this.transactionKey=transactionKey;
            this.tip=tip;
            this.transactionID=transactionID;
            this.developerID=developerID;
        }

    }
    public TipAdjustMiddleRequest(String deviceID,String transactionKey,String tip,String transactionID,String developerID)
    {
        Tipadjustment tipadjustment=new Tipadjustment(deviceID,transactionKey,tip,transactionID,developerID);
        this.TipAdjustment=tipadjustment;
    }
    public String getdeviceID()         { return this.TipAdjustment.deviceID;}
    public String gettransactionKey()   { return this.TipAdjustment.transactionKey;}
    public String gettip()
    {
        this.TipAdjustment.tip=(this.TipAdjustment.tip==null?"0":this.TipAdjustment.tip);
        return this.TipAdjustment.tip;
    }
    public String gettransactionID()    { return this.TipAdjustment.transactionID;}
    public String getdeveloperID()      { return this.TipAdjustment.developerID;}
}
