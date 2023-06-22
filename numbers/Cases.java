package numbers;

public class Cases {
    long naturalNum;
    String strNum;

    Cases(long naturalNum) {
        this.naturalNum = naturalNum;
        this.strNum = Long.toString(naturalNum);
    }

    String getStrNum() {
        return strNum;
    }

    int[] getIndex() {
        int lastIndex = strNum.length() - 1;
        int end = Integer.parseInt(String.valueOf(strNum.charAt(lastIndex)));
        int begin = Integer.parseInt(String.valueOf(strNum.charAt(0)));
        int[] index = new int[]{begin, end};

        return index;
    }

    int checkBuzzCases(int end) {
        int cases = 0;

        if (naturalNum % 7 == 0 && end == 7) {
            cases = 1;
        } else if (end == 7) {
            cases = 2;
        } else if (naturalNum % 7 == 0) {
            cases = 3;
        }
        return cases;
    }

  /*  void buzzCases(int i) {
        if (i > 0) {
            System.out.println("It is a Buzz number.\nExplanation:");
            switch (i) {
                case 1:
                    System.out.printf("%d is %s and %s.", naturalNum, div7, ends7);
                    break;
                case 2:
                    System.out.printf("%d %s.", naturalNum, ends7);
                    break;
                case 3:
                    System.out.printf("%d is %s.", naturalNum, div7);
                    break;
            }

        } else {
            System.out.println("It is not a Buzz number.\nExplanation:");
            System.out.printf("%d is neither %s nor does it end with 7.", naturalNum, div7);
        }

    }*/
  void isHappy() {
      String n = getStrNum();
      String[] digits = n.split("");
      long sum = 0;

      if (n.equals("1")) {
          MyEnum.NUMBER.HAPPY.setB(true);
          return;
      }

      while (true) {
          for (String d : digits) {
              long num = Integer.parseInt(d);
              sum += num * num;
          }
          n = Long.toString(sum);
          digits = n.split("");
          if (digits.length == 1) {
              break;
          }
          sum = 0;
      }

      if (digits[0].equals("1")) {
          MyEnum.NUMBER.HAPPY.setB(true);
      } else {
          MyEnum.NUMBER.SAD.setB(true);
      }
  }

    void isSad() {
        isHappy();
    }

    void isSunny() {
        long incremN = naturalNum + 1;
        MyEnum.NUMBER.SUNNY.setB(checkSquare(incremN));
    }

    void isSquare() {
        MyEnum.NUMBER.SQUARE.setB(checkSquare(naturalNum));
    }

    boolean checkSquare(long n) {
        double num = (double) n;
        double root = Math.sqrt(num);
        double floorRoot = Math.floor(root);
        double squared = floorRoot * floorRoot;

        return (squared == num);
    }

    void isJumping() {
        int len = strNum.length() - 1;
        int i = 0;

        if (strNum.length() == 1) {
            MyEnum.NUMBER.JUMPING.setB(true);
        }

        while (i < len) {
            int n1 = Integer.parseInt(String.valueOf(strNum.charAt(i)));
            int n2 = Integer.parseInt(String.valueOf(strNum.charAt(i + 1)));
            //System.out.println(n1 + " " + n2);
            int diff = Math.abs(n1 - n2);
            // System.out.println(diff);
            if (diff != 1) {
                MyEnum.NUMBER.JUMPING.setB(false);
                break;
            }
            MyEnum.NUMBER.JUMPING.setB(true);
            i++;
        }
    }

    void isSpy() {
        long n;
        long sum = 0;
        long product = 1;

        for (int i = 0; i < strNum.length(); i++) {
            n = Long.parseLong(String.valueOf(strNum.charAt(i)));
            sum += n;
            product *= n;
        }

        if (sum == product) {
            MyEnum.NUMBER.SPY.setB(true);
        }
    }

    void isGapful(int begin, int end) {
        StringBuilder sb = new StringBuilder();
        sb.append(begin).append(end);
        int div = Integer.parseInt(sb.toString());

        if (naturalNum % div == 0) {
            MyEnum.NUMBER.GAPFUL.setB(true);
        }
    }

    void isEvenOdd() {
        if (naturalNum % 2 == 0) {
            MyEnum.NUMBER.EVEN.setB(true);
        } else {
            MyEnum.NUMBER.ODD.setB(true);
        }
    }

    void isDuck() {
        if (strNum.contains("0")) {
            MyEnum.NUMBER.DUCK.setB(true);
        }
    }

    void isBuzz(int i) {
        if (i > 0) {
            MyEnum.NUMBER.BUZZ.setB(true);
        }
    }

    void isPalindromic() {
        StringBuilder sb = new StringBuilder();
        sb.append(strNum);
        sb.reverse();

        if (sb.toString().equals(strNum)) {
            MyEnum.NUMBER.PALINDROMIC.setB(true);
        }
    }

}
