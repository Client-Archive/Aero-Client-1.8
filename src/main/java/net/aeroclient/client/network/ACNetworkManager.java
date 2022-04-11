package net.aeroclient.client.network;

import io.netty.buffer.Unpooled;
import net.aeroclient.client.network.server.ACPacket;
import net.aeroclient.client.utils.ByteBufWrapper;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import org.apache.commons.io.Charsets;

public class ACNetworkManager {


    public void handlePackets(S3FPacketCustomPayload customPayload) {
        String channel = customPayload.getChannelName();
        PacketBuffer packetBuffer = customPayload.getBufferData();
        if (channel.equalsIgnoreCase("Lunar-Client")) {
            ACPacket.handlePacket(packetBuffer);
        }
    }

    public void sendJoinPackets(NetworkManager networkManager) {
        ByteBufWrapper acwrapper = new ByteBufWrapper(Unpooled.buffer());
        ByteBufWrapper lcwrapper = new ByteBufWrapper(Unpooled.buffer());
        ByteBufWrapper cbwrapper = new ByteBufWrapper(Unpooled.buffer());
        acwrapper.writeBytes("AC-Client".getBytes(Charsets.UTF_8));
        cbwrapper.writeBytes("CB-Client".getBytes(Charsets.UTF_8));
        lcwrapper.writeBytes("Lunar-Client".getBytes(Charsets.UTF_8));
        System.out.println("Sending Register Packet");
        networkManager.sendPacket(new C17PacketCustomPayload("REGISTER",
                new PacketBuffer(acwrapper)));
        networkManager.sendPacket(new C17PacketCustomPayload("REGISTER",
                new PacketBuffer(cbwrapper)));
        networkManager.sendPacket(new C17PacketCustomPayload("REGISTER",
                new PacketBuffer(lcwrapper)));
    }


}
