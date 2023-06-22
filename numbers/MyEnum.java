package numbers;

import java.util.ArrayList;
import java.util.Arrays;

public class MyEnum {
    enum NUMBER {
        BUZZ(false, "buzz"),
        DUCK(false, "duck"),
        PALINDROMIC(false, "palindromic"),
        GAPFUL(false, "gapful"),
        SPY(false, "spy"),
        EVEN(false, "even"),
        ODD(false, "odd"),
        SUNNY(false, "sunny"),
        SQUARE(false, "square"),
        JUMPING(false, "jumping"),
        HAPPY(false, "happy"),
        SAD(false, "sad");


        boolean b;
        String name;

        NUMBER(boolean b, String name) {
            this.b = b;
            this.name = name;
        }

        void setB(boolean b) {
            this.b = b;
        }

        boolean getB() {
            return b;
        }
        String getNegName() {
            return "-" + name;
        }

        String getName() {
            return name;
        }

        static void resetValues() {
            for (NUMBER n : NUMBER.values()) {
                n.setB(false);
            }
        }
    }
    enum MUTUAL {
        EVEN("EVEN","ODD"),
        DUCK("DUCK","SPY"),
        SUNNY("SUNNY","SQUARE"),
        SAD("SAD", "HAPPY");

        String name;
        String opp;

        MUTUAL(String name, String opp) {
            this.name = name;
            this.opp = opp;
        }

        String getName() {
            return name;
        }

        String getOpp() {
            return opp;
        }

        String getNegName() {
            return "-" + name;
        }

        String getNegOpp() {
            return "-" + opp;
        }
    }

    static boolean checkEnum(String word, ArrayList<String> wordList) {
        MyEnum.NUMBER[] enumValues = MyEnum.NUMBER.values();
        String invalid;

        for (MyEnum.NUMBER value : enumValues) {
            if (value.getName().equals(word)) {
                return true;
            } else if (value.getNegName().equals(word)) {
                return true;
            }
        }
        wordList.add(word.toUpperCase());
        return false;
    }

    static void errorEnum(ArrayList<String> wordList) {
        MyEnum.NUMBER[] enumValues = MyEnum.NUMBER.values();
        int size = wordList.size();
        String invalid;

        if (size == 1) {
            invalid = "The property %s is wrong.".formatted(wordList);
            System.out.println("\n" + invalid);
        } else {
            invalid = "The properties %s are wrong.".formatted(wordList);
            System.out.println("\n" + invalid);
        }
        System.out.println("Available properties: " + Arrays.toString(enumValues));
    }
}
