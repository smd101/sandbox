package com.example.sandbox.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import lombok.NonNull;
import org.apache.commons.io.FilenameUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncService {

  @Async("Thread0")
  public CompletableFuture<String> createZips(String name, Long sleepTime) throws InterruptedException {

    log.warn("Async function started. processName: " + name + " sleepTime: " + sleepTime);

    String fileKeyName = name;

    // どっかぶり
    // String fileNum = String.format("%06d", 999999);

    // ランダムなら当然、衝突する可能性がある
    // int rnd = new Random().nextInt(999999);
    // String fileNum = String.format("%06d", rnd);

    // スレッドIDを使用すれば被らないけど、Integer.MAX まであり得る（スレッドチューニング次第）
    String fileNum = String.format("%06d", Thread.currentThread().getId());

    // zipFile 名がユニークでないと、他のスレッドで上書きされる
    File zipFile = new File(FilenameUtils.getName(("TEST" + fileNum).concat(".zip")));
    log.info("##### {} zipFile.name: {}", name, zipFile.getName());
    // File zipFile = new File(FilenameUtils.getName(fileKeyName.concat(".zip")));
    File inputFile = new File(AsyncService.class.getClassLoader().getResource(fileKeyName.concat(".txt")).getPath());
    log.info("##### {} inputFile.length: {}", name, inputFile.length());
    try (InputStream is = new FileInputStream(inputFile)) {
      ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)),
          Charset.forName("MS932"));
      putZipEntry(zos, is, fileKeyName.concat(".inf"));
      zos.close();
    } catch (Exception e) {
      log.error("failed to create zip file from outputData", e);
    }

    Thread.sleep(sleepTime);

    log.info("##### {} zipFile.length: {}", name, zipFile.length());

    // 非同期処理のハンドリングができるようにCompletableFutureに実際に使いたい返却値をぶっこんで利用する
    return CompletableFuture.completedFuture(name);
  }

  private void putZipEntry(ZipOutputStream zos, InputStream is, String fileName) throws IOException {
    ZipEntry entry = new ZipEntry(fileName);
    zos.putNextEntry(entry);
    try (InputStream iss = new BufferedInputStream(is)) {
      byte[] buf = new byte[1024];
      for (int len = 0; 0 < (len = iss.read(buf));) {
        zos.write(buf, 0, len);
      }
    }
  }

}
