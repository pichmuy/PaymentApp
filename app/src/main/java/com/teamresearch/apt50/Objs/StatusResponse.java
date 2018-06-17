package com.teamresearch.apt50.Objs;

public class StatusResponse {
    public response response;
    public class response {
        public String error;
        public String errormsg;
        public String command;
        public String amount;
        public String auth_code;
        public String card_payment_type;
        public String processor;
        public String response;
        public String response_message;
        public String Status;
        public String trans_id;
        public String trans_date;
        public String trans_type;
        public String qty;
        public String subtotal;
        public String tax;
        public  response(String command,String amount,String auth_code,String card_payment_type,
                         String processor,String response,String response_message,String status,
                         String trans_id,String trans_date,String trans_type,String qty,String subtotal,
                         String tax)
        {
            this.command=command;
            this.amount=amount;
            this.auth_code=auth_code;
            this.card_payment_type=card_payment_type;
            this.processor=processor;
            this.response=response;
            this.response_message=response_message;
            this.Status=status;
            this.trans_id=trans_id;
            this.trans_date=trans_date;
            this.trans_type=trans_type;
            this.qty=qty;
            this.subtotal=subtotal;
            this.tax=tax;
        }
        public response(String error,String errorMsg)
        {
            this.error=error;
            this.errormsg=errorMsg;
        }
    }
    public StatusResponse(String command,String amount,String auth_code,String card_payment_type,
                          String processor,String response,String response_message,String status,
                          String trans_id,String trans_date,String trans_type,String qty,String subtotal,
                          String tax)
    {
        response res = new response(command,amount,auth_code,card_payment_type,processor,response,
                                    response_message,status,trans_id,trans_date,trans_type,qty,subtotal,tax);
        this.response=res;
    }
    public StatusResponse(String error,String errorMsg)
    {
        response response=new response(error,errorMsg);
        this.response=response;
    }
}
