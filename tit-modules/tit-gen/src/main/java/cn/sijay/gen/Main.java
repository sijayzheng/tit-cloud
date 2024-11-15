package cn.sijay.gen;

import java.io.File;

/**
 * <strong>Main</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-30
 */
public class Main {
    public static void main(String[] args) {
        String[] paths = {
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-doc",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-encrypt",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-idempotent",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-json",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-log",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-mail",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-mybatis",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-ratelimiter",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-redis",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-satoken",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-security",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-sms",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-social",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-translation",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-web",
                "C:\\Users\\sijay\\code\\corpCode\\grup\\ruoyi-common-websocket",
        };
        for (String path : paths) {
            new File(path + "\\src\\main\\resources\\META-INF\\spring\\org.springframework.boot.autoconfigure.AutoConfiguration.imports").deleteOnExit();
//            try (FileInputStream fis = new FileInputStream(path + "\\src\\main\\resources\\META-INF\\spring\\org.springframework.boot.autoconfigure.AutoConfiguration.imports")) {
//                System.out.println(new String(fis.readAllBytes()));
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}
