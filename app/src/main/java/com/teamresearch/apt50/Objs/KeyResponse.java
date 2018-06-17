package com.teamresearch.apt50.Objs;

public class KeyResponse {
    public generateKeyResponse GenerateKeyResponse;
    public class generateKeyResponse
    {
        public String status;
        public String responseCode;
        public String responseMessage;
        public String transactionKey;
        public generateKeyResponse(String status,String responseCode,String responseMessage)
        {
            this.status=status;
            this.responseCode=responseCode;
            this.responseMessage=responseMessage;
        }
    }
    public KeyResponse(String status,String responseCode,String responseMessage)
    {
        generateKeyResponse keyResponse=new generateKeyResponse(status,responseCode,responseMessage);
        this.GenerateKeyResponse=keyResponse;
    }
    public KeyResponse()
    {
    }
}
