package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.annotations.SerializedName;


public class    AuthMiddleRequest {
    @SerializedName("Auth")
    public  auth Auth;
    public class auth
    {
        @SerializedName("deviceID")
        public String deviceID;
        @SerializedName("transactionKey")
        public String transactionKey;
        @SerializedName("cardDataSource")
        public String cardDataSource;
        @SerializedName("transactionAmount")
        public String transactionAmount;
        @SerializedName("tip")
        public String tip;
        @SerializedName("currencyCode")
        public String currencyCode;
        @SerializedName("cardNumber")
        public String cardNumber;
        @SerializedName("expirationDate")
        public String expirationDate;
        @SerializedName("cvv2")
        public String cvv2;
        @SerializedName("addressLine1")
        public String addressLine1;
        @SerializedName("zip")
        public String zip;
        @SerializedName("orderNumber")
        public String orderNumber;
        @SerializedName("firstName")
        public String firstName;
        @SerializedName("lastName")
        public String lastName;
        @SerializedName("terminalCapability")
        public String terminalCapability;
        @SerializedName("terminalOperatingEnvironment")
        public String terminalOperatingEnvironment;
        @SerializedName("cardholderAuthenticationMethod")
        public String cardholderAuthenticationMethod;
        @SerializedName("terminalAuthenticationCapability")
        public String terminalAuthenticationCapability;
        @SerializedName("terminalOutputCapability")
        public String terminalOutputCapability;
        @SerializedName("maxPinLength")
        public String maxPinLength;
        @SerializedName("securityProtocol")
        public String securityProtocol;
        @SerializedName("ucafCollectionIndicator")
        public String ucafCollectionIndicator;
        @SerializedName("tokenRequired")
        public String tokenRequired;
        @SerializedName("track2Data")
        public String track2Data;
        @SerializedName("track1Data")
        public String track1Data;



        public auth(String deviceID,String transactionKey,String cardDataSource,String transactionAmount,String tip,String currencyCode,
                    String cardNumber,String expirationDate,String cvv2,String addressLine1,String zip,String orderNumber,String firstName,
                    String lastName,String terminalCapability,String terminalOperatingEnvironment,String cardholderAuthenticationMethod,
                    String terminalAuthenticationCapability,String terminalOutputCapability,String maxPinLength)
        {
            this.deviceID=deviceID;
            this.transactionKey=transactionKey;
            this.cardDataSource=cardDataSource;
            this.transactionAmount=transactionAmount;
            this.tip=tip;
            this.currencyCode=currencyCode;
            this.cardNumber=cardNumber;
            this.expirationDate=expirationDate;
            this.cvv2=cvv2;
            this.addressLine1=addressLine1;
            this.zip=zip;
            this.orderNumber=orderNumber;
            this.firstName=firstName;
            this.lastName=lastName;
            this.terminalCapability=terminalCapability;
            this.terminalOperatingEnvironment=terminalOperatingEnvironment;
            this.cardholderAuthenticationMethod=cardholderAuthenticationMethod;
            this.terminalAuthenticationCapability=terminalAuthenticationCapability;
            this.terminalOutputCapability=terminalOutputCapability;
            this.maxPinLength=maxPinLength;

        }
        public auth(String deviceID,String transactionKey,String cardDataSource,String transactionAmount,String tip,String currencyCode,
                    String track2Data, String track1Data, String securityProtocol,String ucafCollectionIndicator,String orderNumber,String tokenRequired,
                    String terminalCapability,String terminalOperatingEnvironment,String cardholderAuthenticationMethod,
                    String terminalAuthenticationCapability,String terminalOutputCapability,String maxPinLength)
        {
            this.deviceID=deviceID;
            this.transactionKey=transactionKey;
            this.cardDataSource=cardDataSource;
            this.transactionAmount=transactionAmount;
            this.tip=tip;
            this.currencyCode=currencyCode;
            this.track2Data=track2Data;
            this.track1Data=track1Data;
            this.securityProtocol=securityProtocol;
            this.ucafCollectionIndicator=ucafCollectionIndicator;
            this.orderNumber=orderNumber;
            this.tokenRequired=tokenRequired;
            this.terminalCapability=terminalCapability;
            this.terminalOperatingEnvironment=terminalOperatingEnvironment;
            this.cardholderAuthenticationMethod=cardholderAuthenticationMethod;
            this.terminalAuthenticationCapability=terminalAuthenticationCapability;
            this.terminalOutputCapability=terminalOutputCapability;
            this.maxPinLength=maxPinLength;

        }

    }
    public AuthMiddleRequest(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String tip, String currencyCode,
                             String cardNumber, String expirationDate, String cvv2, String addressLine1, String zip, String orderNumber, String firstName,
                             String lastName, String terminalCapability, String terminalOperatingEnvironment, String cardholderAuthenticationMethod,
                             String terminalAuthenticationCapability, String terminalOutputCapability, String maxPinLength)
    {
        auth authdata=new auth(deviceID,transactionKey,cardDataSource,transactionAmount,tip,currencyCode,cardNumber,expirationDate,cvv2,
                                addressLine1,zip,orderNumber,firstName,lastName,terminalCapability,terminalOperatingEnvironment,cardholderAuthenticationMethod,
                                terminalAuthenticationCapability,terminalOutputCapability,maxPinLength);
        this.Auth=authdata;
    }

