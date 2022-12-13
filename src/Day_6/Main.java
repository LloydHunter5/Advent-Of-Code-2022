package Day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/Day_6/D6_Input.txt"));
        String datastream = s.nextLine();

        // Part 1
        LinkedList<Character> list = new LinkedList<>();
        int charIndex = 0;
        while(!checkList(list)){
            list.addFirst(datastream.charAt(charIndex));
            if(list.size() > 4){
                list.removeLast();
            }
            charIndex++;
        }
        System.out.println(charIndex);

        // Part 2
        charIndex = 0;
        char currentChar;
        while(list.size() < 14){
            currentChar = datastream.charAt(charIndex);
            charIndex++;
            if(list.contains(currentChar)){
                int occurence = list.size()- list.indexOf(currentChar);
                for(int i = 0; i < occurence; i++){
                    list.removeLast();
                }
            }
            list.addFirst(currentChar);

        }
        System.out.println(charIndex);

    }

    public static boolean checkList(LinkedList<Character> list){
        if(list.size() != 4) return false;
        if (list.get(0) == list.get(1) || list.get(0) == list.get(2) || list.get(0) == list.get(3) ) {
            return false;
        }else if (list.get(1) == list.get(2) || list.get(1) == list.get(3)){
            return false;
        }else if (list.get(2) == list.get(3)){
            return false;
        }else{
            return true;
        }
    }


}
