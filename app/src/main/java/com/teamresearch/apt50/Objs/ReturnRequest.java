package com.teamresearch.apt50.Objs;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class ReturnRequest {

    @SerializedName("Return")
    public Return Return;

    public class Return {
        @SerializedName("Amount")
        public String Amount;
        @SerializedName("CardDataSource")
        public String CardDataSource;
        @SerializedName("KSN")
        public String KSN;
        @SerializedName("CardNumber")
        public String CardNumber;
        @SerializedName("FirstName")
        public String FirstName;
        @SerializedName("LastName")
        public String LastName;
        @SerializedName("ExpirationDate")
        public String ExpirationDate;
        @SerializedName("CVV2")
        public String CVV2;
        @Nullable
        @SerializedName("Track1Data")
        public String Track1Data;
        @Nullable
        @SerializedName("Track2Data")
        public String Track2Data;
        @Nullable
        @SerializedName("Track3Data")
        public String Track3Data;
        @SerializedName("TransactionID")
        public String TransactionID;
        public Return()
        {
            this.Amount="";
            this.CardDataSource="";
            this.KSN="";
            this.CardNumber="";
            this.FirstName="";
            this.LastName="";
            this.Track1Data="";
            this.Track2Data="";
            this.Track3Data="";
            this.TransactionID="";
        }
        public Return(String amount,String cardDataSource,String ksn,String cardNumber,String firstName,String lastName,
                      String track2Data,String transactionID)
        {
            this.Amount=amount;
            this.CardDataSource=cardDataSource;
            this.KSN=ksn;
            this.CardNumber=cardNumber;
            this.FirstName=firstName;
            this.LastName=lastName;
            this.Track2Data=track2Data;
            this.TransactionID=transactionID;
        }
    }
    public ReturnRequest(String amount,String cardDataSource,String ksn,String cardNumber,String firstName,String lastName,
                         String track2Data,String transactionID)
    {
        Return returnreq=new Return(amount,cardDataSource,ksn,cardNumber,firstName,lastName,track2Data,transactionID);
        this.Return=returnreq;
    }
    public ReturnRequest()
    {
        Return returnreq=new Return();
        this.Return=returnreq;
    }

    public String getCardDataSource()
    {
        this.Return.CardDataSource=(this.Return.CardDataSource==null?"NULL":this.Return.CardDataSource);
        return this.Return.CardDataSource;
    }

    public String getAmount()
    {
        this.Return.Amount=(this.Return.Amount==null?"0.00":this.Return.Amount);
        return this.Return.Amount;
    }

}
