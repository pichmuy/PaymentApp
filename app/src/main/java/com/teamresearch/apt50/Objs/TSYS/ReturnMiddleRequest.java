package com.teamresearch.apt50.Objs.TSYS;

/**
 * Created by Me on 2/13/2018.
 */

public class ReturnMiddleRequest {

    public returnrequest Return;

    class returnrequest {
     public String deviceID;
     public String transactionKey;
     public String cardDataSource;
     public String transactionAmount;
     public String currencyCode;
     public String orderNumber;
     public String cardNumber;
     public String expirationDate;
     public String cvv2;
     public String terminalCapability;
     public String terminalOperatingEnvironment;
     public String cardholderAuthenticationMethod;
     public String terminalAuthenticationCapability;
     public String terminalOutputCapability;
     public String maxPinLength;
     public String developerID;
     public String securityProtocol;
     public String ucafCollectionIndicator;
     public String tokenRequired;
     public String track2Data;
     public String transationID;
     public returnrequest(String deviceID,String transactionKey, String cardDataSource, String transactionAmount,String cardNumber,
                          String expirationDate,String cvv2,String terminalCapability,String terminalOperatingEnvironment,
                          String cardholderAuthenticationMethod,String terminalAuthenticationCapability,String terminalOutputCapability,
                          String maxPinLength,String developerID)
     {
      this.deviceID=deviceID;
      this.transactionKey=transactionKey;
      this.cardDataSource=cardDataSource;
      this.transactionAmount=transactionAmount;
      this.cardNumber=cardNumber;
      this.expirationDate=expirationDate;
      this.cvv2=cvv2;
      this.terminalCapability=terminalCapability;
      this.terminalOperatingEnvironment=terminalOperatingEnvironment;
      this.cardholderAuthenticationMethod=cardholderAuthenticationMethod;
      this.terminalAuthenticationCapability=terminalAuthenticationCapability;
      this.terminalOutputCapability=terminalOutputCapability;
      this.maxPinLength=maxPinLength;
      this.developerID=developerID;
     }
     public returnrequest(String deviceID,String transactionKey,String cardDataSource,String transactionAmount,String currencyCode,
                 String track2Data, String securityProtocol,String ucafCollectionIndicator,String orderNumber,String tokenRequired,
                 String terminalCapability,String terminalOperatingEnvironment,String cardholderAuthenticationMethod,
                 String terminalAuthenticationCapability,String terminalOutputCapability,String maxPinLength)
     {
      this.deviceID=deviceID;
      this.transactionKey=transactionKey;
      this.cardDataSource=cardDataSource;
      this.transactionAmount=transactionAmount;
      this.currencyCode=currencyCode;
      this.track2Data=track2Data;
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
     public returnrequest(String deviceID,String transactionKey,String transactionAmount,String transactionID)
     {
       this.deviceID=deviceID;
       this.transactionKey=transactionKey;
       this.transactionAmount=transactionAmount;
       this.transationID=transactionID;
     }
    }
    public ReturnMiddleRequest(String deviceID, String transactionKey, String cardDataSource, String transactionAmount, String cardNumber,
                               String expirationDate, String cvv2, String terminalCapability, String terminalOperatingEnvironment,
                               String cardholderAuthenticationMethod, String terminalAuthenticationCapability, String terminalOutputCapability,
                               String maxPinLength, String developerID)

    {
     returnrequest returnrequests=new returnrequest(deviceID,transactionKey,cardDataSource,transactionAmount,cardNumber,
                                         expirationDate,cvv2,terminalCapability,terminalOperatingEnvironment,cardholderAuthenticationMethod,
                                         terminalAuthenticationCapability,terminalOutputCapability,maxPinLength,developerID);
     this.Return=returnrequests;
    }

    public ReturnMiddleRequest(String deviceID,String transactionKey,String cardDataSource,String transactionAmount,String currencyCode,
                               String track2Data, String securityProtocol,String ucafCollectionIndicator,String orderNumber,String tokenRequired,
                               String terminalCapability,String terminalOperatingEnvironment,String cardholderAuthenticationMethod,
                               String terminalAuthenticationCapability,String terminalOutputCapability,String maxPinLength)
    {
     returnrequest returnrequest=new returnrequest(deviceID,transactionKey,cardDataSource,transactionAmount,currencyCode,track2Data,securityProtocol,
          ucafCollectionIndicator,orderNumber,tokenRequired,terminalCapability,terminalOperatingEnvironment,
          cardholderAuthenticationMethod,terminalAuthenticationCapability,terminalOutputCapability,maxPinLength);
       this.Return=returnrequest;
    }
    public ReturnMiddleRequest(String deviceID,String transactionKey,String transactionAmount,String transactionID)
    {
     returnrequest returnrequest=new returnrequest(deviceID,transactionKey,transactionAmount,transactionID);
     this.Return=returnrequest;
    }
 public String getdeviceID()                            { return this.Return.deviceID;}
 public String gettransactionKey()                      { return this.Return.transactionKey;}
 public String getcardDataSource()                      { return this.Return.cardDataSource;}
 public String gettransactionAmount()                   { return this.Return.transactionAmount;}
 public String getorderNumber()                         { return  this.Return.orderNumber;}
 public String getcurrencyCode()                        { return  this.Return.currencyCode;}
 public String getcardNumber()                          { return  this.Return.cardNumber;}
 public String getexpirationDate()                      { return  this.Return.expirationDate;}
 public String getcvv2()                                { return  this.Return.cvv2;}
 public String getterminalCapability()                  { return this.Return.terminalCapability;}
 public String getterminalOperatingEnvironment()        { return  this.Return.terminalOperatingEnvironment;}
 public String getterminalOutputCapability()            { return  this.Return.terminalOutputCapability;}
 public String getcardholderAuthenticationMethod()      { return  this.Return.cardholderAuthenticationMethod;}
 public String getterminalAuthenticationCapability()    { return  this.Return.terminalAuthenticationCapability;}
 public String getmaxPinLength()                        { return  this.Return.maxPinLength;}
 public String getdeveloperID()                         { return this.Return.developerID;}
 public String gettrack2Data()                          { return  this.Return.track2Data;}
 public String getsecurityProtocol()                    { return  this.Return.securityProtocol;}
 public String getucafCollectionIndicator()             { return  this.Return.ucafCollectionIndicator;}
 public String gettokenRequired()                       { return  this.Return.tokenRequired;}
 public String gettransactionID()                       { return this.Return.transationID;}

}
