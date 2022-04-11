package net.aeroclient.client.network.websocket.packets;

import io.netty.buffer.ByteBuf;
import net.aeroclient.client.utils.ByteBufWrapper;
import net.minecraft.network.PacketBuffer;

import java.util.HashMap;

public abstract class WSPacket {
    public static HashMap<Class<? extends WSPacket>, Integer> REGISTRY;

    public abstract void write(ByteBufWrapper out);

    public abstract void read(ByteBufWrapper in);

    public abstract void process(ByteBufWrapper in);

    protected void writeBlob(ByteBuf byteBuf, byte[] arrby) {
        byteBuf.writeShort(arrby.length);
        byteBuf.writeBytes(arrby);
    }

    protected byte[] readBlob(ByteBuf byteBuf) {
        short s = byteBuf.readShort();
        if (s >= 0) {
            byte[] arrby = new byte[s];
            byteBuf.readBytes(arrby);
            return arrby;
        }
        System.out.println("[WS] Key was smaller than nothing!  Weird key!");
        return new byte[0];
    }



    public static void handlePacket(PacketBuffer packet) {
        int PacketId = packet.readVarIntFromBuffer();

    }



}
