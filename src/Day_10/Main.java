package Day_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static int sumOfStrengths = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/Day_10/D10_Input.txt"));

        int cycle = 0;
        int X = 1;

        updateSignalStrengths(cycle,X);

        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] words = line.split(" ");

            switch (words[0]){
                case "noop":
                    cycle++;
                    updateSignalStrengths(cycle,X);
                    drawPixel(X);
                    break;
                case "addx":
                    cycle++;//Cycle 1
                    updateSignalStrengths(cycle,X);
                    drawPixel(X); // Part 2
                    cycle++; // Cycle 2
                    updateSignalStrengths(cycle,X);
                    drawPixel(X); // Part 2
                    // Between 2 & 3
                    X += Integer.parseInt(words[1]);
                    break;
            }
        }
        System.out.println("\nPart 1:");
        System.out.println(sumOfStrengths);

    }

    // Part 1
    public static void updateSignalStrengths(int cycle, int register){
        if((cycle-20)%40 != 0) return;

        sumOfStrengths += register * cycle;
    }

    public static int CRT_POS = 0;
    public static void drawPixel(int register){
        if(CRT_POS % 40 == 0){
            CRT_POS = 0;
            System.out.println(); // newline every 40
        }

        if(register == CRT_POS || register+1 == CRT_POS || register - 1 == CRT_POS){
            System.out.print("#");
        }else{
            System.out.print(".");
        }
        CRT_POS++;
    }
}
