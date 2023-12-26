package com.ll;

public class Calc {

  public static int run(String exp) {

    String[] bits = exp.split(" \\+ ");
    String[] bits2 = exp.split(" - ");

    if (bits.length == 2) {
      int a = Integer.parseInt(bits[0]);
      int b = Integer.parseInt(bits[1]);
      return a + b;
    }

    if (bits2.length == 2) {
      int c = Integer.parseInt(bits2[0]);
      int d = Integer.parseInt(bits2[1];
      return c - d;
    }

    return 0;
  }
}

