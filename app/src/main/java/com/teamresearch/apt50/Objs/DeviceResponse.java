package com.teamresearch.apt50.Objs;

public class DeviceResponse {

    public int error;
    public String errormsg;
    public Merchant merchant;
    public Device device;

    class Merchant{
        public String name;
        public String address;
        public String city;
        public String state;
        public String zipcode;
        public String phone;
        public int language;
        public int currency;
    }
    class Device {
        public int deviceid;
        public String device_type;
        public String merchant_id;
        public String processor;
        public int status;
        public String trans_key;
    }

}
