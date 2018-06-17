package com.teamresearch.apt50.Device;

import android.provider.ContactsContract;

public class ProfileResponse {
    public String Error;
    public String ErrorMsg;
    public Merchant merchant;
    public Device device;
    public class Merchant
    {
        public String name;
        public String address;
        public String city;
        public String state;
        public String zipcode;
        public String phone;
        public int language;
        public int currency;
        public Merchant(String name,String address,String city,String state,String zipcode,String phone,
                        int language,int currency)
        {
            this.name=name;
            this.address=address;
            this.city=city;
            this.state=state;
            this.zipcode=zipcode;
            this.phone=phone;
            this.language=language;
            this.currency=currency;

        }
    }
    public class Device
    {
        public String deviceid;
        public String device_type;
        public String merchant_id;
        public String processor;
        public int status;
        public String trans_key;

        public Device(String deviceid,String device_type,String merchant_id,String processor,
                      int status,String trans_key)
        {
            this.deviceid=deviceid;
            this.device_type=device_type;
            this.merchant_id=merchant_id;
            this.processor=processor;
            this.status=status;
            this.trans_key=trans_key;
        }
    }
    public ProfileResponse(String error, String errorMsg, String name, String address, String city, String state, String zipcode,
                           String phone, int language, int currency,String deviceid,String device_type,String merchant_id,
                           String processor,int status,String trans_key)
    {
        Merchant merchant=new Merchant(name, address, city, state, zipcode, phone, language, currency);
        Device device=new Device(deviceid, device_type, merchant_id, processor, status, trans_key);
        this.Error=error;
        this.ErrorMsg=errorMsg;
        this.merchant=merchant;
        this.device=device;

    }
    public ProfileResponse(String error,String errorMsg)
    {
        this.Error=error;
        this.ErrorMsg=errorMsg;
    }
}
