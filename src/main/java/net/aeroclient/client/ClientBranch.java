package net.aeroclient.client;

public enum ClientBranch {
    NORMAL(""),
    EXP("Exp Branch"),
    BETA("Beta Branch"),
    DEV("Dev Branch"),
    ;
    private String branch;
    ClientBranch(String branch) {
        this.branch = branch;
    }

    public String getBranch() {
        return branch;
    }
}
