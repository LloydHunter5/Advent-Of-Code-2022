package Day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/Day_3/D3_Input.txt"));
        // Part 1
        // int sumOfPriorities = firstPart(s);
        // Part 2
        int sumOfPriorities = secondPart(s);
        System.out.println(sumOfPriorities);
    }

    public static int firstPart(Scanner s){
        int sumOfPriorities = 0;
        while(s.hasNextLine()){
            String line = s.nextLine();
            String c1 = line.substring(0,line.length()/2);
            String c2 = line.substring(line.length()/2);
            boolean[] c1HasItem = new boolean[52];
            boolean[] c2HasItem = new boolean[52];
            for(int i = 0; i < c1.length(); i++){
                char c1char = c1.charAt(i);
                char c2char = c2.charAt(i);

                int c1Index;
                int c2Index;
                if(c1char > 'Z')c1Index = c1char - 'a';
                else c1Index = c1char - 'A' + 26;

                if(c2char > 'Z') c2Index = c2char - 'a';
                else c2Index = c2char - 'A' + 26;

                c1HasItem[c1Index] = true;
                c2HasItem[c2Index] = true;
            }
            for(int i = 0; i < c1HasItem.length; i++){
                if(c1HasItem[i] && c2HasItem[i]){
                    sumOfPriorities += i+1;
                }
            }
        }
        return sumOfPriorities;
    }

    public static int secondPart(Scanner s){
        int sumOfPriorities = 0;
        while(s.hasNextLine()){
            String g1 = s.nextLine();
            String g2 = s.nextLine();
            String g3 = s.nextLine();

            boolean[] g1HasItem = getHasItemArray(g1);
            boolean[] g2HasItem = getHasItemArray(g2);
            boolean[] g3HasItem = getHasItemArray(g3);

            for(int i = 0; i < g1HasItem.length; i++){
                if(g1HasItem[i] && g2HasItem[i] && g3HasItem[i]){
                    sumOfPriorities += i + 1;
                }
            }
        }
        return sumOfPriorities;
    }

    public static boolean[] getHasItemArray(String s){
        boolean[] hasItem = new boolean[52];
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int index;
            if(c > 'Z') index = c - 'a';
            else index = c - 'A' + 26;

            hasItem[index] = true;
        }
        return hasItem;
    }
}
