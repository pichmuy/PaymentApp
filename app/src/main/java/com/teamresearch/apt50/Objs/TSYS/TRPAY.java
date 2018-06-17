package com.teamresearch.apt50.Objs.TSYS;

import android.content.Context;

import com.teamresearch.apt50.Objs.AuthorizeResponse;
import com.teamresearch.apt50.Objs.CaptureResponse;
import com.teamresearch.apt50.Objs.KeyResponse;
import com.teamresearch.apt50.Objs.PrintResponse;
import com.teamresearch.apt50.Objs.ReportResponse;
import com.teamresearch.apt50.Objs.ReturnResponse;
import com.teamresearch.apt50.Objs.SaleResponse;
import com.teamresearch.apt50.Objs.SearchResponse;
import com.teamresearch.apt50.Objs.StatusResponse;
import com.teamresearch.apt50.Objs.TipAdjustResponse;
import com.teamresearch.apt50.Objs.VoidResponse;

import wangpos.sdk4.libbasebinder.Printer;

/**
 * Created by Me on 3/27/2018.
 */

public class TRPAY {
    public static final String processor = "TSYS";
    public AuthorizeResponse authorize(String files, final Context context) {
        switch ( TRPAY.processor ) {
            case "TSYS" : {
                TSYS tsys=new TSYS();
                return tsys.authorize(files, context,false);}
            case "FIRSTDATA": return null;
        }
        return null;
    }
    public SaleResponse sale(String files, final Context context) {
        switch ( TRPAY.processor ) {
            case "TSYS" : {
                TSYS tsys=new TSYS();
                return tsys.sale(files, context,false); }
            case "FIRSTDATA": return null;
        }
        return  null;
    }
    public StatusResponse status(String files, final Context context)
    {
        switch ( TRPAY.processor ) {
            case "TSYS" : {
                TSYS tsys=new TSYS();
                return tsys.status(files, context); }
            case "FIRSTDATA": return null;
        }
        return  null;
    }
    public PrintResponse print(String files, final Context context)
    {
        switch ( TRPAY.processor ) {
            case "TSYS" : {
                TSYS tsys=new TSYS();
                return tsys.print(files, context); }
            case "FIRSTDATA": return null;
        }
        return  null;
    }
    public SearchResponse search(String files, final Context context)
    {
        switch ( TRPAY.processor ) {
            case "TSYS" : {
                TSYS tsys=new TSYS();
                return tsys.search(files, context); }
            case "FIRSTDATA": return null;
        }
        return  null;
    }
    public CaptureResponse capture(String files, final Context context)
    {
        switch ( TRPAY.processor ) {
            case "TSYS" : {
                TSYS tsys=new TSYS();
                return tsys.capture(files, context); }
            case "FIRSTDATA": return null;
        }
        return  null;
    }
    public TipAdjustResponse tip(String files, final Context context)
    {
        switch ( TRPAY.processor ) {
            case "TSYS" : {
                TSYS tsys=new TSYS();
                return tsys.tip(files, context); }
            case "FIRSTDATA": return null;
        }
        return  null;
    }
    public VoidResponse Void(String files, final Context context)
    {
        switch ( TRPAY.processor ) {
            case "TSYS" : {
                TSYS tsys=new TSYS();
                return tsys.Void(files, context); }
            case "FIRSTDATA": return null;
        }
        return  null;
    }
    public ReturnResponse Return(String files, final Context context)
    {
        switch ( TRPAY.processor ) {
            case "TSYS" : {
                TSYS tsys=new TSYS();
                return tsys.Return(files, context,false); }
            case "FIRSTDATA": return null;
        }
        return  null;
    }
    public ReportResponse Report(String files, final Context context)
    {
        switch ( TRPAY.processor ) {
            case "TSYS" : {
                return TSYS.report(files, context); }
            case "FIRSTDATA": return null;
        }
        return  null;
    }
    public KeyResponse genKey(String files, final Context context)
    {
        switch ( TRPAY.processor ) {
            case "TSYS" : {
                TSYS tsys=new TSYS();
                return tsys.genKey(files, context); }
            case "FIRSTDATA": return null;
        }
        return  null;
    }
}
