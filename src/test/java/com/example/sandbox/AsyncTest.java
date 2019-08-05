package com.example.sandbox;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.sandbox.service.AsyncManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTest {

  @Autowired
  private AsyncManager asyncManager;


  @Test
  public void Test2() throws Exception {
    List<String> users = asyncManager.createZips();
    log.info("users: {}", users);
  }


}
