package Day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    // Part 1
    static final int win = 6;
    static final int loss = 0;
    static final int draw = 3;
    enum Move{
        ROCK(1){
            @Override
            int outcome(Move opponentsMove) {
                switch (opponentsMove){
                    case ROCK:
                        return draw;
                    case PAPER:
                        return loss;
                    case SCISSORS:
                        return win;
                }
                return -1;
            }
        },
        PAPER(2){
            @Override
            int outcome(Move opponentsMove) {
                switch (opponentsMove){
                    case ROCK:
                        return win;
                    case PAPER:
                        return draw;
                    case SCISSORS:
                        return loss;
                }
                return -1;
            }
        },
        SCISSORS(3){
            @Override
            int outcome(Move opponentsMove) {
                switch (opponentsMove){
                    case ROCK:
                        return loss;
                    case PAPER:
                        return win;
                    case SCISSORS:
                        return draw;
                }
                return -1;
            }
        };

        int value;
        Move(int val){
            this.value = val;
        }

        abstract int outcome(Move opponentsMove);
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/Day_2/D2_Input.txt"));

        // Part 1
        // int yourTotalScore = calculatePartOneScore(s);

        // Part 2
        int yourTotalScore = calculatePartTwoScore(s);

        System.out.println(yourTotalScore);


    }

    public static int calculatePartOneScore(Scanner s){
        int yourTotalScore = 0;
        while(s.hasNext()){
            String opponentsMove = s.next();
            String yourMove = s.next();

            Move you = null;
            Move opponent = null;
            switch (opponentsMove){
                case "A":
                    opponent = Move.ROCK;
                    break;
                case "B":
                    opponent = Move.PAPER;
                    break;
                case "C":
                    opponent = Move.SCISSORS;
                    break;
            }
            switch (yourMove){
                case "X":
                    you = Move.ROCK;
                    break;
                case "Y":
                    you = Move.PAPER;
                    break;
                case "Z":
                    you = Move.SCISSORS;
                    break;
            }
            yourTotalScore += you.value + you.outcome(opponent);
        }
        return yourTotalScore;
    }

    public static int calculatePartTwoScore(Scanner s){
        int yourTotalScore = 0;
        while(s.hasNext()) {
            String opponentsMoveString = s.next();
            String expectedOutcomeString = s.next();

            Move opponent = null;
            int outcome = 0;
            switch (opponentsMoveString) {
                case "A":
                    opponent = Move.ROCK;
                    break;
                case "B":
                    opponent = Move.PAPER;
                    break;
                case "C":
                    opponent = Move.SCISSORS;
                    break;
            }
            switch (expectedOutcomeString) {
                case "X":
                    outcome = loss;
                    break;
                case "Y":
                    outcome = draw;
                    break;
                case "Z":
                    outcome = win;
                    break;
            }

            yourTotalScore += outcome + getYourMoveValue(outcome,opponent).value;

        }
        return yourTotalScore;
    }

    private static Move getYourMoveValue(int outcome, Move opponentsMove){
        if(outcome == draw){
            return opponentsMove;
        }
        switch (opponentsMove){
            case ROCK:
                switch (outcome){
                    case win:
                        return Move.PAPER;
                    case loss:
                        return Move.SCISSORS;
                }
            case PAPER:
                switch (outcome){
                    case win:
                        return Move.SCISSORS;
                    case loss:
                        return Move.ROCK;
                }
            case SCISSORS:
                switch (outcome){
                    case win:
                        return Move.ROCK;
                    case loss:
                        return Move.PAPER;
                }
        }
        return null;
    }


}
