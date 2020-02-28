package org.qasimovey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import sun.applet.Main;

@SpringBootApplication
//@EnableAsync(proxyTargetClass = true)
public class MainApp {
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class,args);
    }

}
