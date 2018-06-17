package com.teamresearch.apt50.Objs;

import com.teamresearch.apt50.Objs.TSYS.ReportMiddleResponse.ReportItem;

import java.util.ArrayList;

/**
 * Created by Me on 4/18/2018.
 */

public class ReportResponse {
    public GetReportDataresponse GetReportDataResponse;
    public class GetReportDataresponse
    {
        public String  Error;
        public String ErrorMsg;
        public ArrayList<ReportItem> ReportData;
        public GetReportDataresponse (String error,String errorMsg,ArrayList<ReportItem> reportData)
        {
            this.Error=error;
            this.ErrorMsg=errorMsg;
            this.ReportData=reportData;
        }
    }


    public ReportResponse(String error,String errorMsg,ArrayList<ReportItem> reportdata)
    {
        GetReportDataresponse getReportDataresponse=new GetReportDataresponse(error,errorMsg,reportdata);
        this.GetReportDataResponse=getReportDataresponse;
    }
    public ReportResponse()
    {

    }
}
