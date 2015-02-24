package com.myapp.objects;

/**
 * Created by megatron on 15/2/19.
 */
public class Msg {
    private String msg;

    private boolean bSend;

    public Msg(String msg,boolean bSend){
        this.msg = msg;
        this.bSend = bSend;
    }

    public String getMsg(){return msg;}

    public boolean isSend(){return bSend;}
}
