package com.teamresearch.apt50.Objs;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class AuthorizeRequest {

    @SerializedName("Authorize")
    public Authorize Authorize;

    public class Authorize {
        @Nullable
        @SerializedName("Tip")
        public String Tip;
        @Nullable
        @SerializedName("AddressLine1")
        public String AddressLine1;
        @SerializedName("Amount")
        public String Amount;
        @SerializedName("CardDataSource")
        public String CardDataSource;
        @Nullable
        @SerializedName("City")
        public String City;
        @SerializedName("CardNumber")
        public String CardNumber;
        @SerializedName("CVV2")
        public String CVV2;
        @SerializedName("CardHolderName")
        public String CardHolderName;
        @SerializedName("ExpirationDate")
        public String ExpirationDate;
        @SerializedName("KSN")
        public String KSN;
        @Nullable
        @SerializedName("TokenRequired")
        public String TokenRequired;
        @Nullable
        @SerializedName("orderID")
        public String orderID;
        @Nullable
        @SerializedName("State")
        public String State;
        @Nullable
        @SerializedName("Zip")
        public String Zip;
        @Nullable
        @SerializedName("Track1Data")
        public String Track1Data;
        @Nullable
        @SerializedName("Track2Data")
        public String Track2Data;
        @Nullable
        @SerializedName("Track3Data")
        public String Track3Data;
        public int qty;
        public String subtotal;
        public String tax;
        public String print;
        public Authorize()
        {
            this.Tip="";
            this.AddressLine1="";
            this.Amount="";
            this.City="";
            this.CardNumber="";
            this.CVV2="";
            this.CardHolderName="";
            this.ExpirationDate="";
            this.KSN="";
            this.TokenRequired="";
            this.orderID="";
            this.State="";
            this.Zip="";
            this.Track1Data="";
            this.Track2Data="";
            this.Track3Data="";
            this.qty=0;
            this.subtotal="";
            this.tax="";
            this.print="Y";

        }
        public Authorize(String tip,String addressLine1,String amount,String city,String cardNumber,String cvv2,
                         String cardHolderName,String expirationDate,String ksn,String tokenRequired,String orderID,
                         String state,String zip,String track2Data,int qty,String subtotal,String tax)
        {
            this.Tip=tip;
            this.AddressLine1=addressLine1;
            this.Amount=amount;
            this.City=city;
            this.CardNumber=cardNumber;
            this.CVV2=cvv2;
            this.CardHolderName=cardHolderName;
            this.ExpirationDate=expirationDate;
            this.KSN=ksn;
            this.TokenRequired=tokenRequired;
            this.orderID=orderID;
            this.State=state;
            this.Zip=zip;
            this.Track1Data="";
            this.Track2Data=track2Data;
            this.Track3Data="";
            this.qty=qty;
            this.subtotal=subtotal;
            this.tax=tax;
        }
    }

    public AuthorizeRequest()
    {
        Authorize authorize=new Authorize();
        this.Authorize=authorize;
    }
    public AuthorizeRequest(String tip,String addressLine1,String amount,String city,String cardNumber,String cvv2,
                            String cardHolderName,String expirationDate,String ksn,String tokenRequired,String orderID,
                            String state,String zip,String track2Data,int qty,String subtotal,String tax)
    {
        Authorize authorize=new Authorize(tip,addressLine1,amount,city,cardNumber,cvv2,cardHolderName,
                                            expirationDate,ksn,tokenRequired,orderID,state,zip,track2Data,qty,subtotal,tax);
        this.Authorize=authorize;
    }
    public String getCardDataSource()
    {
        this.Authorize.CardDataSource=(this.Authorize.CardDataSource==null?"NULL":this.Authorize.CardDataSource);
        return this.Authorize.CardDataSource;
    }
    public String getTip(){
        this.Authorize.Tip=(this.Authorize.Tip==null?"0":this.Authorize.Tip);
        return this.Authorize.Tip;
    }
    public String getAmount()
    {
        this.Authorize.Amount=(this.Authorize.Amount==null?"0.00":this.Authorize.Amount);
        return this.Authorize.Amount;
    }
    public String getsubtotal()
    {
        this.Authorize.subtotal=(this.Authorize.subtotal==null?"0.00":this.Authorize.subtotal);
        return this.Authorize.subtotal;
    }
    public String gettax()
    {
        this.Authorize.tax=(this.Authorize.tax==null?"0.00":this.Authorize.tax);
        return this.Authorize.tax;
    }
    public String getprint()
    {
        this.Authorize.print=(this.Authorize.print==null?"Y":this.Authorize.print);
        return this.Authorize.print;
    }
    public String getqty()
    {
        return String.valueOf(this.Authorize.qty);
    }

}
