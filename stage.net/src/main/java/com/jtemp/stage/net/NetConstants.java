package com.jtemp.stage.net;

import java.nio.charset.Charset;

/**
 * @author ZMS
 * @Date 2018/11/8 10:38 AM
 */
public final class NetConstants {

    /**
     * 链接超时时间
     */
    public static final String KEY_CONNECT_TIMEOUT = "CONNECT_TIMEOUT";
    public static final String KEY_CONNECT_SYNC = "CONNECT_SYNC";

    /**
     * 默认链接超时时间
     */
    public static final int DEF_CONNECTION_TIMEOUT = 5000;
    public static final boolean DEF_CONNECTION_SYNC = true;

    /**
     * 默认字符集
     */
    public static Charset UTF8 = Charset.forName("UTF-8");

}
