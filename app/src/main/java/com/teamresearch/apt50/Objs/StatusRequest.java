package com.teamresearch.apt50.Objs;

public class StatusRequest {

   public request Request;
   public class request {
       public int Ref;
       public request(int ref)
       {
           this.Ref=ref;
       }
   }
   public StatusRequest(int ref)
   {
       request requestreq=new request(ref);
       this.Request=requestreq;
   }

   public StatusRequest()
   {

   }
}
