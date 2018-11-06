package com.jtemp.stage.net.protocol;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZMS
 * @Date 2018/10/20 5:30 PM
 */
public final class NetProtocolManager {

    private static Map<Integer, Class<? extends NetProtocol>> protocols = Collections.synchronizedMap(new HashMap<>());

    /**
     * 创建
     *
     * @param commandId
     * @return
     */
    public static NetProtocol createProtocol(int commandId) throws IllegalAccessException, InstantiationException {
        Class<? extends NetProtocol> clazz = protocols.get(commandId);
        if (clazz == null) {
            return null;
        }
        return clazz.newInstance();
    }

    /**
     * 注册
     *
     * @param commandId
     * @param clazz
     */
    public static void regiester(int commandId, Class<? extends NetProtocol> clazz) {

        protocols.put(commandId, clazz);
    }
}
