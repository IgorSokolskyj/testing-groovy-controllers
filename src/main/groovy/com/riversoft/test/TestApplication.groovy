package com.riversoft.test

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2


@EnableSwagger2
@SpringBootApplication
class TestApplication {

    static void main(String[] args) {
        SpringApplication.run(TestApplication, args)
    }
}
