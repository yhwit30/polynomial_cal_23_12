package com.ll;

public class Calc {
  public static int run(String exp) {

    exp = exp.replaceAll("- ", "\\+ -");
    boolean needToPlus = exp.contains("+");

    String[] bits = null;

    if (needToPlus) {
      bits = exp.split(" \\+ ");
    }

    int a = Integer.parseInt(bits[0]);
    int b = Integer.parseInt(bits[1]);
    int c = 0;

    if (bits.length > 2) {
      c = Integer.parseInt(bits[2]);
    }

    if (needToPlus) {
      return a + b + c;
    }

    throw new RuntimeException("처리할 수 있는 계산식이 아닙니다");
  }
}