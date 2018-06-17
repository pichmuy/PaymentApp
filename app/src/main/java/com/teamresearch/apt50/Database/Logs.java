package com.teamresearch.apt50.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "Logs")
public class Logs {
    @DatabaseField(columnName = "id",generatedId = true)
    public int id;
    @DatabaseField(columnName = "error")
    public String error;
    @DatabaseField(columnName = "ipaddress")
    public String ipaddress;
    @DatabaseField(columnName = "log_date")
    public Date log_date;
    @DatabaseField(columnName = "merchant_id")
    public String merchant_id;
    @DatabaseField(columnName = "message")
    public String message;
    @DatabaseField(columnName = "source")
    public String source;

    public int getId()                                  { return this.id;}
    public void setId(int id)                           { this.id=id;}
    public String getError()                            { return  this.error;}
    public void setError(String error)                  { this.error=error;}
    public String getIpaddress()                        { return this.ipaddress;}
    public void setIpaddress(String ipaddress)          { this.ipaddress=ipaddress;}
    public Date getLog_date()                           { return this.log_date;}
    public void setLog_date(Date log_date)              { this.log_date=log_date;}
    public String getMerchant_id()                      { return this.merchant_id;}
    public void setMerchant_id(String merchant_id)      { this.merchant_id=merchant_id;}
    public String getMessage()                          { return this.message;}
    public void setMessage(String source)               { this.source=source;}
}
