package com.ll;

public class Calc {
  public static int run(String exp) {

    exp = stripOuterBracket(exp);

    boolean needToMulti = exp.contains(" * ");
    boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
    boolean needToCompound = needToMulti && needToPlus;


    if (needToCompound) {
      exp = exp.replaceAll("- ", "\\+ -");
      String[] bits = exp.split(" \\+ ");
      return Integer.parseInt(bits[0]) + run(bits[1]);


    } else if (needToMulti) {
      String[] bits = exp.split(" \\* ");

      int result = 1;
      for (int i = 0; i < bits.length; i++) {
        result *= Integer.parseInt(bits[i]);
      }
      return result;


    } else if (needToPlus) {
      exp = exp.replaceAll("- ", "\\+ -");
      String[] bits = exp.split(" \\+ ");

      int sum = 0;
      for (int i = 0; i < bits.length; i++) {
        sum += Integer.parseInt(bits[i]);
      }
      return sum;
    }


    throw new RuntimeException("처리할 수 있는 계산식이 아닙니다");
  }

  private static String stripOuterBracket(String exp) {
//    exp = exp.replaceAll("\\(", "");
//    exp = exp.replaceAll("\\)", "");
    int outerBracketCount = 0;

    while (exp.charAt(outerBracketCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketCount) == ')') {
      outerBracketCount++;
    }
    if(outerBracketCount == 0){
      return exp;
    }

    return exp.substring(outerBracketCount, exp.length() - outerBracketCount);
  }
}
