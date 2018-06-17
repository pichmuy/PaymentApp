package com.teamresearch.apt50.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.DateTimeType;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;


@DatabaseTable(tableName = "Transaction")
public class Transaction {

    @DatabaseField(columnName = "command")
    public String command;
    @DatabaseField(columnName = "amount")
    public String amount;
    @DatabaseField(columnName = "auth_code")
    public String auth_code;
    @DatabaseField(columnName = "card_payment_type")
    public String card_payment_type;
    @DatabaseField(columnName = "id",generatedId = true)
    public int id;
    @DatabaseField(columnName = "processor")
    public String processor;
    @DatabaseField(columnName = "response")
    public String response;
    @DatabaseField(columnName = "response_message")
    public String response_message;
    @DatabaseField(columnName = "Status")
    public String status;
    @DatabaseField(columnName = "trans_id")
    public String trans_id;
    @DatabaseField(columnName = "trans_date")
    public String trans_date;
    @DatabaseField(columnName = "trans_type")
    public String trans_type;
    @DatabaseField(columnName = "orderID")
    public String orderID;
    @DatabaseField(columnName = "tip")
    public String tip;
    @DatabaseField(columnName = "subtotal")
    public double subtotal;
    @DatabaseField(columnName = "tax")
    public double tax;
    @DatabaseField(columnName = "qty")
    public int qty;


    public String getCommand()                                   { return this.command;}
    public void setCommand(String command)                       { this.command=command;}
    public String getAmount()                                    { return this.amount;}
    public void setAmount(String amount)                         { this.amount=amount;}
    public String getAuth_code()                                 { return this.auth_code;}
    public void setAuth_code(String auth_code)                   { this.auth_code=auth_code;}
    public String getCard_payment_type()                         { return this.card_payment_type;}
    public void setCard_payment_type(String card_payment_type)   { this.card_payment_type=card_payment_type;}
    public int getId()                                           { return this.id;}
    public void setId(int id)                                    { this.id=id;}
    public String getProcessor()                                 { return this.processor;}
    public void setProcessor(String processor)                   { this.processor=processor;}
    public String getResponse()                                  { return this.response;}
    public void setResponse(String response)                     { this.response=response;}
    public String getResponse_message()                          { return this.response_message;}
    public void setResponse_message(String response_message)     { this.response_message=response_message;}
    public String getStatus()                                    { return this.status;}
    public void setStatus(String status)                         { this.status=status;}
    public String getTrans_id()                                  { return this.trans_id;}
    public void setTrans_id(String trans_id)                     { this.trans_id=trans_id;}
    public String getTrans_date()                                { return this.trans_date;}
    public void setTrans_date(String trans_date)                 { this.trans_date=trans_date;}
    public String getTrans_type()                                { return this.trans_type;}
    public void setTrans_type(String trans_type)                 { this.trans_type=trans_type;}
    public String getorderID()
    {
        return this.orderID;
    }
    public void setOrderID(String orderID)                       { this.orderID=orderID;}
    public String getTip()                                       { return this.tip;}
    public void setTip(String tip)                               { this.tip=tip;}
    public double getSubtotal()                                  { return this.subtotal;}
    public void setSubtotal(double subtotal)                     { this.subtotal=subtotal;}
    public double getTax()                                       { return this.tax;}
    public void setTax(double tax)                               { this.tax=tax;}
    public int getQty()                                          { return  this.qty;}
    public void setQty(int qty)                                  { this.qty=qty;}
}
