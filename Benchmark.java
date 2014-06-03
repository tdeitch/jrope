import java.util.Random;

public class Benchmark {

    public static final int NUM_ROUNDS = 1000;
    public static final int STRLEN = 5000;

    static Random rng;

    interface StringTest {
        String execute(String str);
    }

    interface RopeTest {
        Rope execute(Rope r);
    }

    static class StrCharAtTest implements StringTest {
        public String execute(String str) {
            int i = rng.nextInt(str.length());
            str.charAt(i);
            return str;
        }
    }

    static class RopeCharAtTest implements RopeTest {
        public Rope execute(Rope r) {
            int i = rng.nextInt(r.length());
            r.charAt(i);
            return r;
        }
    }

    static class StrConcatTest implements StringTest {
        public String execute(String str) {
            String str1 = generateString(STRLEN);
            return str.concat(str1);
        }
    }

    static class RopeConcatTest implements RopeTest {
        public Rope execute(Rope r) {
            Rope r1 = new Rope(generateString(STRLEN));
            return r.concat(r1);
        }
    }

    static class StrNewTest implements StringTest {
        public String execute(String str) {
            return generateString(STRLEN);
        }
    }

    static class RopeNewTest implements RopeTest {
        public Rope execute(Rope r) {
            return new Rope(generateString(STRLEN));
        }
    }

    static class StrSubstrTest implements StringTest {
        public String execute(String str) {
            int int1 = rng.nextInt(str.length()+1);
            int int2 = rng.nextInt(str.length()+1);
            int start = Math.min(int1, int2);
            int end = Math.max(int1, int2);
            str.subSequence(start, end);
            return str;
        }
    }

    static class RopeSubstrTest implements RopeTest {
        public Rope execute(Rope r) {
            int int1 = rng.nextInt(r.length()+1);
            int int2 = rng.nextInt(r.length()+1);
            int start = Math.min(int1, int2);
            int end = Math.max(int1, int2);
            r.substring(start, end);
            return r;
        }
    }

    static class StrInsertTest implements StringTest {
        public String execute(String str) {
            int index = rng.nextInt(str.length()+1);
            return str.substring(0, index) + generateString(STRLEN) + str.substring(index, str.length());
        }
    }

    static class RopeInsertTest implements RopeTest {
        public Rope execute(Rope r) {
            int index = rng.nextInt(r.length()+1);
            return r.insert(new Rope(generateString(STRLEN)), index);
        }
    }

    public static void main(String[] args) {
        rng = new Random();
        runStrTest(new StrNewTest(), "");
        runRopeTest(new RopeNewTest(), new Rope(""));
        String strcon = runStrTest(new StrConcatTest(), "");
        Rope rcon = runRopeTest(new RopeConcatTest(), new Rope(""));
        runStrTest(new StrSubstrTest(), "");
        runRopeTest(new RopeSubstrTest(), new Rope(""));
        runStrTest(new StrSubstrTest(), strcon);
        runRopeTest(new RopeSubstrTest(), rcon);
        runStrTest(new StrCharAtTest(), strcon);
        runRopeTest(new RopeCharAtTest(), rcon);
        runStrTest(new StrInsertTest(), "");
        runRopeTest(new RopeInsertTest(), new Rope(""));
    }

    static String runStrTest(StringTest test, String str) {
        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_ROUNDS; i++) {
            str = test.execute(str);
        }
        long endTime = System.nanoTime();
        System.out.println(test.getClass().getSimpleName() + " duration: \t" + (endTime - startTime));
        return str;
    }

    static Rope runRopeTest(RopeTest test, Rope r) {
        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_ROUNDS; i++) {
            r = test.execute(r);
        }
        long endTime = System.nanoTime();
        System.out.println(test.getClass().getSimpleName() + " duration:\t" + (endTime - startTime));
        return r;
    }

    // From: http://stackoverflow.com/questions/2863852/how-to-generate-a-random-string-in-java
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
