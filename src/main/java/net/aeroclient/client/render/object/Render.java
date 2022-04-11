package net.aeroclient.client.render.object;

public class Render {
    String text;
    int x;
    int y;
    int color;
    boolean shadow;
    boolean chroma;



    public Render(String text, int x, int y, int color, boolean shadow, boolean chroma) {
        this.text = text;
        this.x = x; this.y = y;
        this.color = color;
        this.shadow = shadow;
        this.chroma = chroma;
    }

    public String getText() {
        return text;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    public boolean isShadow() {
        return shadow;
    }

    public boolean isChroma() {
        return chroma;
    }
}
