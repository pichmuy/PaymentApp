package com.teamresearch.apt50.Objs;

import com.google.gson.annotations.SerializedName;

public class VoidRequest {
    @SerializedName("Void")
    public voidparam Void;

    public class voidparam {
        @SerializedName("Amount")
        public String Amount;
        @SerializedName("TransactionID")
        public String TransactionID;
        @SerializedName("ReasonCode")
        public int ReasonCode;

        public voidparam(String amount,String transactionID,int reasonCode)
        {
            this.Amount=amount;
            this.TransactionID=transactionID;
            this.ReasonCode=reasonCode;
        }

    }
    public VoidRequest(String amount,String transactionID,int reasonCode)
    {
        voidparam voidparam=new voidparam(amount,transactionID,reasonCode);
        this.Void=voidparam;
    }
    public VoidRequest()
    {

    }
    public String getAmount()
    {
        this.Void.Amount=(this.Void.Amount==null?"0":this.Void.Amount);
        return this.Void.Amount;
    }

}
