package com.teamresearch.apt50;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Button;

import com.google.gson.Gson;
import com.teamresearch.apt50.Database.DBHelper;
import com.teamresearch.apt50.Device.Settings;
import com.teamresearch.apt50.Objs.TSYS.TSYS;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import wangpos.sdk4.libbasebinder.Printer;


public class httpsserver extends Service {
    public httpsserver() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Service is not available yet!");
    }

    public void CreateServer(Context context) {


        WebServer secureAppServer;
        secureAppServer = new WebServer(context);

        File f;
        f = new File("src/main/resources/keyhigh.jks");
        System.setProperty("javax.net.ssl.trustStore", f.getAbsolutePath());
        try {
            secureAppServer.setServerSocketFactory(new NanoHTTPD.SecureServerSocketFactory(NanoHTTPD.makeSSLSocketFactory("/" + f.getName(), "password".toCharArray()), null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            secureAppServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class WebServer extends NanoHTTPD {

        public Context context;
        public Button button;

        public WebServer(Context context) {
            super(8000);
            this.context=context;

        }

        @Override
        /*
         * https service routes here
         * All JSON parameters are passed to specific method
         */
        public Response serve(String uri, Method method, Map<String, String> headers, Map<String, String> parms, Map<String, String> files) {
            String Content_JsonType="application/json";

            if(method.equals(Method.POST)&&headers.get("content-type").equals("application/json")) {
                if (uri.equals("/api/authorize")) {
                    TSYS tsys=new TSYS();
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(tsys.authorize( files.get("postData"),context,true)));
                }
                if (uri.equals("/api/status")) {
                    TSYS tsys=new TSYS();
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(tsys.status( files.get("postData"),context )));
                }
                if (uri.equals("/api/device")) {
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(Settings.Profile(context)));
                }
                if (uri.equals("/api/device/set")) {
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(Settings.Profile(files.get("postData"),context)));
                }
                if (uri.equals("/api/print")) {
                    TSYS tsys=new TSYS();
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType,new Gson().toJson(tsys.print(files.get("postData"),context)));
                }
                if (uri.equals("/api/search")) {
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(TSYS.search(files.get("postData"),context)));
                }
                if (uri.equals("/api/capture")) {
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(TSYS.capture(files.get("postData"),context)));
                }
                if (uri.equals("/api/tipadjust")) {
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(TSYS.tip(files.get("postData"),context)));
                }
                if (uri.equals("/api/void")) {
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(TSYS.Void(files.get("postData"),context)));
                }
                if (uri.equals("/api/sale")) {
                    TSYS tsys=new TSYS();
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(tsys.sale(files.get("postData"),context,true)));
                }
                if (uri.equals("/api/genKey")) {
                    TSYS tsys=new TSYS();
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(tsys.genKey(files.get("postData"),context)));
                }
                if (uri.equals("/api/return")) {
                    TSYS tsys=new TSYS();
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(tsys.Return(files.get("postData"),context,true)));
                }
                if (uri.equals("/api/return/reference")) {
                    TSYS tsys=new TSYS();
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(tsys.Return(files.get("postData"),context,true)));
                }
                if (uri.equals("/api/report")) {
                    return newFixedLengthResponse(Response.Status.OK, Content_JsonType, new Gson().toJson(TSYS.report(files.get("postData"),context)));
                }
            }
            return newFixedLengthResponse(Response.Status.OK, Content_JsonType, "Not Found");
        }
    }
}
