import java.lang.reflect.Array;
import java.util.ArrayList;

public class HashTableText {
    String s;
    public HashTableText(String s) {
        this.s = s;
    }

    public ArrayList parseString() {
        ArrayList a = new ArrayList<>();
        String[] tokens = s.split("\\b");
        for (String t : tokens) {
            if (!t.equals(" "))
            a.add(t);
        }
        return a;
    }

    public int countWords(ArrayList<String> a) {
        
    }

    public static void main(String[] args) {
        String s = "Hello my name is Farzad!";
        HashTableText a = new HashTableText(s);
        System.out.println(a.parseString());
    }
}


