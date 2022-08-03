package com.ernest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@SpringBootTest
@Slf4j
class TransDemoApplicationTests {

    @Test
    void contextLoads() {
        log.error("123");
        log.info("456");
        log.debug("789");
        log.warn("12345");
    }

    @Test
    void pwdTest() {
        String password = "1";
        String salt = UUID.randomUUID().toString().toUpperCase();
        int nums = 3;
        for (int i = 0; i < nums; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        System.out.println(password);
        System.out.println(salt);
    }

}
