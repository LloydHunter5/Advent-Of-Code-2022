package Day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Part 1
        Scanner s = new Scanner(new File("src/Day_2/D1_Input.txt"));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while(s.hasNextLine()){
            String line = s.nextLine();
            int sum = 0;
            while(!line.isBlank() && s.hasNextLine()){
                sum -= Integer.parseInt(line);
                line = s.nextLine();
            }
            pq.add(sum);
        }
        // Part 2
        int topThreeSum = pq.poll() + pq.poll() + pq.poll();
        System.out.println(-topThreeSum);
    }
}
