package Day_9;

public class Rope {


    public class Point{
        public int y;
        public int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    Point head;
    Point tail;

    public Rope(){
        head = new Point(0,0);
        tail = new Point(0,0);
    }

    public Point moveHead(Main.Direction dir){ //Returns new tail position
        switch (dir){
            case UP: head.y++;break;
            case DOWN: head.y--; break;
            case LEFT: head.x--; break;
            case RIGHT: head.x++; break;
        }
        updateTailPos();

        return tail;
    }

    public void updateTailPos(){
        // Update tail position
        if(Math.abs(head.x - tail.x) < 2 && Math.abs(head.y - tail.y) < 2){
            //do nothing
        }else if ((Math.abs(head.x - tail.x) == 2 && Math.abs(head.y - tail.y) == 1) || (Math.abs(head.y - tail.y) == 2 && Math.abs(head.x - tail.x) == 1)){ //diagonal
            switch (head.x - tail.x) {
                case 2:
                case -2:
                    tail.x += (head.x - tail.x)/2;
                    tail.y += head.y - tail.y; // up or down depending on where head is
                    break;
            }
            switch (head.y - tail.y){
                case 2:
                case -2:
                    tail.y += (head.y - tail.y)/2;
                    tail.x += head.x - tail.x; // l or r depending on head
                    break;
            }
        }else{
            tail.x += (head.x - tail.x)/2;
            tail.y += (head.y - tail.y)/2;
        }
    }
}
