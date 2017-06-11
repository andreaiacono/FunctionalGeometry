package sample;

import java.io.File;
import java.nio.file.Files;

/**
 *
 */
public class Utils {

    public static String readResource(String resourcePath) throws Exception {
        File file = new File(Utils.class.getResource(resourcePath).getFile());
        return new String(Files.readAllBytes(file.toPath()));
    }
}
