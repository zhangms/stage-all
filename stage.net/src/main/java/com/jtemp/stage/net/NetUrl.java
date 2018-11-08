package com.jtemp.stage.net;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZMS
 * @Date 2018/10/13 11:42 AM
 */
public class NetUrl {

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
    private Map<String, Object> parameters = new HashMap<>();

    public String getHost() {
        return host;
    }

    public NetUrl setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public NetUrl setPort(int port) {
        this.port = port;
        return this;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public int getParameter(String key, int defaultValue) {
        Object val = parameters.get(key);
        return val == null ? defaultValue : Integer.parseInt(val.toString());
    }

    public boolean getParameter(String key, boolean defaultValue) {
        Object val = parameters.get(key);
        return val == null ? defaultValue : Boolean.parseBoolean(val.toString());
    }

    public NetUrl setParameters(String key, Object value) {
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
