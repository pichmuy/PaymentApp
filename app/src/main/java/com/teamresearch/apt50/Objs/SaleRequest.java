package com.teamresearch.apt50.Objs;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class SaleRequest {

    @SerializedName("Sale")
    public sale Sale;

    public class sale {
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
        public sale()
        {
            this.Tip="";
            this.AddressLine1="";
            this.Amount="";
            this.CardDataSource="";
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
            this.subtotal="0.00";
            this.tax="0.00";
            this.print="Y";
        }
        public sale(String tip,String addressLine1,String amount,String cardDataSource,String city,String cardNumber,
                    String cvv2,String cardHolderName,String expirationDate,String ksn,String tokenRequired,String orderID,
                    String state,String zip,String track2Data,int qty,String subtotal,String tax)
        {
            this.Tip=tip;
            this.AddressLine1=addressLine1;
            this.Amount=amount;
            this.CardDataSource=cardDataSource;
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
            this.Track2Data=track2Data;
            this.qty=qty;
            this.subtotal=subtotal;
            this.tax=tax;
        }
    }

    public SaleRequest(String tip,String addressLine1,String amount,String cardDataSource,String city,String cardNumber,
                       String cvv2,String cardHolderName,String expirationDate,String ksn,String tokenRequired,String orderID,
                       String state,String zip,String track2Data,int qty,String subtotal,String tax)
    {
        sale salereq=new sale(tip,addressLine1,amount,cardDataSource,city,cardNumber,cvv2,cardHolderName,expirationDate,ksn,
                               tokenRequired,orderID,state,zip,track2Data,qty,subtotal,tax);
        this.Sale=salereq;
    }

    public SaleRequest()
    {
        sale sale=new sale();
        this.Sale=sale;
    }

    public String getCardDataSource()
    {
        this.Sale.CardDataSource=(this.Sale.CardDataSource==null?"NULL":this.Sale.CardDataSource);
        return this.Sale.CardDataSource;
    }
    public String getTip(){
        this.Sale.Tip=(this.Sale.Tip==null?"0.00":this.Sale.Tip);
        return this.Sale.Tip;
    }
    public String getAmount()
    {
        this.Sale.Amount=(this.Sale.Amount==null?"0.00":this.Sale.Amount);
        return this.Sale.Amount;
    }
    public String getsubtotal()
    {
        this.Sale.subtotal=(this.Sale.subtotal==null?"0.00":this.Sale.subtotal);
        return this.Sale.subtotal;
    }
    public String gettax()
    {
        this.Sale.tax=(this.Sale.tax==null?"0.00":this.Sale.tax);
        return this.Sale.tax;
    }
    public String getprint()
    {
        this.Sale.print=(this.Sale.print==null?"Y":this.Sale.print);
        return this.Sale.print;
    }


}
