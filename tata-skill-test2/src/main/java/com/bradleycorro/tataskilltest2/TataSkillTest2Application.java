package com.bradleycorro.tataskilltest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bradleycorro.tataskilltest2", "com.bradleycorro.tataskilltest2.shared", "com.bradleycorro.tataskilltest2.clientes"})
public class TataSkillTest2Application {

    public static void main(String[] args) {
        SpringApplication.run(TataSkillTest2Application.class, args);
    }

}
