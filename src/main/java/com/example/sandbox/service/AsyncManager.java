package com.example.sandbox.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncManager {

  @Autowired
  private AsyncService asyncService;

  public List<String> createZips() throws Exception {
    long start = System.currentTimeMillis();

    log.warn("request started");
    List<CompletableFuture<String>> processes = new ArrayList<>();
    for(int i = 0; i<10; i++) {
    CompletableFuture<String> process = asyncService.createZips("process-" + i, (i+1) * 100L);    
    processes.add(process);
    }

    CompletableFuture.allOf(processes.toArray(new CompletableFuture[processes.size()])).join();

    // 返却値の作成
    List<String> names = new ArrayList<>();
    for(CompletableFuture<String> process : processes) {
        process.thenAcceptAsync(processResult -> log.warn("finished name=" + processResult));
        names.add(process.get());
    }

    Thread.sleep(10L);

    long end = System.currentTimeMillis();
    // 処理全体の時間を出力
    log.warn("request finished. time: " + ((end - start))  + "ms");

    return names;
  }

}
