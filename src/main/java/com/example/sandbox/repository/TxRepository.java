package com.example.sandbox.repository;


import com.example.sandbox.entity.TxEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TxRepository extends JpaRepository<TxEntity, Long> {
}