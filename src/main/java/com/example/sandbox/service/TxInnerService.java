package com.example.sandbox.service;


import com.example.sandbox.entity.TxEntity;
import com.example.sandbox.repository.TxRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TxInnerService {
  @Autowired
  private TxRepository repository;

  // @Transactional(propagation = Propagation.REQUIRES_NEW)
  public TxEntity save(String value) throws Exception {
      TxEntity entity = repository.save(TxEntity.builder().value(value).build());

      return entity;
  }

  public TxEntity saveWithError(String value) throws Exception {
    TxEntity entity = repository.save(TxEntity.builder().value(value).build());

    if(true)
      throw new RuntimeException("ERROR " + value);

    return entity;
}

}