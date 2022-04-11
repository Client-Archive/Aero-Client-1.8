import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;

import net.minecraft.client.main.Main;

public class Start
{
    public static void main(String[] args)
    {
        final File file = new File(new File(System.getenv("APPDATA")), ".minecraft");
        Main.main((String[])concat(new String[] {"--version", "Aero Client",
                "--accessToken", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1MDI5MTE2NDZjMGQyMTMwMjkxMjAxMmE0Zjk4MThiYSIsInlnZ3QiOiI4ZjdiNDk0NWM4YjE0MDkxYjQ3MTFhYzg0OWQ2MTU0MiIsInNwciI6IjEzZDMyNDU3MzZmNDQ1NDZiMjc5NDgzNTFkZTY1NmFhIiwiaXNzIjoiWWdnZHJhc2lsLUF1dGgiLCJleHAiOjE2MTUzMTMxODEsImlhdCI6MTYxNTE0MDM4MX0.Qb12ZisNTL3c76BN7VROQ8o8_5qIbo09ndVxTvDDZPM", "--assetIndex", "" +
                "1.8.9", "--userProperties", "{}", "--gameDir", file.getAbsolutePath(), "--assetsDir",
                new File(file, "assets").getAbsolutePath(), "--username", "StayOffMyLane", "--uuid", "13d32457-36f4-4546-b279-48351de656aa"}, args));
    }

    public static <T> T[] concat(T[] first, T[] second)
    {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
