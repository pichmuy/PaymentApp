package com.teamresearch.apt50.Objs;

import com.teamresearch.apt50.Database.Transaction;

import java.util.ArrayList;

public class SearchResponse {
    public searchResponse SearchResponse;
    public class searchResponse {
        public String  Error;
        public String ErrorMsg;
        public ArrayList<Transaction> response;
        searchResponse(String error,String errormsg,ArrayList<Transaction> response)
        {
            this.Error=error;
            this.ErrorMsg=errormsg;
            this.response=response;
        }
        searchResponse(String error,String errorMsg)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;
        }

    }
    public SearchResponse(String error,String errormsg,ArrayList<Transaction> response)
    {
        searchResponse searchres=new searchResponse(error,errormsg,response);
        this.SearchResponse=searchres;
    }

    public SearchResponse()
    {

    }
    public SearchResponse(String error,String errorMsg)
    {
        searchResponse searchResponse=new searchResponse(error,errorMsg);
        this.SearchResponse=searchResponse;
    }

}
