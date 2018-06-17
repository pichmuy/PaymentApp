package com.teamresearch.apt50.Objs.TSYS;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.teamresearch.apt50.Database.DBHelper;
import com.teamresearch.apt50.Database.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Me on 2/19/2018.
 */

public class Logging {

    public static int Transaction(String command,String amount,String auth_code,String card_type,
                            String processor, String response, String response_message,String status,
                            String trans_id, String trans_date, String trans_type,String orderid,String tip,double subtotal,
                                  double tax,int qty,Context context)
    {
        int transid=0;
        DBHelper dbHelper=new DBHelper(context);
        Transaction transaction=new Transaction();
        transaction.setCommand(command);
        transaction.setAmount(amount);
        transaction.setAuth_code(auth_code);
        transaction.setCard_payment_type(card_type);
        transaction.setProcessor(processor);
        transaction.setResponse(response);
        transaction.setResponse_message(response_message);
        transaction.setStatus(status);
        transaction.setTrans_id(trans_id);
        transaction.setTrans_date(trans_date);
        transaction.setTrans_type(trans_type);
        transaction.setOrderID(orderid);
        transaction.setTip(tip);
        transaction.setSubtotal(subtotal);
        transaction.setTax(tax);
        transaction.setQty(qty);


        try {
            dbHelper.createOrUpdate(transaction);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            List<Transaction> alltrans=dbHelper.getAll(Transaction.class);
            transid=alltrans.size();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transid;
    }

    public static Boolean updateTransaction(String ref,String command,String amount,String auth_code,String card_type,
                                     String processor, String response, String response_message,String status,
                                     String trans_id, String trans_date, String trans_type,String orderid,String tip,double subtotal,
                                              double tax,int qty, Context context)
    {
        Boolean success=false;
        DBHelper dbHelper=new DBHelper(context);
        Transaction transaction=new Transaction();
        transaction.setId(Integer.parseInt(ref));
        transaction.setCommand(command);
        transaction.setAmount(amount);
        transaction.setAuth_code(auth_code);
        transaction.setCard_payment_type(card_type);
        transaction.setProcessor(processor);
        transaction.setResponse(response);
        transaction.setResponse_message(response_message);
        transaction.setStatus(status);
        transaction.setTrans_id(trans_id);
        transaction.setTrans_date(trans_date);
        transaction.setTrans_type(trans_type);
        transaction.setOrderID(orderid);
        transaction.setTip(tip);
        transaction.setSubtotal(subtotal);
        transaction.setTax(tax);
        transaction.setQty(qty);

        try {
            dbHelper.createOrUpdate(transaction);
            success=true;
            Log.d("transaction",new Gson().toJson(transaction));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}
