package com.teamresearch.apt50.Objs.TSYS;


import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamresearch.apt50.Database.Configuration;
import com.teamresearch.apt50.Database.DBHelper;
import com.teamresearch.apt50.Database.Transaction;
import com.teamresearch.apt50.Objs.AuthorizeRequest;
import com.teamresearch.apt50.Objs.AuthorizeResponse;
import com.teamresearch.apt50.Objs.CaptureRequest;
import com.teamresearch.apt50.Objs.CaptureResponse;
import com.teamresearch.apt50.Objs.KeyRequest;
import com.teamresearch.apt50.Objs.KeyResponse;
import com.teamresearch.apt50.Objs.PrintRequest;
import com.teamresearch.apt50.Objs.PrintResponse;
import com.teamresearch.apt50.Objs.ReportRequest;
import com.teamresearch.apt50.Objs.ReportResponse;
import com.teamresearch.apt50.Objs.TSYS.ReportMiddleResponse.ReportItem;
import com.teamresearch.apt50.Objs.ReturnRequest;
import com.teamresearch.apt50.Objs.ReturnResponse;
import com.teamresearch.apt50.Objs.SaleRequest;
import com.teamresearch.apt50.Objs.SaleResponse;
import com.teamresearch.apt50.Objs.SearchRequest;
import com.teamresearch.apt50.Objs.SearchResponse;
import com.teamresearch.apt50.Objs.StatusRequest;
import com.teamresearch.apt50.Objs.StatusResponse;
import com.teamresearch.apt50.Objs.TipAdjustRequest;
import com.teamresearch.apt50.Objs.TipAdjustResponse;
import com.teamresearch.apt50.Objs.VoidRequest;
import com.teamresearch.apt50.Objs.VoidResponse;
import com.teamresearch.apt50.Prints.ToolsUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.CertificatePinner;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import wangpos.sdk4.libbasebinder.BankCard;
import wangpos.sdk4.libbasebinder.Core;
import wangpos.sdk4.libbasebinder.Printer;


import static com.teamresearch.apt50.Device.Settings.Profile;

/**
 * @version 1.0.0;
 * @overview Tsys Payment Interface
 * Application Layer that communicates with TSYS
 *
 */

public class TSYS {
    private static final String currencyCode="USD";
    private static final String terminalcapability="KEYED_ENTRY_ONLY";
    private static final String terminalOperatingEnvironment="NO_TERMINAL";
    private static final String cardholderAuthenticationMethod="MANUAL_SIGNATURE";
    private static final String terminalAuthenticationCapability="NO_CAPABILITY";
    private static final String terminalOutputCapability="NONE";
    private static final String maxPinLength="NOT_SUPPORTED";
    private static final String orderNotes="FUNDS CAPTURE";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private AuthMiddleResponse authMiddleResponse=new AuthMiddleResponse();
    private AuthorizeRequest authorizeRequest=new AuthorizeRequest();
    private AuthorizeResponse authorizeResponse=new AuthorizeResponse();
    private SaleRequest saleRequest=new SaleRequest();
    private SaleResponse saleResponse=new SaleResponse();
    private ReturnRequest returnRequest=new ReturnRequest();
    private ReturnResponse returnResponse=new ReturnResponse();
    private static final String TAG = "CardReaderNew";
    private BankCard mBankCard;
    private Core mCore;

    private String mText = "";
    private static final int UPDATE_TEXT = 0;
    private ReadMagTask mReadMagTask;

    private double subtotal=0;
    private double tax=0;
    private Printer printer;

