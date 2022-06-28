package com.cheng.system;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrafficSystemApplicationTests {
    Logger logger=LoggerFactory.getLogger(this.getClass());
    @Test
    void contextLoads() {
    logger.error("test logger");
    }

}
