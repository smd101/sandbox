package com.example.sandbox.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncManager {

  @Autowired
  private AsyncService asyncService;

  public List<String> findUsers() throws Exception {
      long start = System.currentTimeMillis();
      long heavyProcessTime = 3000L;
      long lightProcessTime = 1000L;

      log.warn("request started");
      List<CompletableFuture<String>> processes = new ArrayList<>();
      for(int i = 0; i<50; i++) {
        CompletableFuture<String> process = asyncService.findName("heavy", (i+1) * 100L);    
        processes.add(process);
      }
    //   CompletableFuture<String> heavyProcess = asyncService.findName("heavy", heavyProcessTime);
    //   CompletableFuture<String> lightProcess = asyncService.findName("light", lightProcessTime);

    //   // heavyProcessが終わったら実行される処理
    //   heavyProcess.thenAcceptAsync(heavyProcessResult -> {
    //       log.warn("finished name=" + heavyProcessResult + ", sleep = " + heavyProcessTime);
    //   });

    //   // lightProcessが終わったら実行される処理
    //   lightProcess.thenAcceptAsync(lightProcessResult -> {
    //       log.warn("finished name=" + lightProcessResult + ", sleep = " + lightProcessTime);
    //   });

    //   // 指定した処理が終わったらこれ以降の処理が実行される
    //   CompletableFuture.allOf(heavyProcess, lightProcess).join();
    CompletableFuture.allOf(processes.toArray(new CompletableFuture[processes.size()])).join();

    // 返却値の作成
    List<String> names = new ArrayList<>();
    for(CompletableFuture<String> process : processes) {
        names.add(process.get());
    }

      Thread.sleep(10L);

      long end = System.currentTimeMillis();
      // 処理全体の時間を出力
      log.warn("request finished. time: " + ((end - start))  + "ms");

      return names;
  }

}
