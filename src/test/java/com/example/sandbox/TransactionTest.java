package com.example.sandbox;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.sandbox.entity.TxEntity;
import com.example.sandbox.repository.TxRepository;
import com.example.sandbox.service.TxOuterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

  @Autowired
  private TxOuterService txOuterService;
  @Autowired
  private TxRepository repository;

  @Ignore
  @Test
  public void Test() {
    try {
      txOuterService.insert();  
    } catch (Throwable e) {
      e.printStackTrace();
    }
    

    List<TxEntity> entities = repository.findAll();
    entities.forEach(e->System.out.println(e));

    assertThat(entities.size()).isEqualTo(3);
  }

}
