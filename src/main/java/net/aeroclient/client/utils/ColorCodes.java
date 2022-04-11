package net.aeroclient.client.utils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.Map;

// 
// Decompiled by Procyon v0.5.36
// 

public enum ColorCodes
{
    BLACK("BLACK", 0, '0'), 
    DARK_BLUE("DARK_BLUE", 1, '1'), 
    DARK_GREEN("DARK_GREEN", 2, '2'), 
    DARK_AQUA("DARK_AQUA", 3, '3'), 
    DARK_RED("DARK_RED", 4, '4'), 
    DARK_PURPLE("DARK_PURPLE", 5, '5'), 
    GOLD("GOLD", 6, '6'), 
    GRAY("GRAY", 7, '7'), 
    DARK_GRAY("DARK_GRAY", 8, '8'), 
    BLUE("BLUE", 9, '9'), 
    GREEN("GREEN", 10, 'a'), 
    AQUA("AQUA", 11, 'b'), 
    RED("RED", 12, 'c'), 
    LIGHT_PURPLE("LIGHT_PURPLE", 13, 'd'), 
    YELLOW("YELLOW", 14, 'e'), 
    WHITE("WHITE", 15, 'f'), 
    OBFUSCATED("OBFUSCATED", 16, 'k'),
    BOLD("BOLD", 17, 'l'),
    STRIKETHROUGH("STRIKETHROUGH", 18, 'm'),
    UNDERLINE("UNDERLINE", 19, 'n'),
    ITALIC("ITALIC", 20, 'o'),
    RESET("RESET", 21, 'r');
    String name;
    int id;
    char code;

    


    ColorCodes(String name, int id, char code) {
        this.name = name;
        this.id = id;
        this.code = code;
    }

}
