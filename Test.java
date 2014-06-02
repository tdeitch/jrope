public class Test {
    public static void main(String[] args) {
        Rope r = new Rope("Hi rope!");
        Rope r2 = new Rope("Hi rope2!");
        Rope r3 = r.concat(r2);
        System.out.println("Concatenation");
        System.out.println("\t" + r3);
        System.out.print("charAt\n\t");
        for(int i = 0; i < r3.length(); i++) {
        	System.out.print(r3.charAt(i));
        }
        System.out.println("\nSplitting");
        for(int i = 0; i <= r3.length(); i++) {
            Pair<Rope> pair = r3.split(i);
            System.out.println("\t" + pair.one);
            System.out.println("\t" + pair.two);
        }
        System.out.println("\nSubstring");
        for(int i = 0; i <= r3.length(); i++) {
            for (int j = 0; j < r3.length() - i; j++) {
                System.out.println("\t" + r3.substring(i,j));
            }
        }
    }
}
