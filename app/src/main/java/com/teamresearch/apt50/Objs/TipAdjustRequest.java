package com.teamresearch.apt50.Objs;

import com.google.gson.annotations.SerializedName;

public class TipAdjustRequest {

    @SerializedName("TipAdjust")
    public Tipadjust TipAdjust;
    public class Tipadjust {
        @SerializedName("Tip")
        public String Tip;
        @SerializedName("TransactionID")
        public String TransactionID;
        public Tipadjust(String tip,String transactionID)
        {
            this.Tip=tip;
            this.TransactionID=transactionID;
        }
    }
    public TipAdjustRequest(String tip,String transactionID)
    {
        Tipadjust tipadjust=new Tipadjust(tip,transactionID);
        this.TipAdjust=tipadjust;
    }
    public TipAdjustRequest()
    {

    }
}
