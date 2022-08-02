package com.ernest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

}
