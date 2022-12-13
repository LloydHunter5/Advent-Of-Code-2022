package Day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/Day_4/D4_Input.txt"));

        int numEnclosedRanges = 0;
        int numOverlappedRanges = 0;
        while(s.hasNextLine()){
            String line = s.nextLine();

            int low1 = Integer.parseInt(line.substring(0, line.indexOf('-')));
            int high1 = Integer.parseInt(line.substring(line.indexOf('-')+1,line.indexOf(',')));

            int low2 = Integer.parseInt(line.substring(line.indexOf(',')+1,line.lastIndexOf('-')));
            int high2 = Integer.parseInt(line.substring(line.lastIndexOf('-')+1));


            // Part 1
            if ((low1 <= low2 && high1 >= high2) || (low2 <= low1 && high2 >= high1)) {
                numEnclosedRanges++;
            }

            // Part 2
            if(!(low2 > high1 || low1 > high2)){
               numOverlappedRanges++;
            }
        }
        System.out.println(numOverlappedRanges);

    }
}
