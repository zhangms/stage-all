package com.jtemp.stage.net.netty;

import com.jtemp.stage.common.thread.NamedThreadFactory;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @author ZMS
 * @Date 2018/10/13 4:11 PM
 */
public final class NettyHelper {

    private static final String SYS_NETTY_WORKER_COUNT = "netty.workers";
    private static final String SYS_NETTY_DAEMON = "netty.daemon";
    private static final String SYS_NETTY_BYTEBUF_POOLED = "netty.bytebuf.pooled";

    private static EventLoopGroup workerEventLoopGroup;

    private static ByteBufAllocator byteBufAllocator;

    static synchronized EventLoopGroup workerEventLoopGroup() {
        if (workerEventLoopGroup == null) {
            workerEventLoopGroup = new NioEventLoopGroup(workerThreadsCount(),
                new NamedThreadFactory("netty-worker", isDaemon()));
        }
        return workerEventLoopGroup;
    }

    private static int workerThreadsCount() {
        return SystemPropertyUtil.getInt(SYS_NETTY_WORKER_COUNT, Runtime.getRuntime().availableProcessors() * 2);
    }

    public static boolean isDaemon() {
        return SystemPropertyUtil.getBoolean(SYS_NETTY_DAEMON, false);
    }

    static synchronized ByteBufAllocator byteBufAllocator() {
        if (byteBufAllocator == null) {
            if (SystemPropertyUtil.getBoolean(SYS_NETTY_BYTEBUF_POOLED, false)) {
                byteBufAllocator = PooledByteBufAllocator.DEFAULT;
            } else {
                byteBufAllocator = UnpooledByteBufAllocator.DEFAULT;
            }
        }
        return byteBufAllocator;
    }
}
