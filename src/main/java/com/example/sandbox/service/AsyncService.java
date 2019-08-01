package com.example.sandbox.service;

import java.io.File;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.io.FilenameUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncService {

    @Async("Thread2")
    public CompletableFuture<String> findName(String name, Long sleepTime) throws InterruptedException {
      
      log.warn("Async function started. processName: " + name + "sleepTime: " + sleepTime);
      Thread.sleep(sleepTime);

      File zipFile = new File(FilenameUtils.getName("MIT999999".concat(".zip")));

      // 非同期処理のハンドリングができるようにCompletableFutureに実際に使いたい返却値をぶっこんで利用する
      return CompletableFuture.completedFuture(name);
    }

}

