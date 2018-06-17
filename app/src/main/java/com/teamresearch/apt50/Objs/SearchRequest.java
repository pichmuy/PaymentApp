package com.teamresearch.apt50.Objs;

import java.util.ArrayList;

public class SearchRequest {

    public search Search;
    public class search {
        public String trans_id;
        public String trans_type;
        public String start_date;
        public String end_date;
        public search(String trans_id,String trans_type,String start_date,String end_date)
        {
            this.trans_id=trans_id;
            this.trans_type=trans_type;
            this.start_date=start_date;
            this.end_date=end_date;
        }
    }
    public SearchRequest(String trans_id,String trans_type,String start_date,String end_date)
    {
        search searchreq=new search(trans_id,trans_type,start_date,end_date);
        this.Search=searchreq;
    }
    public SearchRequest()
    {

    }
}
