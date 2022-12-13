package Day_7;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class FilesystemTree {

    public class Node{
        Node parent;
        HashMap<String,Node> subdirectories;
        HashMap<String,Integer> files;
        int size;

        public Node(Node parent){
            this.parent = parent;
            subdirectories = new HashMap<>();
            files = new HashMap<>();
            size = 0;
        }

        public void addDir(String name){
            subdirectories.put(name,new Node(this));
        }
        public void addFile(String name, int size){
            files.put(name,size);
        }
        public Node getSubdirectory(String name){
            return subdirectories.get(name);
        }
        public int getFileSize(String name){
            return files.get(name);
        }

        private int calculateSize(){
            if(size > 0) return size; //if it has already been calculated

            subdirectories.forEach(new BiConsumer<String, Node>() {
                @Override
                public void accept(String s, Node node) {
                    size += node.calculateSize();
                }
            });
            files.forEach(new BiConsumer<String, Integer>() {
                @Override
                public void accept(String s, Integer filesize) {
                    size += filesize;
                }
            });
            return size;
        }
    }

    public Node root;

    public FilesystemTree(){
        root = new Node(null);
    }

    public long calculateSize(){
        root.calculateSize();
        return root.size;
    }
}
