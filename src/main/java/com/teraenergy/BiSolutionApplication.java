package com.teraenergy;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class BiSolutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiSolutionApplication.class, args);
    }

}
