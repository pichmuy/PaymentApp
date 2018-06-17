package com.teamresearch.apt50.Objs;

public  class KeyRequest {
    public generateKey GenerateKey;
    public class generateKey
    {
        public String mid;
        public String userID;
        public String password;
        public generateKey(String mid,String userID,String password)
        {
            this.mid=mid;
            this.userID=userID;
            this.password=password;
        }
    }
    public KeyRequest(String mid,String userID,String password)
    {
        generateKey key=new generateKey(mid,userID,password);
        this.GenerateKey=key;
    }
    public KeyRequest()
    {

    }
    public String getmid()
    {
        return this.GenerateKey.mid;
    }
    public String getuserID()
    {
        return this.GenerateKey.userID;
    }
    public String getpassword()
    {
        return this.GenerateKey.password;
    }
}