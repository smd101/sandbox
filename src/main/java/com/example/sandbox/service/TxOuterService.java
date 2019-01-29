package com.example.sandbox.service;

import com.example.sandbox.entity.TxEntity;
import com.example.sandbox.repository.TxRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TxOuterService {
  @Autowired
  private TxRepository repository;
  @Autowired
  private TxInnerService txInnerService;

  @Transactional
  public void insert() {
    repository.save(TxEntity.builder().value("1").build());
    try {
      txInnerService.save("2");
    } catch (Throwable e) {
      e.printStackTrace();
    }

    for(int i=0;i<2;i++) {
      try {
        txInnerService.saveWithError(String.valueOf("2-"+i));
      } catch (Throwable e) {
        e.printStackTrace();
      }  
    }

    repository.save(TxEntity.builder().value("3").build());

    
    if(false)
      throw new RuntimeException("ERROR 3");

  }

}