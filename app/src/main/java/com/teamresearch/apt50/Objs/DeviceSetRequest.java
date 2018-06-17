package com.teamresearch.apt50.Objs;

public class DeviceSetRequest {
    public String name;
    public String address;
    public String city;
    public String state;
    public String zipcode;
    public String phone;
    public int deviceid;
    public String device_type;
    public String merchant_id;
    public String processor;
    public int status;
    public String trans_key;
    public DeviceSetRequest(String name,String address,String city,String state,String zipcode,String phone,
                            int deviceid,String device_type,String merchant_id,String processor,int status,String trans_key)
    {
        this.name=name;
        this.address=address;
        this.city=city;
        this.state=state;
        this.zipcode=zipcode;
        this.phone=phone;
        this.deviceid=deviceid;
        this.device_type=device_type;
        this.merchant_id=merchant_id;
        this.processor=processor;
        this.status=status;
        this.trans_key=trans_key;
    }
    public DeviceSetRequest()
    {

    }
}
