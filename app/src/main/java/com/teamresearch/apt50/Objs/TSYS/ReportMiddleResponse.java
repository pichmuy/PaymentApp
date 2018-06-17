package com.teamresearch.apt50.Objs.TSYS;

import java.util.ArrayList;

/**
 * Created by Me on 4/21/2018.
 */

public class ReportMiddleResponse {
    public GetReportDataresponse GetReportDataResponse;
    public class GetReportDataresponse
    {
        public String status;
        public String responseCode;
        public String responseMessage;
        public String reportDataID;
        public String pageNumber;
        public String totalPages;
        public String recordsInResponse;
        public String totalRecords;
        public ReportData  reportData;
        public PageTotals pageTotals;
        public GrandTotals grandTotals;
        public String nextPage;
    }

    public class ReportData
    {
        public ArrayList<ReportItem> ROW;
    }

    public class ReportItem
    {
        public String batchNumber;
        public String batchDate;
        public String batchTime;
        public String merchantID;
        public String totalSalesCount;
        public String totalSalesAmount;
        public String returnCount;
        public String returnTotal;
        public String transactionCount;
        public String netAmount;
        public String batchStatus;

    }
    public class PageTotals
    {
        public TotalDetail ROW;
    }
    public class TotalDetail
    {
        public String netAmount;
        public String returnAmount;
        public String salesAmount;
        public String transactionCount;
    }
    public class GrandTotals
    {
        public TotalDetail ROW;
    }

    public String getstatus()                   { return this.GetReportDataResponse.status;}
    public String getresponseCode()             { return this.GetReportDataResponse.responseCode;}
    public String getresponseMessage()          { return this.GetReportDataResponse.responseMessage;}
}
