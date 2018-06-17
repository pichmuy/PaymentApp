package com.teamresearch.apt50.Objs;

public class PrintRequest {
    public Print Print;
    public class Print {
        public int Ref;
        public Print(int ref)
        {
            this.Ref=ref;
        }
    }
    public PrintRequest(int ref)
    {
        Print print=new Print(ref);
        this.Print=print;
    }
    public PrintRequest()
    {

    }
}
