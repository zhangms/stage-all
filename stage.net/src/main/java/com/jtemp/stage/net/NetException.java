package com.jtemp.stage.net;

/**
 * @author ZMS
 * @Date 2018/11/6 2:51 PM
 */
public class NetException extends Exception {

    public NetException() {
    }

    public NetException(String message) {
        super(message);
    }

    public NetException(String message, Exception cause) {
        super(message, cause);
    }
}
