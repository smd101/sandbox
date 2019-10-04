package com.example.sandbox;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptionalTest {


  @Data
  @AllArgsConstructor
  public static class OptionalTestClass {
    private String name;
  }
  /**
   * Optional の isPresent により、取得値を切り分ける.
   */
  @Test
  public void howto_optional_return() {
    Optional<String> notNullOpt = Optional.of("aaa");
    String actual1 = notNullOpt.map(v -> v).orElse(null);
    assertThat(actual1).isEqualTo("aaa");

    Optional<String> nullOpt = Optional.empty();
    String actual2 = nullOpt.map(v -> v).orElse(null);
    assertThat(actual2).isNull();

    String optTest = this.get().map(OptionalTestClass::getName).orElseThrow(() -> new IllegalArgumentException());
    assertThat(optTest).isEqualTo("999");


  }

  public Optional<OptionalTestClass> get() {
    return Optional.ofNullable(new OptionalTestClass("999"));
//    return Optional.empty();
  }


}
