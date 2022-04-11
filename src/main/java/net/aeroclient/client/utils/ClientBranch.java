package net.aeroclient.client.utils;

public enum ClientBranch {
    NORMAL(""),
    EXP("Exp Branch"),
    BETA("Beta Branch"),
    DEV("Dev Branch");

    private String branch;

    private ClientBranch(String branch) {
        this.branch = branch;
    }

    public String getBranch() {
        return this.branch;
    }
}
