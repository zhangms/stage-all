package com.jtemp.stage.net;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZMS
 * @Date 2018/10/13 11:42 AM
 */
public class NetURL {

    /**
     * server address
     */
    private String host;

    /**
     * server port
     */
    private int port;

    /**
     * server params
     */
    private Map<String, String> parameters = new HashMap<>();

    public String getHost() {
        return host;
    }

    public NetURL setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public NetURL setPort(int port) {
        this.port = port;
        return this;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public NetURL setParameters(String key, String value) {
        if (key == null) {
            return this;
        }
        if (value != null) {
            this.parameters.put(key, value);
        } else {
            this.parameters.remove(key);
        }
        return this;
    }
}
