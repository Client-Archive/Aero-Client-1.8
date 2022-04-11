package net.aeroclient.client.network.websocket;

import io.netty.buffer.Unpooled;
import net.aeroclient.client.AeroClient;
import net.aeroclient.client.utils.ByteBufWrapper;
import net.aeroclient.client.utils.ComputerUtils;
import net.minecraft.client.Minecraft;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class ACWebSocketClient extends WebSocketClient {
    public ACWebSocketClient(URI ip, Map<String, String> var2) {
        super(ip, new Draft_6455(), var2, 30000);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("[AC] [WS] Connected to WebSocket");
    }

    @Override
    public void onMessage(String s) {
        System.out.println("Message: " + s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("[AC] [WS] Disconnected to WebSocket. With code: " + i + " Reason: " + s);

    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        System.out.println(new ByteBufWrapper(Unpooled.wrappedBuffer((byte[])bytes.array())).readVarInt());
        super.onMessage(bytes);
    }

}
