package com.teamresearch.apt50.Objs;

public class PrintResponse {
    public Printresponse PrintResponse;
    public class Printresponse {
        public String Error;
        public String ErrorMsg;
        public Printresponse(String error,String errorMsg)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;
        }
    }
    public PrintResponse(String error,String errormsg)
    {
        Printresponse printresponse=new Printresponse(error,errormsg);
        this.PrintResponse=printresponse;
    }
    public PrintResponse()
    {

    }
}
