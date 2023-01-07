package _1_basic._7_hook_thread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zy
 * @date 2023/1/7 14:59
 */
public class PreventDuplicated {
    private final static String LOCK_PATH = "src/_1_basic/_7_hook_thread/";
    private final static String LOCK_FILE = ".lock";
    private final static String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException {
        checkRunning();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The program received kill SIGNAL.");
            getLockFile().toFile().delete();
        }));

        for( ; ;) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("the program is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkRunning() throws IOException{
        Path path = getLockFile();
        if(path.toFile().exists()) {
            throw new RuntimeException("the program alreaty is running.");
        }
        //Set<PosixFilePermission> posixFilePermissions = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path);
    }

    private static Path getLockFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }
}
