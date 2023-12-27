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

    throw return new RuntimeException("처리할 수 있는 계산식이 아님");
  }
}



//public class Calc {
//  public static int run(String exp) {
//
//    boolean needToPlus = exp.contains("+");
//    boolean needToMinus = exp.contains("-");
//
//    String[] bits = null;
//
//    if (needToPlus) {
//      bits = exp.split(" \\+ ");
//    } else if (needToMinus) {
//      bits = exp.split(" - ");
//    }
//
//    int a = Integer.parseInt(bits[0]);
//    int b = Integer.parseInt(bits[1]);
//
//    if (needToPlus) {
//      return a + b;
//    } else if (needToMinus) {
//      return a - b;
//    }
//
//    throw new RuntimeException("처리할 수 있는 계산식이 아닙니다");
//  }
//}
