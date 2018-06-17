package com.teamresearch.apt50.Objs;

/**
 * Created by Me on 2/4/2018.
 */

public class SaleResponse {

    public Saleresponse SaleResponse;

    public class Saleresponse {
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

        public Saleresponse(String error, String errorMsg,String ref, String transactionID, String transactionTimeStamp,
                        String status, String authCode,double totalAmount, double tip, String addressVerificationCode,
                        String cvvVerificationCode, String cardType, String maskedCardNumber, String token,
                        String expireDate, String firstName, String lastName)

        {

            this.Error = error;
            this.ErrorMsg = errorMsg;
            this.Ref=ref;
            this.TransactionID = transactionID;
            this.TransactionTimeStamp = transactionTimeStamp;
            this.Status = status;
            this.AuthCode = authCode;
            this.TotalAmount = totalAmount;
            this.Tip = tip;
            this.AddressVerificationCode = addressVerificationCode;
            this.CvvVerificationCode = cvvVerificationCode;
            this.CardType = cardType;
            this.maskedCardNumber = maskedCardNumber;
            this.Token = token;
            this.ExpireDate = expireDate;
            this.FirstName = firstName;
            this.LastName = lastName;

        }

        public Saleresponse(String error,String errorMsg,String command,String ref,double totalAmount,double tip)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;
            this.Command=command;
            this.Ref=ref;
            this.TotalAmount=totalAmount;
            this.Tip=tip;
        }

        public Saleresponse(String error,String errorMsg)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;
        }

        public Saleresponse(String status)
        {
            this.Status=status;
        }
    }
    public SaleResponse(String error, String errorMsg, String ref,String transactionID, String transactionTimeStamp,
                        String status, String authCode, double totalAmount, double tip, String addressVerificationCode,
                        String cvvVerificationCode, String cardType, String maskedCardNumber, String token,
                        String expireDate, String firstName, String lastName)
    {
        Saleresponse saleresponse=new Saleresponse(error,errorMsg,ref,transactionID,transactionTimeStamp,status,authCode,
                                    totalAmount,tip,addressVerificationCode,cvvVerificationCode,cardType,maskedCardNumber,
                                    token,expireDate,firstName,lastName);
        this.SaleResponse=saleresponse;
    }

    public SaleResponse(String error,String errorMsg,String command,String ref,double totalAmount,double tip)
    {
        Saleresponse saleResponse=new Saleresponse(error,errorMsg,command,ref,totalAmount,tip);
        this.SaleResponse=saleResponse;
    }

    public SaleResponse(String error,String errorMsg)
    {
        Saleresponse saleResponse=new Saleresponse(error,errorMsg);
        this.SaleResponse=saleResponse;
    }

    public SaleResponse()
    {
        Saleresponse saleResponse=new Saleresponse("Initiated");
        this.SaleResponse=saleResponse;
    }
}
