package com.jtemp.stage.net;

/**
 * @author ZMS
 * @Date 2018/10/13 10:35 AM
 */
public interface Client {

    void connect(NetURL url);

    void close();

}
