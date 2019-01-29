package com.example.sandbox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {

  @AllArgsConstructor
  @Data
  public static class Value {
    private String message;
  }


  @Test
  public void Test() {
    Value val = null;//new Value("TEST MESSAGE.");
    log.info("#### log sample: {}", val);
  }

  
  @Test
  public void ErrorMessageTest() {
    try {
      throw new RuntimeException("Runtime Message!!!!!");
    } catch (Throwable e) {
      log.error("getErrorMessage!!: {}", e.getMessage());
      log.error("Error occurd!", e);
    }
    
  }

}