    /**
     * Performs an Auth on a provided
     * Credit Card
     */
    public  AuthorizeResponse authorize(String files, final Context context,Boolean server_status)
    {
        String errorcheck=Profile(context).Error;

        if(errorcheck.equals("900"))
        {
            String errormsg=Profile(context).ErrorMsg;
            authorizeResponse=new AuthorizeResponse(errorcheck,errormsg);
            return authorizeResponse;
        }
        String deviceID= Profile(context).device.deviceid;
        String trans_key=Profile(context).device.trans_key;
        if(trans_key==null||deviceID==null)
        {
            String error="901";
            String errormsg="The TransactionKey has not been generated, this is required to perform credit functions";
            authorizeResponse=new AuthorizeResponse(error,errormsg);
            return authorizeResponse;
        }

        String command="Auth";
        authorizeRequest = new AuthorizeRequest();
        authorizeResponse=new AuthorizeResponse();
        new Thread() {
            @Override
            public void run() {
                printer = new Printer(context);
            }
        }.start();
        try {
            authorizeRequest = new Gson().fromJson(files, AuthorizeRequest.class);
            subtotal=Double.parseDouble(authorizeRequest.getsubtotal());
            tax=Double.parseDouble(authorizeRequest.gettax());
        }
        catch (Exception e)
        {
            authorizeResponse=new AuthorizeResponse("1","Invalid parameter");
            return authorizeResponse;
        }
        if(authorizeRequest.Authorize.orderID==null||authorizeRequest.Authorize.orderID=="")
        {
            authorizeResponse=new AuthorizeResponse("8","orderID should not be null");
            return authorizeResponse;
        }
        Log.d("data", files);
        stopTask(context);

        if (authorizeRequest.getCardDataSource().equals("SWIPE")) {
            String Nullstring = "";

            /* Record the transaction */
            int ref =  Logging.Transaction(command,authorizeRequest.Authorize.Amount,Nullstring,Nullstring,"TSYS","",Nullstring,"PENDING",Nullstring,Nullstring,Nullstring,
                    command,authorizeRequest.getTip(),Double.parseDouble(authorizeRequest.getsubtotal()),Double.parseDouble(authorizeRequest.gettax()),Integer.parseInt(authorizeRequest.getqty()),context);
            new Thread() {
                @Override
                public void run() {
                    mCore = new Core(context);
                    mBankCard = new BankCard(context);
                }
            }.start();

            startTask(context,command,server_status,authorizeRequest.Authorize.Amount,ref);
            authorizeResponse=new AuthorizeResponse("0","Transaction initiated",command,String.valueOf(ref),
                                                    Double.parseDouble(authorizeRequest.Authorize.Amount),Double.parseDouble(authorizeRequest.Authorize.Tip));
            return authorizeResponse;
        } else if (authorizeRequest.getCardDataSource().equals("EMV")) {
            /* NOTE: FOR FUTURE USE, NOT INCLUDED IN THIS RELEASE */
            return null; // Exit this method
        } else if (authorizeRequest.getCardDataSource().equals("MANUAL")) {
            /* Extract first name/last name from full name */
            final int ref;
            String firstname=null;
            String lastname=null;

                try {
                    String[] name = authorizeRequest.Authorize.CardHolderName.split(" ");
                    firstname = name[0];
                    lastname = name[1];
                }
                catch(Exception e)
                {
                    authorizeResponse=new AuthorizeResponse("12","Invalid CardHolderName");
                    return authorizeResponse;
                }

            AuthMiddleRequest authMiddleRequest = new AuthMiddleRequest(
                    deviceID, trans_key,
                    authorizeRequest.Authorize.CardDataSource,
                    authorizeRequest.Authorize.Amount, authorizeRequest.Authorize.Tip, currencyCode,
                    authorizeRequest.Authorize.CardNumber, authorizeRequest.Authorize.ExpirationDate,
                    authorizeRequest.Authorize.CVV2, authorizeRequest.Authorize.AddressLine1, authorizeRequest.Authorize.Zip,
                    authorizeRequest.Authorize.orderID, firstname, lastname, terminalcapability, terminalOperatingEnvironment,
                    cardholderAuthenticationMethod, terminalAuthenticationCapability, terminalOutputCapability, maxPinLength
            );

            Gson gson = new GsonBuilder().registerTypeAdapter(AuthMiddleRequest.class, new AuthJsonSerializer()).create();

            String responsedata = null;
            RequestBody body = RequestBody.create(JSON, gson.toJson(authMiddleRequest));

            /* Post request/get response */
            responsedata = post(body);

            Log.d("responsedata", responsedata);

            authMiddleResponse = new AuthMiddleResponse();
            authMiddleResponse = gson.fromJson(responsedata, AuthMiddleResponse.class);
            Log.d("authmiddleresponse",new Gson().toJson(authMiddleResponse));

            String error=null;
            if(authMiddleResponse.getstatus().equals("PASS")) {

                error = "0";
                ref=Logging.Transaction(command, authorizeRequest.getAmount(), authMiddleResponse.getauthCode(), authMiddleResponse.getcardType(),
                        "TSYS", authMiddleResponse.getresponseCode(), authMiddleResponse.getresponseMessage(),
                        authMiddleResponse.getstatus(), authMiddleResponse.gettransactionID(), authMiddleResponse.gettransactionTimestamp(),
                        command,authorizeRequest.Authorize.orderID,authorizeRequest.getTip(),subtotal, tax, authorizeRequest.Authorize.qty,context);
                if(server_status) {
                    Intent intent = new Intent("TRANSACTION APPROVED");
                    intent.putExtra("Amount", authMiddleResponse.gettotalAmount());
                    intent.putExtra("Ref", String.valueOf(ref));
                    context.sendBroadcast(intent);
                }
                DBHelper dbHelper=new DBHelper(context);
                Configuration configuration=new Configuration();
                try {
                    configuration=dbHelper.getById(Configuration.class,1);
                    if(configuration.device_type.equals("APT50")&&authorizeRequest.getprint().equals("Y")){
                        new PrintThread(ref,context,printer).start();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                if(server_status) {
                    Intent intnet = new Intent("TRANSACTION DECLINED");
                    intnet.putExtra("ErrorMsg",authMiddleResponse.getresponseMessage());
                    context.sendBroadcast(intnet);
                }
                ref=Logging.Transaction(command, authorizeRequest.getAmount(), authMiddleResponse.getauthCode(), authMiddleResponse.getcardType(),
                        "TSYS", authMiddleResponse.getresponseCode(), authMiddleResponse.getresponseMessage(),
                        authMiddleResponse.getstatus(), authMiddleResponse.gettransactionID(), authMiddleResponse.gettransactionTimestamp(),
                        command,authorizeRequest.Authorize.orderID,authorizeRequest.getTip(),subtotal, tax, authorizeRequest.Authorize.qty,context);
                error = authMiddleResponse.getresponseCode();
            }

            String errorMsg = authMiddleResponse.getresponseMessage();
            String transactionID = authMiddleResponse.gettransactionID();
            String transactionTimeStamp = authMiddleResponse.gettransactionTimestamp();
            String status = authMiddleResponse.getstatus();
            String authcode = authMiddleResponse.getauthCode();
            double totalamount = Double.parseDouble(authMiddleResponse.gettotalAmount());
            double tip = Double.parseDouble(authMiddleResponse.gettip());
            String addressverificationcode = authMiddleResponse.getaddressVerificationCode();
            String cvvverificationcode = authMiddleResponse.getcvvVerificationCode();
            String cardtype = authMiddleResponse.getcardType();
            String maskedcardnumber = authMiddleResponse.getmaskedCardNumber();
            String firstName = authMiddleResponse.getfirstName();
            String lastName = authMiddleResponse.getlastName();
            String expireDate=authMiddleResponse.getexpirationDate();
            AuthorizeResponse authorizeResponse = new AuthorizeResponse(error, errorMsg,String.valueOf(ref), transactionID,
                    transactionTimeStamp, status, authcode, totalamount, tip, addressverificationcode,
                    cvvverificationcode, cardtype, maskedcardnumber, "Card Token", expireDate,
                    firstName, lastName);
            return authorizeResponse;

        }
        authorizeResponse=new AuthorizeResponse("10","Invalid CardDataSource");
        return authorizeResponse;
    }

    /**
     * Performs a Swipe/MSR Auth on a provided
     * Credit Card
     */
    public  void authorizeSwipe(String files, final Context context,Boolean server_status)
    {

        String deviceID= Profile(context).device.deviceid;
        String trans_key=Profile(context).device.trans_key;
        String tokenRequired="Y";
        String securityProtocol="21";
        String ucafCollectionIndicator="5";
        String terminalCapability="MAGSTRIPE_KEYED_ENTRY_ONLY";
        String terminalOperatingEnvironment="ON_MERCHANT_PREMISES_ATTENDED";
        String cardholderAuthenticationMethod="MANUAL_SIGNATURE";
        String terminalOutputCapability="PRINT_AND_DISPLAY";
        String maxPinLength="UNKNOWN";
        String command="Auth";

        authorizeRequest = new AuthorizeRequest();
        authorizeRequest = new Gson().fromJson(files, AuthorizeRequest.class);

        Log.d("authorizeRequestPRE",new Gson().toJson(authorizeRequest));
            AuthMiddleRequest authMiddleRequest=new AuthMiddleRequest(deviceID, trans_key,authorizeRequest.Authorize.CardDataSource,authorizeRequest.Authorize.Amount,authorizeRequest.Authorize.Tip,currencyCode,authorizeRequest.Authorize.Track2Data,authorizeRequest.Authorize.Track1Data,securityProtocol,ucafCollectionIndicator,
                authorizeRequest.Authorize.orderID,tokenRequired,terminalCapability, terminalOperatingEnvironment,cardholderAuthenticationMethod, terminalAuthenticationCapability, terminalOutputCapability, maxPinLength);

        Log.d("authorizeRequestPOST",new Gson().toJson(authMiddleRequest));

        Gson gson = new GsonBuilder().registerTypeAdapter(AuthMiddleRequest.class, new AuthSwipeJsonSerializer()).create();
            Log.d("ResponseGson",gson.toJson(authMiddleRequest));

            String responsedata = "";

            RequestBody swipebody =  RequestBody.create(JSON, gson.toJson(authMiddleRequest));

            /* Post request/get response */
            responsedata = post(swipebody);

            Log.d("responsedata", responsedata);

            authMiddleResponse = new AuthMiddleResponse();
            authMiddleResponse = gson.fromJson(responsedata, AuthMiddleResponse.class);

            if(authMiddleResponse.getstatus().equals("PASS")) {
                Logging.updateTransaction(authorizeResponse.AuthorizeResponse.Ref,command, authorizeRequest.getAmount(), authMiddleResponse.getauthCode(), authMiddleResponse.getcardType(),
                        "TSYS", authMiddleResponse.getresponseCode(), authMiddleResponse.getresponseMessage(),
                        authMiddleResponse.getstatus(), authMiddleResponse.gettransactionID(), authMiddleResponse.gettransactionTimestamp(),
                        command,authorizeRequest.Authorize.orderID,authorizeRequest.getTip(),subtotal, tax, authorizeRequest.Authorize.qty, context);
                DBHelper dbHelper=new DBHelper(context);
                Configuration configuration=new Configuration();
                try {
                    configuration=dbHelper.getById(Configuration.class,1);
                    if(configuration.device_type.equals("APT50")&&authorizeRequest.getprint().equals("Y")){
                        new PrintThread(Integer.parseInt(authorizeResponse.AuthorizeResponse.Ref),context,printer).start();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            else {
                Logging.updateTransaction(authorizeResponse.AuthorizeResponse.Ref,command, authorizeRequest.getAmount(), authMiddleResponse.getauthCode(), authMiddleResponse.getcardType(),
                        "TSYS", authMiddleResponse.getresponseCode(), authMiddleResponse.getresponseMessage(),
                        authMiddleResponse.getstatus(), authMiddleResponse.gettransactionID(), authMiddleResponse.gettransactionTimestamp(),
                        command,authorizeRequest.Authorize.orderID,authorizeRequest.getTip(),subtotal, tax, authorizeRequest.Authorize.qty, context);
            }




    }

    /**
     * Captures the funds on a previously authorized
     * or a sale transaction
     */
    public static CaptureResponse capture(String files, final Context context)
    {
        String errorcheck=Profile(context).Error;
        if(errorcheck.equals("900"))
        {
            String errormsg=Profile(context).ErrorMsg;
            CaptureResponse captureResponse=new CaptureResponse(errorcheck,errormsg);
            return captureResponse;
        }

        String deviceID= Profile(context).device.deviceid;
        String trans_key=Profile(context).device.trans_key;

        if(trans_key==null||deviceID==null)
        {
            String error="901";
            String errormsg="The TransactionKey has not been generated, this is required to perform credit functions";
            CaptureResponse captureResponse=new CaptureResponse(errorcheck,errormsg);
            return captureResponse;
        }

        String command="Capture";
        CaptureRequest captureRequest=new CaptureRequest();
        CaptureResponse captureResponse=new CaptureResponse();
        try {
            captureRequest = new Gson().fromJson(files, CaptureRequest.class);
        }
        catch (Exception e)
        {
            captureResponse=new CaptureResponse("1","Invalid Parameter");
            return captureResponse;
        }
        Log.d("CaptureRequest",new Gson().toJson(captureRequest));
        CaptureMiddleRequest captureMiddleRequest=new CaptureMiddleRequest(deviceID,trans_key,
                captureRequest.Capture.Amount,"20",captureRequest.Capture.TransactionID,orderNotes);

        Gson gson=new GsonBuilder().registerTypeAdapter(CaptureMiddleRequest.class,new CaptureJsonSerializer()).create();
        String responsedata=null ;
        RequestBody body = RequestBody.create(JSON, gson.toJson(captureMiddleRequest));
        responsedata=post(body);

        Log.d("responsedata",responsedata);
        CaptureMiddleResponse captureMiddleResponse =new CaptureMiddleResponse();
        captureMiddleResponse =gson.fromJson(responsedata,CaptureMiddleResponse.class);

        String  error;
        if(captureMiddleResponse.getstatus().equals("PASS")) {
            error="0";
        }
        else {
            error=captureMiddleResponse.getresponseCode();
        }
        Logging.Transaction(command, captureRequest.getAmount(), "", "",
                "TSYS", captureMiddleResponse.getresponseCode(), captureMiddleResponse.getresponseMessage(),
                captureMiddleResponse.getstatus(), captureMiddleResponse.gettransactionID(), captureMiddleResponse.gettransactionTimestamp(),
                command,"",captureMiddleResponse.gettip(),0.00, 0.00,0,context);
        captureResponse=new CaptureResponse(error,captureMiddleResponse.getresponseMessage(),captureMiddleResponse.gettransactionID(),captureMiddleResponse.gettransactionTimestamp());
        Log.d("Capture_Response",new Gson().toJson(captureResponse));
        return captureResponse;
    }


    /**
     * Voids a previously authorized
     * or a sale transaction
     */
    public static VoidResponse Void(String files,Context context)
    {
        String errorcheck=Profile(context).Error;
        if(errorcheck.equals("900"))
        {
            String errormsg=Profile(context).ErrorMsg;
            VoidResponse voidResponse=new VoidResponse(errorcheck,errormsg);
            return voidResponse;
        }
        String deviceID= Profile(context).device.deviceid;
        String trans_key=Profile(context).device.trans_key;
        if(trans_key==null||deviceID==null)
        {
            String error="901";
            String errormsg="The TransactionKey has not been generated, this is required to perform credit functions";
            VoidResponse voidResponse=new VoidResponse(errorcheck,errormsg);
            return voidResponse;
        }
        String command="Void";
        VoidRequest voidRequest = new VoidRequest();
        try {
            voidRequest = new Gson().fromJson(files, VoidRequest.class);
        }
        catch (Exception e)
        {
            VoidResponse voidResponse=new VoidResponse("1","Invalid Parameter");
            return voidResponse;
        }
        Log.d("VoidRequest",new Gson().toJson(voidRequest));
        VoidMiddleRequest voidMiddleRequest=new VoidMiddleRequest(deviceID,trans_key,
                voidRequest.Void.TransactionID);

        Gson gson=new GsonBuilder().registerTypeAdapter(VoidMiddleRequest.class,new VoidJsonSerializer()).create();
        String responsedata=null ;
        RequestBody body = RequestBody.create(JSON, gson.toJson(voidMiddleRequest));
        responsedata=post(body);

        Log.d("responsedata",responsedata);
        VoidMiddleResponse voidMiddleResponse =new VoidMiddleResponse();
        voidMiddleResponse =gson.fromJson(responsedata,VoidMiddleResponse.class);

        String  error;
        if(voidMiddleResponse.getstatus().equals("PASS")) {
            error = "0";
        }
        else error=voidMiddleResponse.getresponseCode();

        Logging.Transaction(command, voidRequest.getAmount(), "", "",
                "TSYS", voidMiddleResponse.getresponseCode(), voidMiddleResponse.getresponseMessage(),
                voidMiddleResponse.getstatus(), voidMiddleResponse.gettransactionID(), voidMiddleResponse.gettransactionTimestamp(),
                command,"","0",0.00, 0.00,0,context);

        VoidResponse voidResponse=new VoidResponse(error,voidMiddleResponse.getresponseMessage(),voidMiddleResponse.getvoidedAmount(),
                                voidMiddleResponse.gettransactionID(), voidMiddleResponse.gettransactionTimestamp());
        Log.d("Void_Response",new Gson().toJson(voidResponse));
        return voidResponse;
    }

    /**
     * Performs a Sale on a provided
     * Credit Card
     */
    public  SaleResponse sale(String files, final Context context,Boolean server_status)
    {
        String errorcheck=Profile(context).Error;
        if(errorcheck.equals("900"))
        {
            String errormsg=Profile(context).ErrorMsg;
            SaleResponse saleResponse=new SaleResponse(errorcheck,errormsg);
            return saleResponse;
        }
        String deviceID= Profile(context).device.deviceid;
        String trans_key=Profile(context).device.trans_key;
        if(trans_key==null||deviceID==null)
        {
            String error="901";
            String errormsg="The TransactionKey has not been generated, this is required to perform credit functions";
            SaleResponse saleResponse=new SaleResponse(errorcheck,errormsg);
            return saleResponse;
        }

        String command="Sale";
        String NullString="";

        new Thread() {
            @Override
            public void run() {
                printer = new Printer(context);
            }
        }.start();
        saleRequest =new SaleRequest();
        try {
            saleRequest = new Gson().fromJson(files, SaleRequest.class);
            subtotal=Double.parseDouble(saleRequest.Sale.subtotal);
            tax=Double.parseDouble(saleRequest.Sale.tax);
        }
        catch (Exception e)
        {
            saleResponse=new SaleResponse("1","Invalid Parameter");
            return saleResponse;
        }
        if(saleRequest.Sale.orderID==null||saleRequest.Sale.orderID=="")
        {
            saleResponse=new SaleResponse("8","orderID should not be null");
            return saleResponse;
        }
        saleResponse=new SaleResponse();
        if(saleRequest.getCardDataSource().equals("SWIPE")){
            int ref=Logging.Transaction(command,saleRequest.getAmount(),NullString,NullString,"TSYS","",
                                    NullString,"PENDING",NullString,NullString,command,"","0",0.00,0.00,0,context);

            new Thread() {
                @Override
                public void run() {
                    mCore = new Core(context);
                    mBankCard = new BankCard(context);
                }
            }.start();

            startTask(context,command,server_status,String.valueOf(saleRequest.Sale.Amount),ref);
            saleResponse=new SaleResponse("0","Transaction initiated",command,String.valueOf(ref),
                    Double.parseDouble(saleRequest.getAmount()),Double.parseDouble(saleRequest.getTip()));
            return saleResponse;
        }
        else if(saleRequest.getCardDataSource().equals("EMV")){
            return null;
        }
        else if(saleRequest.getCardDataSource().equals("MANUAL")) {
            int ref;
            Log.d("SaleRequest", new Gson().toJson(saleRequest));
            String firstname=null;
            String lastname=null;
            try {
                String[] name = saleRequest.Sale.CardHolderName.split(" ");
                firstname = name[0];
                lastname = name[1];
            }
            catch (Exception e)
            {
                saleResponse=new SaleResponse("12","Invalid CardHolderName");
                return saleResponse;
            }
            SaleMiddleRequest saleMiddleRequest = new SaleMiddleRequest(deviceID, trans_key,saleRequest.Sale.CardDataSource,
                    saleRequest.Sale.Amount, saleRequest.Sale.Tip, currencyCode, saleRequest.Sale.CardNumber,
                    saleRequest.Sale.ExpirationDate, saleRequest.Sale.CVV2, saleRequest.Sale.AddressLine1, saleRequest.Sale.Zip,
                    saleRequest.Sale.orderID, firstname, lastname, terminalcapability, terminalOperatingEnvironment,
                    cardholderAuthenticationMethod, terminalAuthenticationCapability, terminalOutputCapability, maxPinLength);
            Gson gson = new GsonBuilder().registerTypeAdapter(SaleMiddleRequest.class, new SaleJsonSerializer()).create();
            String responsedata = null;
            RequestBody body = RequestBody.create(JSON, gson.toJson(saleMiddleRequest));
            responsedata = post(body);

            Log.d("responsedata", responsedata);
            SaleMiddleResponse saleMiddleResponse = new SaleMiddleResponse();
            saleMiddleResponse = gson.fromJson(responsedata, SaleMiddleResponse.class);
            double totalAmount = Double.parseDouble(saleMiddleResponse.gettotalAmount());
            double tip = Double.parseDouble(saleMiddleResponse.gettip());

            String error;
            if(saleMiddleResponse.getstatus().equals("PASS")) {

                error = "0";
                ref=Logging.Transaction(command, saleRequest.getAmount(), saleMiddleResponse.getauthCode(), saleMiddleResponse.getcardType(),
                        "TSYS", saleMiddleResponse.getresponseCode(), saleMiddleResponse.getresponseMessage(),
                        saleMiddleResponse.getstatus(), saleMiddleResponse.gettransactionID(), saleMiddleResponse.gettransactionTimestamp(),
                        command,saleRequest.Sale.orderID,saleRequest.getTip(),subtotal,tax, saleRequest.Sale.qty, context);
                if(server_status) {
                    Intent intnet = new Intent("TRANSACTION APPROVED");
                    intnet.putExtra("Amount",saleMiddleResponse.gettotalAmount());
                    intnet.putExtra("Ref",String.valueOf(ref));
                    context.sendBroadcast(intnet);
                }
                DBHelper dbHelper=new DBHelper(context);
                Configuration configuration=new Configuration();
                try {
                    configuration=dbHelper.getById(Configuration.class,1);
                    if(configuration.device_type.equals("APT50")&&saleRequest.getprint().equals("Y")){
                        new PrintThread(ref,context,printer).start();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            else {
                if(server_status) {
                    Intent intnet = new Intent("TRANSACTION DECLINED");
                    intnet.putExtra("ErrorMsg",saleMiddleResponse.getresponseMessage());
                    context.sendBroadcast(intnet);
                }
                ref=Logging.Transaction(command, saleRequest.getAmount(), saleMiddleResponse.getauthCode(), saleMiddleResponse.getcardType(),
                        "TSYS", saleMiddleResponse.getresponseCode(), saleMiddleResponse.getresponseMessage(),
                        saleMiddleResponse.getstatus(), saleMiddleResponse.gettransactionID(), saleMiddleResponse.gettransactionTimestamp(),
                        command,saleRequest.Sale.orderID,saleRequest.getTip(),subtotal,tax, saleRequest.Sale.qty, context);
                error = saleMiddleResponse.getresponseCode();
            }

            SaleResponse saleResponse = new SaleResponse(error, saleMiddleResponse.getresponseMessage(),String.valueOf(ref), saleMiddleResponse.gettransactionID(), saleMiddleResponse.gettransactionTimestamp(),
                    saleMiddleResponse.getstatus(), saleMiddleResponse.getauthCode(), totalAmount, tip, saleMiddleResponse.getaddressVerificationCode(),
                    saleMiddleResponse.getcvvVerificationCode(), saleMiddleResponse.getcardType(), saleMiddleResponse.getmaskedCardNumber(), "CardToken", saleMiddleResponse.getexpirationDate(),
                    saleMiddleResponse.getfirstName(), saleMiddleResponse.getlastName());
            return saleResponse;
        }
        saleResponse=new SaleResponse("10","Invalid CardDataSource");
        return saleResponse;
    }

    /**
     * Performs a Swiped/MSR Sale on a provided
     * Credit Card
     */
    public  void saleSwipe(String files, final Context context,Boolean server_status)
    {
        String deviceID= Profile(context).device.deviceid;
        String trans_key=Profile(context).device.trans_key;
        String tokenRequired="Y";
        String securityProtocol="21";
        String ucafCollectionIndicator="5";
        String terminalCapability="UNKNOWN";
        String terminalOperatingEnvironment="ON_MERCHANT_PREMISES_ATTENDED";
        String cardholderAuthenticationMethod="MANUAL_SIGNATURE";
        String terminalOutputCapability="UNKNOWN";
        String maxPinLength="UNKNOWN";
        String command="Sale";
        saleRequest = new SaleRequest();
        saleRequest = new Gson().fromJson(files, SaleRequest.class);

        Log.d("saleRequest",new Gson().toJson(saleRequest));

        SaleMiddleRequest saleMiddleRequest=new SaleMiddleRequest(deviceID, trans_key,
                saleRequest.Sale.CardDataSource,saleRequest.Sale.Amount,
                saleRequest.Sale.Tip,currencyCode,saleRequest.Sale.Track2Data,saleRequest.Sale.Track1Data,securityProtocol,ucafCollectionIndicator,
                saleRequest.Sale.orderID,tokenRequired,terminalCapability, terminalOperatingEnvironment,
                cardholderAuthenticationMethod, terminalAuthenticationCapability, terminalOutputCapability, maxPinLength);
        Log.d("salemiddleRequest",new Gson().toJson(saleMiddleRequest));

        Gson gson = new GsonBuilder().registerTypeAdapter(SaleMiddleRequest.class, new SaleSwipeJsonSerializer()).create();
        Log.d("testinggson",gson.toJson(saleMiddleRequest));

        String responsedata = "";

        RequestBody swipebody =  RequestBody.create(JSON, gson.toJson(saleMiddleRequest));

        /* Post request/get response */
        responsedata = post(swipebody);

        Log.d("responsedata", responsedata);

        SaleMiddleResponse saleMiddleResponse=new SaleMiddleResponse();
        saleMiddleResponse = gson.fromJson(responsedata, SaleMiddleResponse.class);

        if(saleMiddleResponse.getstatus().equals("PASS")) {
            Logging.updateTransaction(saleResponse.SaleResponse.Ref,command, saleRequest.getAmount(), saleMiddleResponse.getauthCode(), saleMiddleResponse.getcardType(),
                    "TSYS", saleMiddleResponse.getresponseCode(), saleMiddleResponse.getresponseMessage(),
                    saleMiddleResponse.getstatus(), saleMiddleResponse.gettransactionID(), saleMiddleResponse.gettransactionTimestamp(),
                    command,saleRequest.Sale.orderID,saleRequest.getTip(),subtotal,tax, saleRequest.Sale.qty,context);
            DBHelper dbHelper=new DBHelper(context);
            Configuration configuration=new Configuration();
            try {
                configuration=dbHelper.getById(Configuration.class,1);
                if(configuration.device_type.equals("APT50")&&saleRequest.getprint().equals("Y")){
                    new PrintThread(Integer.parseInt(saleResponse.SaleResponse.Ref),context,printer).start();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            Logging.updateTransaction(saleResponse.SaleResponse.Ref,command, saleRequest.getAmount(), saleMiddleResponse.getauthCode(), saleMiddleResponse.getcardType(),
                    "TSYS", saleMiddleResponse.getresponseCode(), saleMiddleResponse.getresponseMessage(),
                    saleMiddleResponse.getstatus(), saleMiddleResponse.gettransactionID(), saleMiddleResponse.gettransactionTimestamp(),
                    command,saleRequest.Sale.orderID,saleRequest.getTip(),subtotal,tax, saleRequest.Sale.qty,context);
        }
        Thread r=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        r.start();
    }

    /**
     * Performs a tip adjustment on a prior
     * Sale or Authorization
     */
    public static TipAdjustResponse tip(String files,Context context)
    {
        String deviceID= Profile(context).device.deviceid;
        String trans_key=Profile(context).device.trans_key;
        String command="TipAdjust";

        TipAdjustRequest tipAdjustRequest = new TipAdjustRequest();
        try {
            tipAdjustRequest = new Gson().fromJson(files, TipAdjustRequest.class);
        }
        catch (Exception e)
        {
            TipAdjustResponse tipAdjustResponse=new TipAdjustResponse("1","Invalid Parameter");
            return tipAdjustResponse;
        }

        TipAdjustMiddleRequest tipAdjustMiddleRequest=new TipAdjustMiddleRequest(deviceID,trans_key, tipAdjustRequest.TipAdjust.Tip,tipAdjustRequest.TipAdjust.TransactionID,"002975");
        Gson gson=new GsonBuilder().registerTypeAdapter(TipAdjustMiddleRequest.class,new TipAdjustJsonSerializer()).create();

        String responsedata=null ;
        RequestBody body = RequestBody.create(JSON, gson.toJson(tipAdjustMiddleRequest));

        responsedata=post(body);

        Log.d("responsedata",responsedata);
        TipAdjustMiddleResponse tipAdjustMiddleResponse =new TipAdjustMiddleResponse();
        tipAdjustMiddleResponse =gson.fromJson(responsedata,TipAdjustMiddleResponse.class);

        String  error;
        if(tipAdjustMiddleResponse.getstatus().equals("PASS"))
        {
            error="0";
        }
        else error=tipAdjustMiddleResponse.getresponseCode();

        Logging.Transaction(command, tipAdjustMiddleResponse.gettotalAmount(), "", "",
                "TSYS", tipAdjustMiddleResponse.getresponseCode(), tipAdjustMiddleResponse.getresponseMessage(),
                tipAdjustMiddleResponse.getstatus(), tipAdjustMiddleResponse.gettransactionID(), tipAdjustMiddleResponse.gettransactionTimestamp(),
                command,"",tipAdjustMiddleResponse.gettip(),0.00, 0.00,0,context);

        TipAdjustResponse tipAdjustResponse=new TipAdjustResponse(error,tipAdjustMiddleResponse.getresponseMessage(),
                tipAdjustMiddleResponse.gettotalAmount(), tipAdjustMiddleResponse.gettip(),tipAdjustMiddleResponse.gettransactionID(),
                tipAdjustMiddleResponse.gettransactionTimestamp());
        Log.d("TipAdjust_Response",new Gson().toJson(tipAdjustResponse));
        return tipAdjustResponse;
    }

    /**
     * Returns the funds on a prior Sale or Authorization
     */
    public ReturnResponse Return(String files, final Context context, Boolean server_status) {

        String errorcheck = Profile(context).Error;

        if (errorcheck.equals("900")) {
            String errormsg = Profile(context).ErrorMsg;
            ReturnResponse returnResponse = new ReturnResponse(errorcheck, errormsg);
            return returnResponse;
        }
        String deviceID = Profile(context).device.deviceid;
        String trans_key = Profile(context).device.trans_key;

        if (trans_key == null || deviceID == null) {
            String error = "901";
            String errormsg = "The TransactionKey has not been generated, this is required to perform credit functions";
            ReturnResponse returnResponse = new ReturnResponse(errorcheck, errormsg);
            return returnResponse;
        }
        String command = "Return";
        String NullString = "";
        new Thread() {
            @Override
            public void run() {
                printer = new Printer(context);
            }
        }.start();
        returnRequest = new ReturnRequest();
        try {
            returnRequest = new Gson().fromJson(files, ReturnRequest.class);

        }
        catch (Exception e)
        {
            returnResponse=new ReturnResponse("1","Invalid Parameter");
            return returnResponse;
        }
        returnResponse = new ReturnResponse();
        Log.d("returnRequest",new Gson().toJson(returnRequest));

            if (returnRequest.getCardDataSource().equals("SWIPE")) {
                int ref = Logging.Transaction(command, returnRequest.getAmount(), NullString, NullString, "TSYS", "",
                        NullString, "PENDING", NullString, NullString, command,"", "0", 0.00, 0.00, 0, context);

                startTask(context, command,server_status,returnRequest.Return.Amount,ref);
                returnResponse = new ReturnResponse("0", "Transaction initiated", command, String.valueOf(ref),
                        Double.parseDouble(returnRequest.getAmount()));
                return returnResponse;
            } else if (returnRequest.getCardDataSource().equals("EMV")) {
                return null;
            } else if (returnRequest.getCardDataSource().equals("MANUAL")) {
                int ref;
                RequestBody body;
                String responsedata = null;
                if(returnRequest.Return.TransactionID!=null)
                {
                    ReturnMiddleRequest returnMiddleRequest=new ReturnMiddleRequest(deviceID,trans_key,returnRequest.getAmount(),
                            returnRequest.Return.TransactionID);
                    Log.d("transactionID",new Gson().toJson(returnMiddleRequest));
                    Gson gson = new GsonBuilder().registerTypeAdapter(ReturnMiddleRequest.class, new ReturnReferenceJsonSerializer()).create();
                    body = RequestBody.create(JSON, gson.toJson(returnMiddleRequest));
                }
                else {
                    ReturnMiddleRequest returnMiddleRequest = new ReturnMiddleRequest(deviceID, trans_key, "MANUAL",
                            returnRequest.getAmount(), returnRequest.Return.CardNumber, returnRequest.Return.ExpirationDate,
                            returnRequest.Return.CVV2, terminalcapability, terminalOperatingEnvironment, cardholderAuthenticationMethod, terminalAuthenticationCapability,
                            terminalOutputCapability, maxPinLength, "002975");
                    Gson gson = new GsonBuilder().registerTypeAdapter(ReturnMiddleRequest.class, new ReturnJsonSerializer()).create();
                    body = RequestBody.create(JSON, gson.toJson(returnMiddleRequest));
                }
                responsedata = post(body);

                Log.d("responsedata", responsedata);
                ReturnMiddleResponse returnMiddleResponse = new ReturnMiddleResponse();
                returnMiddleResponse = new Gson().fromJson(responsedata, ReturnMiddleResponse.class);

                String error;


                if (returnMiddleResponse.getstatus().equals("PASS")) {

                    error = "0";
                    ref = Logging.Transaction(command, returnRequest.getAmount(), returnMiddleResponse.getauthCode(), returnMiddleResponse.getcardType(),
                            "TSYS", returnMiddleResponse.getresponseCode(), returnMiddleResponse.getresponseMessage(),
                            returnMiddleResponse.getstatus(), returnMiddleResponse.gettransactionID(), returnMiddleResponse.gettransactionTimestamp(),
                            command,"", "0", 0, 0, 0, context);
                    if(server_status) {
                        Intent intnet = new Intent("TRANSACTION APPROVED");
                        intnet.putExtra("Amount",returnMiddleResponse.getreturnedAmount());
                        intnet.putExtra("Ref",String.valueOf(ref));
                        context.sendBroadcast(intnet);
                    }
                    DBHelper dbHelper=new DBHelper(context);
                    Configuration configuration=new Configuration();
                    try {
                        configuration=dbHelper.getById(Configuration.class,1);
                        if(configuration.device_type.equals("APT50")&&saleRequest.getprint().equals("Y")){
                            new PrintThread(ref,context,printer).start();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    if(server_status) {
                        Intent intnet = new Intent("TRANSACTION DECLINED");
                        intnet.putExtra("ErrorMsg",returnMiddleResponse.getresponseMessage());
                        context.sendBroadcast(intnet);
                    }
                    ref = Logging.Transaction(command, returnRequest.getAmount(), returnMiddleResponse.getauthCode(), returnMiddleResponse.getcardType(),
                            "TSYS", returnMiddleResponse.getresponseCode(), returnMiddleResponse.getresponseMessage(),
                            returnMiddleResponse.getstatus(), returnMiddleResponse.gettransactionID(), returnMiddleResponse.gettransactionTimestamp(),
                            command, "", "0", 0, 0, 0, context);
                    error = returnMiddleResponse.getresponseCode();

                }

                ReturnResponse returnResponse = new ReturnResponse(returnMiddleResponse.getcardType(), error, returnMiddleResponse.getresponseMessage(),
                        String.valueOf(ref),returnMiddleResponse.getexpirationDate(), returnMiddleResponse.getmaskedCardNumber(), Double.parseDouble(returnMiddleResponse.getreturnedAmount()),
                        returnMiddleResponse.gettransactionID(), returnMiddleResponse.gettransactionTimestamp());
                Log.d("Return_Response",new Gson().toJson(returnResponse));
                return returnResponse;
            }

        return null;
    }

    /**
     * Performs a Return on a swipe transaction
     * This is return with no reference
     */
    public  void returnSwipe(String files, final Context context,Boolean server_status)
    {
        String deviceID= Profile(context).device.deviceid;
        String trans_key=Profile(context).device.trans_key;
        String tokenRequired="Y";
        String securityProtocol="21";
        String ucafCollectionIndicator="5";
        String terminalCapability="UNKNOWN";
        String terminalOperatingEnvironment="ON_MERCHANT_PREMISES_ATTENDED";
        String cardholderAuthenticationMethod="MANUAL_SIGNATURE";
        String terminalOutputCapability="UNKNOWN";
        String maxPinLength="UNKNOWN";
        String command="Return";
        returnRequest = new ReturnRequest();

        returnRequest = new Gson().fromJson(files, ReturnRequest.class);

        Log.d("returnRequest",new Gson().toJson(returnRequest));

        ReturnMiddleRequest returnMiddleRequest = new ReturnMiddleRequest(deviceID, trans_key, returnRequest.Return.CardDataSource,
                returnRequest.getAmount(), currencyCode, returnRequest.Return.Track2Data,
                securityProtocol, ucafCollectionIndicator,"",tokenRequired,terminalCapability, terminalOperatingEnvironment, cardholderAuthenticationMethod,
                terminalAuthenticationCapability,terminalOutputCapability, maxPinLength);

        Log.d("returnRequest",new Gson().toJson(returnMiddleRequest));
        Gson gson = new GsonBuilder().registerTypeAdapter(ReturnMiddleRequest.class, new ReturnSwipeJsonSerializer()).create();

        Log.d("testinggson",gson.toJson(returnMiddleRequest));

        String responsedata = "";

        RequestBody swipebody =  RequestBody.create(JSON, gson.toJson(returnMiddleRequest));

        /* Post request/get response */
        responsedata = post(swipebody);

        Log.d("responsedata", responsedata);

        ReturnMiddleResponse returnMiddleResponse=new ReturnMiddleResponse();
        returnMiddleResponse = gson.fromJson(responsedata, ReturnMiddleResponse.class);

        if(returnMiddleResponse.getstatus().equals("PASS")) {
            Logging.updateTransaction(returnResponse.ReturnResponse.Ref,command, returnRequest.getAmount(), returnMiddleResponse.getauthCode(), returnMiddleResponse.getcardType(),
                    "TSYS", returnMiddleResponse.getresponseCode(), returnMiddleResponse.getresponseMessage(),
                    returnMiddleResponse.getstatus(), returnMiddleResponse.gettransactionID(), returnMiddleResponse.gettransactionTimestamp(),
                    command,"","0",0,0, 0,context);
            DBHelper dbHelper=new DBHelper(context);
            Configuration configuration=new Configuration();
            try {
                configuration=dbHelper.getById(Configuration.class,1);
                if(configuration.device_type.equals("APT50")&&saleRequest.getprint().equals("Y")){

                    new PrintThread(Integer.parseInt(returnResponse.ReturnResponse.Ref),context,printer).start();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else {
            Logging.updateTransaction(returnResponse.ReturnResponse.Ref,command, returnRequest.getAmount(), returnMiddleResponse.getauthCode(), returnMiddleResponse.getcardType(),
                    "TSYS", returnMiddleResponse.getresponseCode(), returnMiddleResponse.getresponseMessage(),
                    returnMiddleResponse.getstatus(), returnMiddleResponse.gettransactionID(), returnMiddleResponse.gettransactionTimestamp(),
                    command,"","0",0,0, 0,context);
        }

    }
    /**
     * Prints a prior Ref from the
     * Transaction Table
     */
    public  PrintResponse print(String files, Context context)
    {
        String command="Print";
//        printer = WeiposImpl.as().openPrinter();
        PrintRequest printRequest=new PrintRequest();
        PrintResponse printResponse=new PrintResponse();
        try {
            printRequest = new Gson().fromJson(files, PrintRequest.class);
        }
        catch (Exception e)
        {
            printResponse=new PrintResponse("1","Invalid Parameter");
            return printResponse;
        }
        int ref=printRequest.Print.Ref;
//        Hardware hardware=new Hardware();
//        if(hardware.Print(ref,context,printer))
//        {
//            printResponse=new PrintResponse("0","Success");
//        }
//        else
//        {
//            printResponse=new PrintResponse("2","Failed");
//        }
        return printResponse;
    }

    /**
     * Parses a track into Track1/Track2
     */
     private String[] parseTrack(String track)
     {
         String[] trackdata = track.split("\\|"); // Split off tracks one and two
         String track1 = trackdata[0];
         String track2 = trackdata[1];  // Strip off the ';', which is the second track's leading sentinel.
         return new String[]{ track1, track2 };
     }

    /**
     * Returns the status on a prior Transaction
     */
    public StatusResponse status(String files, Context context)
    {
        String command="Status";
        StatusRequest statusRequest = new StatusRequest();
        try {
            statusRequest = new Gson().fromJson(files, StatusRequest.class);
        }
        catch (Exception e)
        {
            StatusResponse statusResponse=new StatusResponse("1","Invalid Parameter");
            return statusResponse;
        }
        DBHelper dbHelper=new DBHelper(context);
        Transaction transaction = new Transaction();
        Log.d("StatusRef",String.valueOf(statusRequest.Request.Ref));
        try {
            transaction=dbHelper.getById(Transaction.class,statusRequest.Request.Ref);
        } catch (SQLException e) {
            StatusResponse statusResponse=new StatusResponse("2","Can't find data");
            return statusResponse;
        }
        Log.d("Transaction_class",new Gson().toJson(transaction));
//        String qty=(String.valueOf(transaction.qty)==null?"0":String.valueOf(transaction.qty));
        Log.d("Transaction qty",String.valueOf(transaction.qty));
        Log.d("Transaction subtotal",String.valueOf(transaction.subtotal));
        Log.d("Transaction tax",String.valueOf(transaction.tax));
        StatusResponse statusResponse=new StatusResponse(transaction.command,transaction.amount,transaction.auth_code,
                                        transaction.card_payment_type,transaction.processor,transaction.response,
                                        transaction.response_message,transaction.status,transaction.trans_id,
                                        transaction.trans_date,transaction.trans_type,String.valueOf(transaction.qty),
                                        String.valueOf(transaction.subtotal), String.valueOf(transaction.tax));
        return statusResponse;

    }
    /**
     * Searches against the Transaction Database
     * For matching records
     */
    public static  SearchResponse search(String files, Context context)
    {
        String command="Search";
        SearchRequest searchRequest = new SearchRequest();
        SearchResponse searchResponse=new SearchResponse();
        try {
            searchRequest = new Gson().fromJson(files, SearchRequest.class);
        }
        catch (Exception e)
        {
            searchResponse=new SearchResponse("1","Invalid Parameter");
            return searchResponse;
        }
        Log.d("searchRequest",new Gson().toJson(searchRequest));
        String trans_id=searchRequest.Search.trans_id;
        String trans_type=searchRequest.Search.trans_type;
        String start_date=searchRequest.Search.start_date;
        String end_date=searchRequest.Search.end_date;
        ArrayList<Transaction> response=null;
        DBHelper dbHelper=new DBHelper(context);
        if(trans_id!=null&&!trans_id.equals("")){
            response=new ArrayList<>();

            try {
                List<Transaction> alltransactions=dbHelper.getAll(Transaction.class);
                for(int i=0;i<alltransactions.size();i++)
                {
                    String transid=alltransactions.get(i).getTrans_id();
                    if(transid.equals(trans_id)) response.add(alltransactions.get(i));
                }
                Log.d("Search_Response",new Gson().toJson(response));
                searchResponse=new SearchResponse("0","Success",response);
            } catch (SQLException e) {
                e.printStackTrace();
                searchResponse=new SearchResponse("1","Invalid TransactionID Parameter");
            }
            return searchResponse;
        }
        else{
            if(trans_type!=null&&!trans_type.equals("")){
                response=new ArrayList<>();
                Log.d("trans_type","Success");
                try {
                    List<Transaction> alltransactions=dbHelper.getAll(Transaction.class);
                    for(int i=0;i<alltransactions.size();i++)
                    {
                        String transtype=alltransactions.get(i).getTrans_type();
                        if(trans_type.equals(transtype)) response.add(alltransactions.get(i));
                    }
                    searchResponse=new SearchResponse("0","Success",response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    searchResponse=new SearchResponse("1","Invalid TransType Parameter");
                }
            }
            if((start_date!=null&&!start_date.equals(""))|(end_date!=null&&!end_date.equals("")))
            {
                List<Transaction> alltransactions=new ArrayList<>();

                    if (response == null|response.size()<1) {
                        try {
                            response=new ArrayList<>();
                            alltransactions = dbHelper.getAll(Transaction.class);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        alltransactions=response;
                        response=new ArrayList<>();
                    }

                    for (int i = 0; i < alltransactions.size(); i++) {
                        String[] transdatearray;
                        if(alltransactions.get(i).getTrans_date()!=null&&alltransactions.get(i).getTrans_date()!="") {
                            transdatearray = alltransactions.get(i).getTrans_date().split("T");
                            String transdate=transdatearray[0];

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                            try {

                                if(start_date==null|start_date.equals("")) start_date="2000-01-01";
                                if(end_date==null|end_date.equals("")) end_date="2050-01-01";
                                Log.d("trans_string", transdate);
                                Log.d("start_date", start_date);
                                Log.d("start_date", start_date);

                                Date str_startdate = sdf.parse(start_date);
                                Date str_enddate = sdf.parse(end_date);
                                Date str_transdate = sdf.parse(transdate);

                                Log.d("str_transdate", String.valueOf(str_transdate) + "   " + String.valueOf(str_transdate.getTime()));
                                Log.d("str_startdate", String.valueOf(str_startdate) + "   " + String.valueOf(str_startdate.getTime()));
                                Log.d("str_enddate", String.valueOf(str_enddate) + "   " + String.valueOf(str_enddate.getTime()));
                                if ((str_startdate.getTime()-86440000) < str_transdate.getTime() && str_transdate.getTime() < (str_enddate.getTime()+86440000)) {
                                    response.add(alltransactions.get(i));
                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    searchResponse = new SearchResponse("0", "Success", response);
                    return searchResponse;
            }
            else
            { if(trans_type!=null)  return searchResponse;
               else{
                searchResponse=new SearchResponse("1","No Found",null);
                return searchResponse;
            }
            }
        }
    }

    public static ReportResponse report(String files,Context context)
    {
        String deviceID= Profile(context).device.deviceid;
        String trans_key=Profile(context).device.trans_key;

        ReportRequest reportRequest= new ReportRequest();
        ReportResponse reportResponse=new ReportResponse();
        try {
            reportRequest= new Gson().fromJson(files, ReportRequest.class);
            Log.d("Report_Request",new Gson().toJson(reportRequest));
        }
        catch (Exception e)
        {
            reportResponse=new ReportResponse("1","Invalid Parameter",null);
            return reportResponse;
        }

        ReportMiddleRequest reportMiddleRequest=new ReportMiddleRequest(deviceID,trans_key,reportRequest.GetReportData.reportName);
        Gson gson=new GsonBuilder().registerTypeAdapter(ReportMiddleRequest.class,new ReportJsonSerializer()).create();

        String responsedata=null ;
        RequestBody body = RequestBody.create(JSON, gson.toJson(reportMiddleRequest));

        responsedata=post(body);

        Log.d("responsedata",responsedata);
        ReportMiddleResponse reportMiddleResponse=new ReportMiddleResponse();
        reportMiddleResponse =gson.fromJson(responsedata,ReportMiddleResponse.class);
        Log.d("Report_MiddleResponse",new Gson().toJson(reportMiddleResponse));
        String start_date=reportRequest.GetReportData.start_date;
        String end_date=reportRequest.GetReportData.end_date;
        String  error;
        if(reportMiddleResponse.getstatus().equals("PASS"))
        {
            error="0";
            ArrayList<ReportItem> row=reportMiddleResponse.GetReportDataResponse.reportData.ROW;
            ArrayList<ReportItem> reportdata=new ArrayList<>();
            if(start_date==null|start_date.equals("")) start_date="2000-01-01";
            if(end_date==null|end_date.equals("")) end_date="2050-01-01";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            for(int i=0;i<row.size();i++)
            {
                String transdate=row.get(i).batchDate;
                try{
                    Date str_startdate = sdf.parse(start_date);
                    Date str_enddate = sdf.parse(end_date);
                    Date str_transdate = sdf.parse(transdate);

                    Log.d("str_transdate", String.valueOf(str_transdate) + "   " + String.valueOf(str_transdate.getTime()));
                    Log.d("str_startdate", String.valueOf(str_startdate) + "   " + String.valueOf(str_startdate.getTime()));
                    Log.d("str_enddate", String.valueOf(str_enddate) + "   " + String.valueOf(str_enddate.getTime()));
                    if ((str_startdate.getTime()-86440000) < str_transdate.getTime() && str_transdate.getTime() < (str_enddate.getTime()+86440000)) {
                        reportdata.add(row.get(i));
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            reportResponse=new ReportResponse(error,reportMiddleResponse.getresponseMessage(),reportdata);
            return reportResponse;
        }
        else {

            error = reportMiddleResponse.getresponseCode();
            reportResponse = new ReportResponse(error, reportMiddleResponse.getresponseMessage(), null);
            return reportResponse;
        }
    }
    /**
     *  Posts a supplied transaction to TSYS
     *  Returns the response:
     *  NOTE: Prior to going live this must be set to production end point
     */
    public static String  post(RequestBody requestBody) {
        String url="https://stagegw.transnox.com/servlets/TransNox_API_Server/";
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("stagegw.transnox.com", "sha256/EjcbKhwfndYnPsHnfnXt9f10DzII9Eup2iGyhi/PN1Y=")
                .add("stagegw.transnox.com", "sha256/njN4rRG+22dNXAi+yb8e3UMypgzPUPHlv4+foULwl1g=")
                .add("stagegw.transnox.com", "sha256/i7WTqTvh0OioIruIfFR4kMPnBqrS2rdiVPl/s2uC/CY=")
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .certificatePinner(certificatePinner).build();

        Request request = new Request.Builder()
                .header("content-type","application/json")
                .url(url)
                .post(requestBody)
                .build();

        Response response= null;
        String respon = null;
        try {
            response = client.newCall(request).execute();
            respon=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respon;
    }
    /**
     * Generates a new trans_key with TSYS
     *
     */

    public class PrintThread extends Thread {
        Context mcontext;
        int mref;
        Printer mPrinter;
        public PrintThread(int ref,Context context,Printer printer)
        {
            this.mcontext=context;
            this.mref=ref;
            this.mPrinter=printer;
        }
        @Override
        public void run() {

            int result=ToolsUtil.printNormal(mref,mcontext,mPrinter);
            String result_str = null;
            if(result==1) result_str="Parameter Error";
            else if(result==6) result_str="Not available";
            else if(result==138) result_str="Out of Paper";
            else if(result==139) result_str="Overheat";
            if(result_str!=null) {
                Intent intnet = new Intent("Printer Status");
                intnet.putExtra("Status", result_str);
                mcontext.sendBroadcast(intnet);
            }
        }
    }
    public KeyResponse genKey(String files, Context context)
    {
        String command="GenerateKey";
        KeyRequest keyRequest= new KeyRequest();
        KeyResponse keyResponse=new KeyResponse();
        try {
            keyRequest= new Gson().fromJson(files, KeyRequest.class);
        }
        catch (Exception e)
        {
            keyResponse=new KeyResponse("FAIL","1","Invalid Parameter");
            return keyResponse;
        }
        Gson gson = new GsonBuilder().registerTypeAdapter(KeyRequest.class, new KeyJsonSerializer()).create();

        Log.d("testinggson",gson.toJson(keyRequest));
        String responsedata = "";
        RequestBody swipebody =  RequestBody.create(JSON, gson.toJson(keyRequest));

        /* Post request/get response */
        responsedata = post(swipebody);

        Log.d("responsedata", responsedata);

        keyResponse=gson.fromJson(responsedata, KeyResponse.class);
        return keyResponse;
    }

    public static Boolean isDouble(String value)
    {
        try{
            Double.parseDouble(value);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    class ReadMagTask extends Thread {
        private boolean isRun = false;
        private Context context;
        private String command;
        private Boolean ser_status;
        private String amount;
        private ReadMagTask(Context context, String command,Boolean status,String amt)
        {
            this.context=context;
            this.command=command;
            this.ser_status=status;
            this.amount=amt;

        }

        @Override
        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String decodeData=getMagData(context);
            if (decodeData != null && decodeData.length() != 0) {
                System.out.println( decodeData );
                Log.d("DECODE", decodeData);
                updateLogInfo(decodeData,context,command,ser_status);
            }
            else{updateLogInfo("",context,command,ser_status);}

//            updateLogInfo("B4418409417562981^TEAM RESEARCH INC/TEST T^2103201007400000000000583000000|4418409417562981=210320100000583|null",context,command,true);

        }

        public void killsleep()
        {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public String getMagData(Context context) {
        byte[] respdata = new byte[1024];
        int[] resplen = new int[1];
        int retvalue = -1;
        byte[] dataOutStr1=new byte[77];
        int[] dataOut1len=new int[1];
        byte[] dataOutStr2=new byte[38];
        int[] dataOut2len=new int[1];
        byte[] dataOutStr3=new byte[115];
        int[] dataOut3len=new int[1];

        try {
            Log.v(TAG, "CardReader, getMagData(), buzzer--->>>");
            mCore.buzzer();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            retvalue = mBankCard.readCard(BankCard.CARD_TYPE_NORMAL, BankCard.CARD_MODE_MAG, 60, respdata, resplen, "app1");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (respdata[0] == 0x03){
            Intent intnet = new Intent("Swipe Your card now");
            intnet.putExtra("ErrorMsg", "Error, your swipe was incomplete. Please try again");
            context.sendBroadcast(intnet);
            return "";
        }
        else if (respdata[0] == 0x04){
            Intent intnet = new Intent("Swipe Your card now");
            intnet.putExtra("ErrorMsg", "Error, User cancelled credit card swipe! Please try again");
            context.sendBroadcast(intnet);
            return "";
            // Successful read
        }
        else if (respdata[0] == 0x00) {
            int retval = mBankCard.parseMagnetic(respdata, resplen[0], dataOutStr1, dataOut1len, dataOutStr2, dataOut2len, dataOutStr3, dataOut3len);
            byte[] data1 = new byte[dataOut1len[0]];
            for (int i = 0; i < dataOut1len[0]; i++) {
                data1[i] = dataOutStr1[i];
            }
            String parsedata1 = new String(data1);

            byte[] data2 = new byte[dataOut2len[0]];
            for (int i = 0; i < dataOut2len[0]; i++) {
                data2[i] = dataOutStr2[i];
            }
            String parsedata2 = new String(data2);

            byte[] data3 = new byte[dataOut3len[0]];
            for (int i = 0; i < dataOut3len[0]; i++) {
                data3[i] = dataOutStr3[i];
            }
            String parsedata3 = new String(data3);
            String finaldata = parsedata1 + "|" + parsedata2 + "|" + parsedata3;

            Log.v(TAG, "CardReader, getMagData, final data = " + finaldata);


            if (retvalue == 0) {
                return finaldata;
            } else {
                return "";
            }
        }
        else{
            return "";
        }
    }
    private void startTask(final Context context, String command,Boolean server_status,String amount,int ref){
        if(mReadMagTask==null){
            mReadMagTask=new ReadMagTask(context,command,server_status,amount);
            mReadMagTask.start();
            if(server_status) {
                Intent swipeintnet = new Intent("PLEASE SWIPE YOUR CREDIT CARD");
                swipeintnet.putExtra("Amount", amount);
                swipeintnet.putExtra("Ref", ref);
                context.sendBroadcast(swipeintnet);
            }

        }
    }
    private void stopTask(final Context context){

        if(mReadMagTask!=null){
            mReadMagTask.interrupt();
            mReadMagTask=null;
        }
    }

    /**
     * Captures Magnetic card data
     * And passes it onto callback
     */
    private void updateLogInfo(String msg, final Context context,String command,Boolean status) {


        /**
         * MSR Card Read events, action based on
         * Transaction Type
         */
        Log.d("CardReaderResult",msg);
        if (!msg.equals("")) {
            Intent intnet = new Intent("Authorizing Card");
            context.sendBroadcast(intnet);
            Log.d("Broadcast success","True");
            if (command.equals("Auth")) {
                // EXAMPLE OF REAL CARD SWIPE: %B4418409417562981^TEAM RESEARCH INC/TEST T^2103201007400000000000583000000?;4418409417562981=210320100000583?
                // This is what is returned by routine
                // B4418409417562981^TEAM RESEARCH INC/TEST T^2103201007400000000000583000000|4418409417562981=210320100000583|null

                String[] trackdata = parseTrack(msg);
                Log.d("track1", trackdata[0]);
                Log.d("track2", trackdata[1]);

                if (trackdata[0] != null && trackdata[0].length() > 0) {
                    authorizeRequest.Authorize.Track1Data = trackdata[0];
                } else if (trackdata[1] != null && trackdata[1].length() > 0) {
                    authorizeRequest.Authorize.Track2Data = trackdata[1];
                }

                //authorizeRequest.Authorize.Track2Data = msg;
                String files = new Gson().toJson(authorizeRequest);
                authorizeSwipe(files, context, status);

            } else if (command.equals("Sale")) {
                String[] trackdata = parseTrack(msg);
                Log.d("track1", trackdata[0]);
                Log.d("track2", trackdata[1]);
                if (trackdata[0] != null && trackdata[0].length() > 0) {
                    saleRequest.Sale.Track1Data = trackdata[0];
                } else if (trackdata[1] != null && trackdata[1].length() > 0) {
                    saleRequest.Sale.Track2Data = trackdata[1];
                }

                //authorizeRequest.Authorize.Track2Data = msg;
                String files = new Gson().toJson(saleRequest);
                saleSwipe(files, context, status);
            } else if (command.equals("Return")) {
                returnRequest.Return.Track2Data = msg;
                String files = new Gson().toJson(returnRequest);
                returnSwipe(files, context, status);
            }
        }
    }

}
