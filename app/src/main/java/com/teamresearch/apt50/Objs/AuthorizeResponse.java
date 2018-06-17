package com.teamresearch.apt50.Objs;

public class AuthorizeResponse {

    public Authorizeresponse AuthorizeResponse;

    public class Authorizeresponse {
        public String Error;
        public String ErrorMsg;
        public String TransactionID;
        public String TransactionTimeStamp;
        public String Status;
        public String AuthCode;
        public double TotalAmount;
        public double Tip;
        public String AddressVerificationCode;
        public String CvvVerificationCode;
        public String CardType;
        public String maskedCardNumber;
        public String Token;
        public String ExpireDate;
        public String FirstName;
        public String LastName;
        public String Command;
        public String Ref;

        public Authorizeresponse(String error,String errorMsg,String ref,String transactionID,String transactionTimeStamp,String status,
                                 String authCode,double totalAmount,double tip, String addressVerificationCode,String cvvVerificationCode,
                                 String cardType, String maskedCardNumber, String token,String expireDate,String firstName,String lastName)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;
            this.Ref=ref;
            this.TransactionID=transactionID;
            this.TransactionTimeStamp=transactionTimeStamp;
            this.Status=status;
            this.AuthCode=authCode;
            this.TotalAmount=totalAmount;
            this.Tip=tip;
            this.AddressVerificationCode=addressVerificationCode;
            this.CvvVerificationCode=cvvVerificationCode;
            this.CardType=cardType;
            this.maskedCardNumber=maskedCardNumber;
            this.Token=token;
            this.ExpireDate=expireDate;
            this.FirstName=firstName;
            this.LastName=lastName;

        }
        public Authorizeresponse(String error,String errorMsg,String command,String ref,double totalAmount,double tip)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;
            this.Command=command;
            this.Ref=ref;
            this.TotalAmount=totalAmount;
            this.Tip=tip;
        }

        public Authorizeresponse(String status,String responseCode,String responseMessage)
        {
            this.Status=status;
            this.Error=responseCode;
            this.ErrorMsg=responseMessage;
        }

        public Authorizeresponse(String status)
        {
            this.Status=status;
        }
        public Authorizeresponse(String error,String errorMsg)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;
        }
    }
    public AuthorizeResponse(String error,String errorMsg,String ref,String transactionID,String transactionTimeStamp,String status,
                             String authCode,double totalAmount,double tip, String addressVerificationCode,String cvvVerificationCode,
                             String cardType, String maskedCardNumber, String token,String expireDate,String firstName,String lastName)
    {
        Authorizeresponse authorizeresponse=new Authorizeresponse(error,errorMsg,ref,transactionID,transactionTimeStamp,status,authCode,
                                            totalAmount,tip,addressVerificationCode,cvvVerificationCode,cardType,maskedCardNumber,
                                            token,expireDate,firstName,lastName);
        this.AuthorizeResponse=authorizeresponse;
    }
    public AuthorizeResponse(String error,String errorMsg,String command,String ref,double totalAmount,double tip)
    {
        Authorizeresponse authorizeresponse=new Authorizeresponse(error,errorMsg,command,ref,totalAmount,tip);
        this.AuthorizeResponse=authorizeresponse;
    }

    public AuthorizeResponse(String error,String errorMsg)
    {
        Authorizeresponse authorizeresponse=new Authorizeresponse(error,errorMsg);
        this.AuthorizeResponse=authorizeresponse;
    }

    public AuthorizeResponse(String status,String responseCode,String responseMessage)
    {
        Authorizeresponse authResponse=new Authorizeresponse(status,responseCode,responseMessage);
        this.AuthorizeResponse=authResponse;
    }
    public AuthorizeResponse()
    {
        Authorizeresponse authResponse=new Authorizeresponse("Initiated");
        this.AuthorizeResponse=authResponse;
    }
}
