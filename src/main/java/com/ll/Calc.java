package com.ll;

public class Calc {
  public static int run(String exp) {
    exp = exp.trim();
    exp = stripOuterBracket(exp);

    // 연산기호 없을 경우 바로 리턴
    if (!exp.contains(" ")) return Integer.parseInt(exp);

    boolean needToMulti = exp.contains(" * ");
    boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
    boolean needToCompound = needToMulti && needToPlus;
    boolean needToSplit = exp.contains("(") || exp.contains(")");

    if (needToSplit) {
      int bracketCount = 0;
      int splitPointIndex = -1;

      for (int i = 0; i < exp.length(); i++) {
        if (exp.charAt(i) == '(') {
          bracketCount++;
        } else if (exp.charAt(i) == ')') {
          bracketCount--;
        }
        if (bracketCount == 0) {
          splitPointIndex = i;
          break;
        }
      }

      String firstExp = exp.substring(0, splitPointIndex + 1);
      String secondExp = exp.substring(splitPointIndex + 3);

      return Calc.run((firstExp) + Calc.run(secondExp));

    } else if (needToCompound) {
      exp = exp.replaceAll("- ", "\\+ -");
      String[] bits = exp.split(" \\+ ");
      return Integer.parseInt(bits[0]) + run(bits[1]); //todo


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

    throw new

        RuntimeException("처리할 수 있는 계산식이 아닙니다");
  }


  private static String stripOuterBracket(String exp) {
    int outerBracketCount = 0;

    while (exp.charAt(outerBracketCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketCount) == ')') {
      outerBracketCount++;
    }
    if (outerBracketCount == 0) return exp;

    return exp.substring(outerBracketCount, exp.length() - outerBracketCount);
  }
}


