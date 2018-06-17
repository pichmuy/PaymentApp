package com.teamresearch.apt50.Device;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.teamresearch.apt50.Database.Configuration;
import com.teamresearch.apt50.Database.DBHelper;
import com.teamresearch.apt50.Database.Merchant;


import org.json.JSONObject;

import java.sql.SQLException;
import java.util.Map;

public class Settings {

    public static ProfileResponse Profile(Context context)
    {
        DBHelper dbHelper=new DBHelper(context);
        Merchant merchant=new Merchant();
        Configuration configuration=new Configuration();
        String error="";
        String errormsg="";
        try {
            merchant=dbHelper.getById(Merchant.class,1);
            configuration=dbHelper.getById(Configuration.class,1);
            error="0";
            errormsg="Success";

        } catch (SQLException e) {
            e.printStackTrace();

        }
        if(merchant==null||configuration==null)
        {
            errormsg="Key configuration is missing, please run the /api/deviceset command in order to config the device";
            error="900";
            ProfileResponse profileResponse=new ProfileResponse(error,errormsg);
            return profileResponse;
        }

        ProfileResponse profileResponse=new ProfileResponse(error,errormsg,merchant.name,merchant.address,merchant.city,
                                            merchant.state,merchant.zipcode,merchant.phone,merchant.language,merchant.currency,
                                            String.valueOf(configuration.device_id),configuration.device_type,configuration.merchant_id,
                                            configuration.processor,configuration.status,configuration.trans_key);
        return profileResponse;
    }
    public static ProfileResponse Profile(String files,Context context)
    {
        ProfileRequest profileRequest=new ProfileRequest();
        try {
            profileRequest = new Gson().fromJson(files, ProfileRequest.class);
        }
        catch (Exception e)
        {
            ProfileResponse profileResponse=new ProfileResponse("1","Invalid Request Parameter");
            return profileResponse;
        }
        Log.d("profile",new Gson().toJson(profileRequest));

        if(profileRequest.device.device_type.equals("APT40")||profileRequest.device.device_type.equals("APT50")||profileRequest.device.device_type.equals("APT120"))
        {
            if(profileRequest.device.status==0||profileRequest.device.status==1)
            {
                if(profileRequest.device.processor.equals("TSYS"))
                {
                    Configuration configuration=new Configuration();
                    Merchant merchant=new Merchant();
                    merchant.setName(profileRequest.merchant.name);
                    merchant.setAddress(profileRequest.merchant.address);
                    merchant.setCity(profileRequest.merchant.city);
                    merchant.setState(profileRequest.merchant.state);
                    merchant.setZipcode(profileRequest.merchant.zipcode);
                    merchant.setPhone(profileRequest.merchant.phone);
                    merchant.setId(1);
                    configuration.setDevice_id(profileRequest.device.deviceid);
                    configuration.setDevice_type(profileRequest.device.device_type);
                    configuration.setMerchant_id(profileRequest.device.merchant_id);
                    configuration.setProcessor(profileRequest.device.processor);
                    configuration.setStatus(profileRequest.device.status);
                    if(profileRequest.device.trans_key!=null)
                    {
                        configuration.setTrans_key(profileRequest.device.trans_key);
                    }
                    else {

                    }
                    configuration.setId(1);
                    DBHelper dbHelper=new DBHelper(context);

                    try {
                        dbHelper.createOrUpdate(merchant);
                        dbHelper.createOrUpdate(configuration);
                        ProfileResponse profileResponse=new ProfileResponse("0","Success");
                        return profileResponse;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ProfileResponse profileResponse=new ProfileResponse("1","Failed");
        return profileResponse;

    }
}
