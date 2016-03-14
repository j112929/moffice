package com.plocc;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by xiaojiang on 2016-01-04.
 */
@SpringBootApplication(scanBasePackages = {"com.plocc.im", "com.plocc.moffice","com.plocc.framework"})
@ImportResource("classpath*:/spring/application-*.xml")
public class Startup {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Startup.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
