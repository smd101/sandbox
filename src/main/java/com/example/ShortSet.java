package com.example;

import java.util.Set;
import java.util.HashSet;

public class ShortSet {
  public static void main (String[] args) {
    Set<Short> s = new HashSet<>();
    for (short i = 0; i < 100; i++) {
      s.add(i);
      s.remove(i - 1);
    new RuntimeException();
    }
    System.out.println(s.size());
  }

  public void noError() {
    Exception e = new RuntimeException("stored");
    e = new UnsupportedOperationException("also stored");
    throw new IllegalArgumentException("thrown");
  }

  public Exception returnsException() {
    return new RuntimeException("returned");
  }

}