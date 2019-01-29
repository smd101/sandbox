package com.example.sandbox;

import java.util.List;

import com.example.sandbox.entity.TxEntity;
import com.example.sandbox.repository.TxRepository;
import com.example.sandbox.service.TxOuterService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

  @Autowired
  private TxOuterService txOuterService;
  @Autowired
  private TxRepository repository;

  @Test
  public void Test() {
    try {
      txOuterService.insert();  
    } catch (Throwable e) {
      e.printStackTrace();
    }
    

    List<TxEntity> entities = repository.findAll();
    System.out.println(entities.size());
    entities.forEach(e->System.out.println(e));

    // assertThat(entities.size()).isEqualTo(3);
  }

}
