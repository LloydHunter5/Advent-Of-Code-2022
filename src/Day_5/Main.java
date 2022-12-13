package Day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static final char[][] initialConfig = {
            {'R','N','F','V','L','J','S','M'},
            {'P','N','D','Z','F','J','W','H'},
            {'W','R','C','D','G'},
            {'N','B','S'},
            {'M','Z','W','P','C','B','F','N'},
            {'P','R','M','W'},
            {'R','T','N','G','L','S','W'},
            {'Q','T','H','F','N','B','V'},
            {'L','M','H','Z','N','F'}
    };
    public static void main(String[] args) throws FileNotFoundException {
        Stack<Character>[] stacks = new Stack[9];
        for(int i = 0; i < stacks.length; i++){
            stacks[i] = new Stack<>();
        }

        for(int i = 0; i < stacks.length; i++){
            for(char c : initialConfig[i]){
                stacks[i].push(c);
            }
        }

        Scanner s = new Scanner(new File("src/Day_5/D5_Input.txt"));

        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] words = line.split(" ");
            int amountToMove = Integer.parseInt(words[1]);
            int from = Integer.parseInt(words[3])-1;
            int to = Integer.parseInt(words[5])-1;

            // Part 1
            /*
            for(int i = 0; i < amountToMove; i++){
                char c = stacks[from].pop();
                stacks[to].push(c);
            }
            */

            // Part 2
            Stack<Character> tempStack = new Stack<>();
            for(int i = 0; i < amountToMove; i++){
                tempStack.push(stacks[from].pop());
            }
            while(!tempStack.isEmpty()){
                stacks[to].push(tempStack.pop());
            }
        }
        for(Stack stack : stacks){
            System.out.print(stack.peek());
        }
    }
}
