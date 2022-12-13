package Day_9;

public class RopeChain {
    Rope root;
    Rope[] ropes = new Rope[9];
    public RopeChain(){
        for(int i = 0; i < ropes.length; i++){
            ropes[i] = new Rope();
            if(i == 0) continue;
            ropes[i].head = ropes[i-1].tail;
        }
        root = ropes[0];
    }

    public Rope.Point moveRope(Main.Direction dir){
        root.moveHead(dir);
        for(int i = 0; i < ropes.length; i++){
            ropes[i].updateTailPos();
        }
        return ropes[8].tail;
    }
}
