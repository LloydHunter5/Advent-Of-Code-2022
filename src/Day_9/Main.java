package Day_9;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class Main {
    public static HashMap<Integer, HashMap<Integer,Boolean>> visitedPoints = new HashMap<>();

    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/Day_9/D9_Input.txt"));
        Rope rope = new Rope();
        RopeChain ropeChain = new RopeChain();
        visitPoint(0,0);
        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] words = line.split(" ");
            Direction direction;
            switch (words[0]){
                case "R": direction = Direction.RIGHT;break;
                case "U": direction = Direction.UP;   break;
                case "L": direction = Direction.LEFT; break;
                case "D": direction = Direction.DOWN; break;
                default:  direction = null;
            }

            int dist = Integer.parseInt(words[1]);

            // Part 1
            /*
            for(int i = 0; i < dist; i++){
                Rope.Point tailPos = rope.moveHead(direction);
                visitPoint(tailPos.x, tailPos.y);
            }
            */

            // Part 2
            for(int i = 0; i < dist; i++){
                Rope.Point tailPos = ropeChain.moveRope(direction);
                visitPoint(tailPos.x,tailPos.y);
            }
        }

        final int[] numVisitedPoints = {0};
        visitedPoints.forEach(new BiConsumer<Integer, HashMap<Integer, Boolean>>() {
            @Override
            public void accept(Integer x, HashMap<Integer, Boolean> row) {
                row.forEach(new BiConsumer<Integer, Boolean>() {
                    @Override
                    public void accept(Integer y, Boolean isVisited) {
                        if(isVisited) {
                            numVisitedPoints[0]++;
                        }
                    }
                });
            }
        });
        System.out.println(numVisitedPoints[0]);

        /*
        for(int i = 9; i >= -10; i--){
            System.out.println();
            for(int j = -10; j < 10; j++){
                if(isVisitedPoint(j,i)){
                    System.out.print("# ");
                }else{
                    System.out.print(". ");
                }
            }
        }
        */
    }

    public static boolean isVisitedPoint(int x, int y){
        visitedPoints.putIfAbsent(x,new HashMap<>());
        visitedPoints.get(x).putIfAbsent(y,false);
        return visitedPoints.get(x).get(y);
    }

    public static void visitPoint(int x, int y){
        visitedPoints.putIfAbsent(x, new HashMap<>());
        visitedPoints.get(x).put(y,true);
    }
}
