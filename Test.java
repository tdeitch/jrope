public class Test {
    public static void main(String[] args) {
        Rope r = new Rope("Hi rope!");
        Rope r2 = new Rope("Hi rope2!");
        Rope r3 = r.concat(r2);
        System.out.println(r3);
        // System.out.println(r3.getChar(3));
    }
}