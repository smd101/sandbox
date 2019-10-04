package com.example.sandbox;


import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
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


  @Setter
  @Getter
  @AllArgsConstructor
  @EqualsAndHashCode
  @Builder
  public static class SampleDto {
    private String id;
    private String name;
  }

  @Test
  public void logTest() {
    LocalDateTime startTime = LocalDateTime.now();
    log.error("Process timeout has occured. indexNo : {}", 77,
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS").format(startTime));

  }

  @Test
  public void lombokTest() {

//    SampleDto sampleDto = new SampleDto("ID0001", "SAMPLE DTO!!!");
    SampleDto sample1 = SampleDto.builder().id("ID0001").name("SAMPLE DTO!!!").build();
    SampleDto sample2 = SampleDto.builder().id("ID0001").name("SAMPLE DTO!!!").build();

    assertThat(sample1.equals(sample2)).isTrue();

    log.info("##### sampleDto: {}", sample1);
  }

  @Test
  public void Test2() throws Exception {
    List<String> users = asyncManager.createZips();
    log.info("users: {}", users);
  }




  @Test
  public void convertFormDataCheckValue3ToBigDecimalTest() {
    BigDecimal actual = convertFormDataCheckValue3ToBigDecimal("000043020");
    assertThat(actual).isEqualTo(new BigDecimal("43.020"));

    actual = convertFormDataCheckValue3ToBigDecimal("    43020");
    assertThat(actual).isEqualTo(new BigDecimal("43.020"));

    actual = convertFormDataCheckValue3ToBigDecimal("   4302.0");
    assertThat(actual).isEqualTo(new BigDecimal("43.020"));

    actual = convertFormDataCheckValue3ToBigDecimal("   4.3020");
    assertThat(actual).isEqualTo(new BigDecimal("43.020"));

    actual = convertFormDataCheckValue3ToBigDecimal("         ");
    assertThat(actual).isEqualTo(new BigDecimal("0.000"));

  }


  /**
   * 帳票データチェック値3のASP差異吸収用メソッド<br>
   * スペースまたは0埋め、小数点あるなしに関わらず、全ての値をbigdecimalに変換<br>
   * 下3桁は小数点以下の数値であることが前提である。(ex: 000043020 -> 43.02)<br>
   *
   * @param formDataCheckValue3
   * @return
   */
  protected final BigDecimal convertFormDataCheckValue3ToBigDecimal(String formDataCheckValue3) {
    if (formDataCheckValue3 == null) {
      return null;
    }

    int length = formDataCheckValue3.length();

    String beforePointStr = StringUtils.trim(
            formDataCheckValue3.substring(0, length - 3)
                    .replace(".", ""));

    BigDecimal beforePoint;
    if (StringUtils.isEmpty(beforePointStr)) {
      // スペース埋めかつ数値が1以下の場合の対応
      beforePoint = BigDecimal.ZERO;
    } else {
      beforePoint = new BigDecimal(beforePointStr);
    }

    BigDecimal afterPoint = new BigDecimal("0." +
            formDataCheckValue3.substring(length - 3, length).replace(" ", "0"));

    return beforePoint.add(afterPoint);
  }

}
