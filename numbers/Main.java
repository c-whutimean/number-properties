package numbers;

import java.util.*;

public class Main {
    static String div7 = "divisible by 7";
    static String ends7 = "ends with 7";
    long naturalNum;
    String strNum;
    ArrayList<String> wordList = new ArrayList<>();
    ArrayList<String> hasWord = new ArrayList<>();

    Main(long naturalNum) {
        this.naturalNum = naturalNum;
        this.strNum = Long.toString(naturalNum);
    }

    // this is for my consecutive method
    void setNaturalNum(long naturalNum) {
        this.naturalNum = naturalNum;
        this.strNum = Long.toString(naturalNum);
    }

    void consecutiveCall(Long natural, String howMany, String word) {
        int size = Integer.parseInt(howMany);

        if (word.isEmpty()) {
            for (int i = 0; i < size; i++) {
                setNaturalNum(natural + i);
                consecutive();
            }
            for (String s : wordList) {
                System.out.println(s);
            }
        } else {
            int j = 0;
            String[] words = word.split(" ");
            List<String> _words = new ArrayList<>();
            List<String> reg = new ArrayList<>();

            for (String w : words) {
                if (w.startsWith("-")) {
                    w = w.substring(1);
                    _words.add(w);
                }
            }
            String[] _array =  _words.toArray(new String[0]);

            for (String w : words) {
                if(!w.startsWith("-")) {
                    reg.add(w);
                }
            }
            String[] regArr = reg.toArray(new String[0]);


            while (true) {
                setNaturalNum(natural + j);
                consecutive();
                String s = wordList.get(j);

                boolean allWordsContained = true;

                if (_array.length > 0) {
                    for (int i = 0; i < _array.length; i++) {
                        if (s.contains(_array[i])) {
                            allWordsContained = false;
                            break;
                        }
                    }
                }

                if (regArr.length > 0) {
                    for (int k = 0; k < regArr.length; k++) {
                        if (!s.contains(regArr[k])) {
                            allWordsContained = false;
                            break;
                        }
                    }
                }

                if (allWordsContained) {
                    hasWord.add(s);
                }

                if (hasWord.size() == size) {
                    break;
                }

                j++;
            }
        }
        for (String s : hasWord) {
            System.out.println(s);
        }
    }


    void consecutive() {
        Wordy.checkCases(naturalNum);
        StringBuilder sb = new StringBuilder();

        for (MyEnum.NUMBER n : MyEnum.NUMBER.values()) {
            if (n.getB() == true) {
                sb.append(n.getName());
                sb.append(", ");
            }
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        String cases = sb.toString();
        String adding = "%15d is %s".formatted(naturalNum, cases);
        wordList.add(adding);
        MyEnum.NUMBER.resetValues();
    }

    static boolean cantCovert(String n) {
        try {
            long num = Long.parseLong(n);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    static boolean mutualEx(String words) {
        words = words.toUpperCase().trim();
        String[] word = words.split(" ");
        Map<String, String> hashMap = new HashMap<>();

        for (MyEnum.MUTUAL m : MyEnum.MUTUAL.values()) {
            String name = m.getNegName();
            String opp = m.getNegOpp();
            hashMap.putIfAbsent(name, opp);
        }

        for (MyEnum.NUMBER n : MyEnum.NUMBER.values()) {
            String key = n.getName();
            String value = n.getNegName();
            hashMap.putIfAbsent(key, value);
        }

        for (MyEnum.MUTUAL m : MyEnum.MUTUAL.values()) {
            String name = m.getName();
            String opp = m.getOpp();
            hashMap.putIfAbsent(name, opp);
        }
        System.out.println();

        hashMap.remove("-SQUARE", "-SUNNY");
        hashMap.remove("-SUNNY", "-SQUARE");
        hashMap.put("SUNNY", "SQUARE");
        hashMap.put("-SUNNY", "SUNNY");
        hashMap.put("-SQUARE", "SQUARE");

      /*  for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey().toUpperCase();
            String value = entry.getValue().toUpperCase();
            System.out.println(key + "\t" + value);
        }
        System.out.println(Arrays.toString(word));*/

        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey().toUpperCase();
            String value = entry.getValue().toUpperCase();
            String found;

            for (String ww : word) {
                if (ww.equals(key)) {
                    for (int i = 0; i < word.length; i++) {
                        if (word[i].equals(value)) {
                            found = key + " " + value;
                            String[] w = found.split(" ");
                            String error = "The request contains mutually exclusive properties: " +
                                    Arrays.toString(w);
                            String error2 = "There are no numbers with these properties.";
                            System.out.println();
                            System.out.println(error);
                            System.out.println(error2);
                            return false;

                        }
                    }
                }
            }
        }
        return true;
    }

    void requestAllCases(String[] request, int len, long natural) {
        String howMany = request[1];
        String word = "";
        for (int i = 2; i < len; i++) {
            String wordi = request[i].toLowerCase();
            word += wordi + " ";
        }
        String[] words = word.split(" ");

        for (String w : words) {
            MyEnum.checkEnum(w, wordList);
        }

        if (wordList.isEmpty()) {
            if (mutualEx(word)) {
                consecutiveCall(natural, howMany, word);
            }
        } else {
            MyEnum.errorEnum(wordList);
        }
    }

    void requestCase(String[] request, int len, long natural) {
        switch (len) {
            case 1:
                Wordy.properties(natural);
                break;
            case 2:
                String howMany = request[1];
                String word = "";
                consecutiveCall(natural, howMany, word);
                break;
            case 3:
                howMany = request[1];
                word = request[2].toLowerCase();
                boolean valid = MyEnum.checkEnum(word, wordList);

                if (valid) {
                    consecutiveCall(natural, howMany, word);
                } else {
                    MyEnum.errorEnum(wordList);
                }
                break;
            default:
                requestAllCases(request, len, natural);
                break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long natural;
        String r;
        String error1 = "\nThe first parameter should be a " +
                "natural number or zero.";
        String error2 = "The second parameter should be a natural number.";

        Wordy.menu();
        while (true) {
            System.out.print("\nEnter a request: ");
            r = sc.nextLine();
            String[] request = r.split(" ");
            String num1 = request[0];
            int len = request.length;

            if (num1.equals("0")) {
                System.out.println("\nGoodbye!");
                break;
            } else if (len == 1 && ((num1.contains("-")) || cantCovert(num1))) {
                System.out.println(error1);
            } else if (len > 1 && request[1].contains("-")) {
                System.out.println("\n" + error2);
            } else {
                natural = Long.parseLong(num1);
                Main buzzer = new Main(natural);
                buzzer.requestCase(request, len, natural);
            }
        }
    }
}



