package concurrent;

import java.time.LocalDateTime;

import static java.lang.String.format;

public class Utils {

    public static void threadContextPrint(String msg){
        System.out.println(format("%s : %s : %s", LocalDateTime.now(), Thread.currentThread().getName(), msg));
    }
}
