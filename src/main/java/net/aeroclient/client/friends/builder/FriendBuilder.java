package net.aeroclient.client.friends.builder;

import net.aeroclient.client.friends.Friend;
import net.aeroclient.client.friends.objects.EnumFriendStatus;

public class FriendBuilder
{
    private String playerId;
    private String name;
    private String status;
    private String server;
    private boolean online;
    private long offlineSince;
    private EnumFriendStatus friendStatus;

    FriendBuilder(String name) {
        this.name = name;
    }

    public FriendBuilder playerId(final String playerId) {
        this.playerId = playerId;
        return this;
    }

    public FriendBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public FriendBuilder status(final String status) {
        this.status = status;
        return this;
    }

    public FriendBuilder server(final String server) {
        this.server = server;
        return this;
    }

    public FriendBuilder online(final boolean online) {
        this.online = online;
        return this;
    }

    public FriendBuilder offlineSince(final long offlineSince) {
        this.offlineSince = offlineSince;
        return this;
    }

    public FriendBuilder FriendStatus(final EnumFriendStatus friendStatus) {
        this.friendStatus = friendStatus;
        return this;
    }

    public Friend name() {
        return new Friend(this.playerId, this.name, this.status, this.server, this.online, this.offlineSince, this.friendStatus);
    }

    @Override
    public String toString() {
        return "Friend.FriendBuilder(playerId=" + this.playerId + ", name=" + this.name + ", status=" + this.status + ", server=" + this.server + ", online=" + this.online + ", offlineSince=" + this.offlineSince + ", onlineStatus=" + this.friendStatus + ")";
    }
}
