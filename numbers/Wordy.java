package numbers;

public class Wordy {
    static void menu() {
        String menu = """
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                \t* the first parameter represents a starting number;
                \t* the second parameter shows how many consecutive numbers are to be processed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.
                """;
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println(menu);
    }

    static void checkCases(Long naturalNum) {
        Cases c = new Cases(naturalNum);
        int[] splitNum = c.getIndex();
        int i = c.checkBuzzCases(splitNum[1]);
        if (c.strNum.length() > 2) {
            c.isGapful(splitNum[0], splitNum[1]);
        }
        c.isBuzz(i);
        c.isEvenOdd();
        c.isDuck();
        c.isPalindromic();
        c.isSpy();
        c.isSquare();
        c.isSunny();
        c.isJumping();
        c.isHappy();
        c.isSad();
    }

    static void properties(Long naturalNum) {
        checkCases(naturalNum);
        boolean even = MyEnum.NUMBER.EVEN.getB();
        boolean odd = MyEnum.NUMBER.ODD.getB();
        boolean buzz = MyEnum.NUMBER.BUZZ.getB();
        boolean duck = MyEnum.NUMBER.DUCK.getB();
        boolean pali = MyEnum.NUMBER.PALINDROMIC.getB();
        boolean gap = MyEnum.NUMBER.GAPFUL.getB();
        boolean spy = MyEnum.NUMBER.SPY.getB();
        boolean sunny = MyEnum.NUMBER.SUNNY.getB();
        boolean square = MyEnum.NUMBER.SQUARE.getB();
        boolean jumping = MyEnum.NUMBER.JUMPING.getB();
        boolean sad = MyEnum.NUMBER.SAD.getB();
        boolean happy = MyEnum.NUMBER.HAPPY.getB();

        String output = """
                %15s: %b
                %15s: %b
                %15s: %b
                %15s: %b
                %15s: %b
                %15s: %b
                %15s: %b
                %15s: %b
                %15s: %b
                %15s: %b
                %15s: %b
                %15s: %b
                """.formatted("buzz", buzz, "duck",
                duck, "palindromic", pali, "gapful",
                gap, "spy", spy, "even", even, "odd", odd,
                "sunny", sunny, "square", square,
                "jumping", jumping, "sad", sad,
                "happy", happy);

        System.out.printf("\nProperties of %d\n", naturalNum);
        System.out.println(output);
        MyEnum.NUMBER.resetValues();
    }
}
