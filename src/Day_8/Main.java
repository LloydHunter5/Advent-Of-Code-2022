package Day_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/Day_8/D8_Input.txt"));

        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();

        int columnNumber = 0;
        while(s.hasNextLine()){
            String row = s.nextLine();
            grid.add(new ArrayList<>());
            for(int i = 0; i < row.length(); i++){
                char c = row.charAt(i);
                grid.get(columnNumber).add(c - '0');
            }
            columnNumber++;
        }

        // Part 1
        boolean[][] isVisibleFromOutside = new boolean[grid.size()][grid.get(0).size()];

        // From left
        int rowIndex = 0;
        for(ArrayList<Integer> row : grid){
            int previousHeight = -1;
            for(int i = 0; i < row.size(); i++){
                if(row.get(i) > previousHeight) {
                    isVisibleFromOutside[rowIndex][i] = true;
                    previousHeight = row.get(i);
                }

            }
            rowIndex++;
        }
        // From right
        rowIndex = 0;
        for(ArrayList<Integer> row : grid){
            int previousHeight = -1;
            for(int i = row.size() - 1; i > 0; i--){
                if(row.get(i) > previousHeight) {
                    isVisibleFromOutside[rowIndex][i] = true;
                    previousHeight = row.get(i);
                }
            }
            rowIndex++;
        }
        // From top

        for(int col = 0; col < grid.get(0).size(); col++){
            int previousHeight = -1;
            for(int row = 0; row < grid.size(); row++){
                if(grid.get(row).get(col) > previousHeight) {
                    isVisibleFromOutside[row][col] = true;
                    previousHeight = grid.get(row).get(col);
                }
            }
        }
        // From bottom
        for(int col = grid.get(0).size() - 1; col > 0; col--){
            int previousHeight = -1;
            for(int row = grid.size() - 1;row > 0 ; row--){
                if(grid.get(row).get(col) > previousHeight){
                    isVisibleFromOutside[row][col] = true;
                    previousHeight = grid.get(row).get(col);
                }
            }
        }

        /* Optional print
        for(boolean[] row : isVisibleFromOutside){
            System.out.println();
            for(boolean cellIsVisible : row){
                if(cellIsVisible){
                    System.out.print("X");
                }else{
                    System.out.print(" ");
                }
            }
        }
        */

        int sumVisibleTrees = 0;
        for(boolean[] row : isVisibleFromOutside){
            for(boolean isVisibleCell : row){
                if(isVisibleCell){
                    sumVisibleTrees++;
                }
            }
        }

        System.out.println(sumVisibleTrees);


        //Part 2
        int bestProductVisible = 0;
        int bestVisibleX = 0, bestVisibleY = 0;
        for(int y = 0; y < grid.size(); y++){
            for(int x = 0; x < grid.get(0).size(); x++){
                int startTreeHeight = grid.get(y).get(x);


                // check left
                int visibleFromLeft = 0;
                for(int i = x - 1; i >= 0; i--){
                    if(grid.get(y).get(i) >= startTreeHeight){
                        visibleFromLeft++;
                        break;
                    }
                    visibleFromLeft++;
                }
                // right
                int visibleFromRight = 0;
                for(int i = x + 1; i < grid.get(0).size(); i++){
                    if(grid.get(y).get(i) >= startTreeHeight){
                        visibleFromRight++;
                        break;
                    }
                    visibleFromRight++;
                }
                // up
                int visibleFromUp = 0;
                for(int i = y - 1; i >= 0; i--){
                    if(grid.get(i).get(x) >= startTreeHeight){
                        visibleFromUp++;
                        break;
                    }
                    visibleFromUp++;
                }
                // down
                int visibleFromDown = 0;
                for(int i = y + 1; i < grid.size(); i++){
                    if(grid.get(i).get(x) >= startTreeHeight){
                        visibleFromDown++;
                        break;
                    }
                    visibleFromDown++;
                }

                int scenicScore = visibleFromDown * visibleFromLeft * visibleFromUp * visibleFromRight;
                if(scenicScore > bestProductVisible){
                    bestProductVisible = scenicScore;
                    bestVisibleX = x;
                    bestVisibleY = y;
                }
            }
        }

        System.out.println(bestProductVisible);
        System.out.println();
        System.out.println((bestVisibleX+1) + ", "+ (bestVisibleY+1));
        System.out.println();
    }
}
