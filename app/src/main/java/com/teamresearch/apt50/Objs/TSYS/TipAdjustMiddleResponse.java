package com.teamresearch.apt50.Objs.TSYS;

/**
 * Created by Me on 2/19/2018.
 */

public class TipAdjustMiddleResponse {

    public Tipadjustresponse TipAdjustmentResponse;

    public class Tipadjustresponse
    {
        public String status;
        public String responseCode;
        public String responseMessage;
        public String taskID;
        public String transactionID;
        public String transactionTimestamp;
        public String totalAmount;
        public String tip;
        public String customerReceipt;
        public String merchantReceipt;
       
    }
    public String getstatus()                           { return this.TipAdjustmentResponse.status;}
    public String getresponseCode()                     { return this.TipAdjustmentResponse.responseCode;}
    public String getresponseMessage()                  { return this.TipAdjustmentResponse.responseMessage;}
    public String gettaskID()                           { return this.TipAdjustmentResponse.taskID;}
    public String gettransactionID()                    { return this.TipAdjustmentResponse.transactionID;}
    public String gettransactionTimestamp()             { return this.TipAdjustmentResponse.transactionTimestamp;}
    public String gettotalAmount()                      { return this.TipAdjustmentResponse.totalAmount;}
    public String gettip()
    {
        this.TipAdjustmentResponse.tip=(this.TipAdjustmentResponse.tip==null?"0":this.TipAdjustmentResponse.tip);
        return this.TipAdjustmentResponse.tip;
    }
    public String getcustomerReceipt()                  { return this.TipAdjustmentResponse.customerReceipt;}
    public String getmerchantReceipt()                  { return this.TipAdjustmentResponse.merchantReceipt;}

}
