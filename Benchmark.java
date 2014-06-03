import java.util.Random;

public class Benchmark {

    public static final int NUM_ROUNDS = 1000;
    public static final int STRLEN = 5000;
    static final int OUTPUT_OFFSET = 35;

    static Random rng;

    interface Test<T extends CharSequence> {
        T execute(T str);
    }

    /*
     *    SPECIFY TESTS HERE
     */

    static class CharAtTest<T extends CharSequence> implements Test<T> {
        public T execute(T str) {
            int i = rng.nextInt(str.length());
            str.charAt(i);
            return str;
        }
    }

    static class ConcatTestString implements Test<String> {
        public String execute(String str) {
            String str1 = generateString(STRLEN);
            return str.concat(str1);
        }
    }

    static class ConcatTestRope implements Test<Rope> {
        public Rope execute(Rope r) {
            Rope r1 = new Rope(generateString(STRLEN));
            return r.concat(r1);
        }
    }

    static class NewTestString implements Test<String> {
        public String execute(String str) {
            return generateString(STRLEN);
        }
    }

    static class NewTestRope implements Test<Rope> {
        public Rope execute(Rope r) {
            return new Rope(generateString(STRLEN));
        }
    }

    static class SubstrTest<T extends CharSequence> implements Test<T> {
        public T execute(T str) {
            int int1 = rng.nextInt(str.length()+1);
            int int2 = rng.nextInt(str.length()+1);
            int start = Math.min(int1, int2);
            int end = Math.max(int1, int2);
            str.subSequence(start, end);
            return str;
        }
    }

    static class InsertTestString implements Test<String> {
        public String execute(String str) {
            int index = rng.nextInt(str.length()+1);
            return (str.substring(0, index) + generateString(STRLEN)
                + str.substring(index, str.length()));
        }
    }

    static class InsertTestRope implements Test<Rope> {
        public Rope execute(Rope r) {
            int index = rng.nextInt(r.length()+1);
            return r.insert(new Rope(generateString(STRLEN)), index);
        }
    }

    public static void main(String[] args) {
        rng = new Random();
        runTest(new NewTestString(), "");
        runTest(new NewTestRope(), new Rope(""));
        String strcon = runTest(new ConcatTestString(), "");
        Rope rcon = runTest(new ConcatTestRope(), new Rope(""));
        runTest(new SubstrTest<String>(), "");
        runTest(new SubstrTest<Rope>(), new Rope(""));
        runTest(new SubstrTest<String>(), strcon);
        runTest(new SubstrTest<Rope>(), rcon);
        runTest(new CharAtTest<String>(), strcon);
        runTest(new CharAtTest<Rope>(), rcon);
        runTest(new InsertTestString(), "");
        runTest(new InsertTestRope(), new Rope(""));
    }

    static <T extends CharSequence> T runTest(Test<T> test, T str) {
        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_ROUNDS; i++) {
            str = test.execute(str);
        }
        long endTime = System.nanoTime();
        String testName = test.getClass().getSimpleName().replace("String", "").replace("Rope", "");
        testName = str.getClass().getSimpleName() + ":" + testName;
        int maxSize = OUTPUT_OFFSET - testName.length();
        System.out.printf("%s duration: %" + maxSize + "d\n", testName, (endTime - startTime));
        return str;
    }
    
    // From: http://stackoverflow.com/questions/2863852/
    // how-to-generate-a-random-string-in-java
    public static String generateString(int length) {
        String characters = "qwertyuiopasdfghjklzxcvbnm";
        int charlen = 26;
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(26));
        }
        return new String(text);
    }
}
