package net.aeroclient.client.friends;

import com.google.gson.JsonParser;
import net.aeroclient.client.friends.builder.FriendBuilder;
import net.aeroclient.client.friends.objects.EnumFriendStatus;
import net.aeroclient.client.utils.ColorCodes;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class Friend {
    private String playerId;
    private String name;
    private String status;
    private String server;
    private boolean online;
    private long offlineSince;
    private EnumFriendStatus friendStatus;

    public String getInfo() {
        String s = null;
        if (this.online) {
            if (this.server != null && !Objects.equals(this.server, "")) {
                s = "Playing" + ColorCodes.BOLD + " " + this.server;
            }
            else {
                s = friendStatus.getName();
            }
        }
        else {
            final long n = System.currentTimeMillis() - this.offlineSince;
            final long n2 = 1000L * 60L;
            final long n3 = n2 * 60L;
            final long n4 = n3 * 24L;
            final long lng = n / n4;
            final long n5 = n % n4;
            final long lng2 = n5 / n3;
            final long lng3 = n5 % n3 / n2;
            if (lng > 0L) {
                s = "Offline for " + lng + ((lng == 1L) ? " day" : " days");
            }
            else if (lng2 > 0L) {
                s = "Offline for " + lng2 + ((lng2 == 1L) ? " hour" : " hours");
            }
            else {
                s = "Offline for " + lng3 + ((lng3 == 1L) ? " minute" : " minutes");
            }
        }
        return s;
    }

    @ConstructorProperties({ "playerId", "name", "status", "server", "online", "offlineSince", "onlineStatus" })
    public Friend(final String playerId, final String name, final String status, final String server, final boolean online, final long offlineSince, final EnumFriendStatus friendStatus) {
        this.friendStatus = EnumFriendStatus.OFFLINE;
        this.playerId = playerId;
        this.name = name;
        this.status = status;
        this.server = server;
        this.online = online;
        this.offlineSince = offlineSince;
        this.friendStatus = friendStatus;
    }


    public String getPlayerId() {
        return this.playerId;
    }

    public String getName() {
        return this.name;
    }

    public String getStatus() {
        return this.status;
    }

    public String getServer() {
        return this.server;
    }

    public boolean isOnline() {
        return this.online;
    }

    public long getOfflineSince() {
        return this.offlineSince;
    }

    public EnumFriendStatus getFriendColor() {
        return this.friendStatus;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setServer(final String server) {
        this.server = server;
    }

    public void setOnline(final boolean online) {
        this.online = online;
    }

    public void setOfflineSince(final long offlineSince) {
        this.offlineSince = offlineSince;
    }

    public void setFriendColor(final EnumFriendStatus friendStatus) {
        this.friendStatus = friendStatus;
    }

}

