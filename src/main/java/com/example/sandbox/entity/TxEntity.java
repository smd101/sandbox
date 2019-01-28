package com.example.sandbox.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tst_tx_entity")
public class TxEntity {
  @Id
  @GeneratedValue
  private Long id;
  private String value;
}