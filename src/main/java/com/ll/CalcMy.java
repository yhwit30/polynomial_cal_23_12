package com.ll;

public class CalcMy {
  public static int run1(String exp) {

    String[] bits = exp.split(" \\+ ");
    String[] bits2 = exp.split(" - ");

    if (bits.length == 2) {
      int a = Integer.parseInt(bits[0]);
      int b = Integer.parseInt(bits[1]);
      return a + b;
    }

    if (bits.length == 3) {
      int a = Integer.parseInt(bits[0]);
      int b = Integer.parseInt(bits[1]);
      int c = Integer.parseInt(bits[2]);
      return a + b + c;
    }

    if (bits2.length == 2) {
      int c = Integer.parseInt(bits2[0]);
      int d = Integer.parseInt(bits2[1]);
      return c - d;
    }

    return 0;
  }
}

