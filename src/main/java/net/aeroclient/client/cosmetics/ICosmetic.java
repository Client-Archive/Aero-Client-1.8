package net.aeroclient.client.cosmetics;

import net.aeroclient.client.cosmetics.object.CosmeticType;

public interface ICosmetic {
    CosmeticType type();
    String label();
    String icon();
}
