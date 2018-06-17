package com.teamresearch.apt50.Objs.TSYS;

/**
 * Created by Me on 4/21/2018.
 */

public class ReportMiddleRequest {
    public GetReportdata GetReportData;

    public class GetReportdata{
            public String deviceID;
            public String transactionKey;
            public String reportName;
        public GetReportdata(String deviceid,String transactionkey,String reportname)
        {
            this.deviceID=deviceid;
            this.transactionKey=transactionkey;
            this.reportName=reportname;
        }
    }

    public ReportMiddleRequest(String deviceid,String transactionkey,String reportname)
    {
        GetReportdata getReportdata=new GetReportdata(deviceid,transactionkey,reportname);
        this.GetReportData=getReportdata;
    }


    public String getdeviceID()         { return this.GetReportData.deviceID;}
    public String gettransactionKey()   { return this.GetReportData.transactionKey;}
    public String getreportName()       { return this.GetReportData.reportName;}
}