    public AuthMiddleRequest(String deviceID,String transactionKey,String cardDataSource,String transactionAmount,String tip,String currencyCode,
                             String track2Data,String track1Data,String securityProtocol,String ucafCollectionIndicator,String orderNumber,String tokenRequired,
                             String terminalCapability,String terminalOperatingEnvironment,String cardholderAuthenticationMethod,
                             String terminalAuthenticationCapability,String terminalOutputCapability,String maxPinLength)
    {
        auth authdata=new auth(deviceID,transactionKey,cardDataSource,transactionAmount,tip,currencyCode,track2Data,track1Data,securityProtocol,
                                ucafCollectionIndicator,orderNumber,tokenRequired,terminalCapability,terminalOperatingEnvironment,
                                cardholderAuthenticationMethod,terminalAuthenticationCapability,terminalOutputCapability,maxPinLength);
        this.Auth=authdata;
    }
    public auth getAuth()                               { return this.Auth;}
    public String getdeviceID()                         { return this.Auth.deviceID;}
    public String gettransactionKey()                   { return this.Auth.transactionKey;}
    public String getcardDataSource()                   { return this.Auth.cardDataSource;}
    public String gettransactionAmount()                { return this.Auth.transactionAmount;}
    public String gettip()                              { return this.Auth.tip;}
    public String getcurrencyCode()                     { return  this.Auth.currencyCode;}
    public String getcardNumber()                       { return  this.Auth.cardNumber;}
    public String getexpirationDate()                   { return  this.Auth.expirationDate;}
    public String getcvv2()                             { return  this.Auth.cvv2;}
    public String getaddressLine1()                     { return this.Auth.addressLine1;}
    public String getzip()                              { return  this.Auth.zip;}
    public String getorderNumber()                      { return  this.Auth.orderNumber;}
    public String getfirstName()                        { return  this.Auth.firstName;}
    public String getlastName()                         { return  this.Auth.lastName;}
    public String getterminalCapability()               { return this.Auth.terminalCapability;}
    public String getterminalOperatingEnvironment()     { return  this.Auth.terminalOperatingEnvironment;}
    public String getcardholderAuthenticationMethod()   { return  this.Auth.cardholderAuthenticationMethod;}
    public String getterminalAuthenticationCapability() { return  this.Auth.terminalAuthenticationCapability;}
    public String getterminalOutputCapability()         { return  this.Auth.terminalOutputCapability;}
    public String getmaxPinLength()                     { return  this.Auth.maxPinLength;}
    public String gettrack1Data()                       { return  this.Auth.track1Data;}
    public String gettrack2Data()                       { return  this.Auth.track2Data;}
    public String getsecurityProtocol()                 { return  this.Auth.securityProtocol;}
    public String getucafCollectionIndicator()          { return  this.Auth.ucafCollectionIndicator;}
    public String gettokenRequired()                    { return  this.Auth.tokenRequired;}
}
