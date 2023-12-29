package com.ll;

public class Calc {
  public static boolean recursionDebug = true; // 내가 디버그 모드를 켜겠다 할때는 true로 변경
  public static int runCallCount = 0;

  public static int run(String exp) {
    runCallCount++;

    exp = exp.trim(); //공백제거
    exp = stripOuterBracket(exp); //괄호가 전 연산을 덮는 경우 괄호제거

    // 음수괄호 패턴이면, 패턴 변경
    int[] pos = null;
    while ((pos = isNegativeCaseBrachket(exp)) != null) {
      exp = changeNegativeBracket(exp, pos[0], pos[1]);
    }
    exp = stripOuterBracket(exp);

    if (recursionDebug) {
      System.out.printf("exp(%d) : %s\n", runCallCount, exp);
    }

    if (!exp.contains(" ")) return Integer.parseInt(exp);  // 연산기호 없을 경우 바로 리턴

    boolean needToMulti = exp.contains(" * ");
    boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
    boolean needToCompound = needToMulti && needToPlus;
    boolean needToSplit = exp.contains("(") || exp.contains(")");

    if (needToSplit) {
      exp = exp.replaceAll("- ", "\\+ -");

      int splitPointIndex = findSplitPointIndex(exp); //+,*로 괄호식을 나누는 수 가져오기. 안되면 -1 가져옴.

      String firstExp = exp.substring(0, splitPointIndex); //괄호식 따로 분배
      String secondExp = exp.substring(splitPointIndex + 1); //괄호식 외 식 분배

      char operator = exp.charAt(splitPointIndex);

      exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp);  //괄호식 계산과 그 외 계산을 연산

      return Calc.run(exp);  //split한 경우 계산값 리턴


    } else if (needToCompound) {
      String[] bits = exp.split(" \\+ "); //+로 나눔

      return run(bits[0]) + run(bits[1]);


    } else if (needToMulti) {
      String[] bits = exp.split(" \\* "); //*로 나눔

      int result = 1; //곱하기라 결과를 1로 둬야하는 것 주의!
      for (int i = 0; i < bits.length; i++) {
        result *= Integer.parseInt(bits[i]); // result = result * Integer.parseInt(bits[i])
      }
      return result;


    } else if (needToPlus) {
      exp = exp.replaceAll("- ", "\\+ -");  // -부호는 엮어서 +로 연산
      String[] bits = exp.split(" \\+ ");

      int sum = 0;
      for (int i = 0; i < bits.length; i++) {
        sum += Integer.parseInt(bits[i]); // sum = sum + Integer.parseInt(bits[i])
      }
      return sum;
    }

    throw new

        RuntimeException("처리할 수 있는 계산식이 아닙니다");
  }

  private static String changeNegativeBracket(String exp, int startPos, int endPos) {
    String head = exp.substring(0, startPos);
    String body = "(" + exp.substring(startPos + 1, endPos + 1) + " * -1)";
    String tail = exp.substring(endPos + 1);

    exp = head + body + tail;

    return exp;
  }

  private static int[] isNegativeCaseBrachket(String exp) {  //이거 좋다.
    for (int i = 0; i < exp.length() - 1; i++) {
      if (exp.charAt(i) == '-' && exp.charAt(i + 1) == '(') {
        //마이너스 괄호 찾았다
        int bracketCount = 1;   // '-('다음부터 시작하니까 다음은 ')'부터 끝내고 반복돌려야 하니까 1로 한 것.
        for (int j = i + 2; j < exp.length(); j++) {  // '-('를 스킵해야 하니까 +2 하고 시작.
          char c = exp.charAt(j);

          if (c == '(') {
            bracketCount++;
          } else if (c == ')') {
            bracketCount--;
          }
          if (bracketCount == 0) {
            return new int[]{i, j};
          }
        }
      }
    }
    return null;
  }


  private static int findSplitPointIndexBy(String exp, char findChar) {
    int bracketCount = 0;
    for (int i = 0; i < exp.length(); i++) {  //괄호식을 스킵해서 나누는 것 즉, 괄호식을 따로 계산하기 위해 묶음
      char c = exp.charAt(i);

      if (c == '(') {
        bracketCount++;
      } else if (c == ')') {
        bracketCount--;
      } else if (c == findChar) {
        if (bracketCount == 0) return i;
      }

    }
    return -1;  // 혹시 모를 반증에 대비
  }

  private static int findSplitPointIndex(String exp) {
    int index = findSplitPointIndexBy(exp, '+');

    if (index >= 0) return index; //+로 값이 있으면 그걸 리턴하고

    return findSplitPointIndexBy(exp, '*'); //아니면 *를 리턴

  }


  private static String stripOuterBracket(String exp) {
    if (exp.charAt(0) == '(' && exp.charAt(exp.length() - 1) == ')') {
      int bracketCount = 0;

      for (int i = 0; i < exp.length(); i++) {
        if (exp.charAt(i) == '(') {
          bracketCount++;
        } else if (exp.charAt(i) == ')') {
          bracketCount--;
        }

        if (bracketCount == 0) {
          if (exp.length() == i + 1) {
            return stripOuterBracket(exp.substring(1, exp.length() - 1));
          }

          return exp;
        }
      }
    }
    return exp;
  }
}
