package Day_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class Main {

    public static FilesystemTree filesystem;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/Day_7/D7_Input.txt"));
        filesystem = new FilesystemTree();
        // Current directory is root
        FilesystemTree.Node cd = filesystem.root;
        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] words = line.split(" ");

            switch(words[0]){
                case "$":
                    switch (words[1]){
                        case "cd":
                            if (words[2].equals("/")) cd = filesystem.root;
                            else if (words[2].equals("..")) cd = cd.parent;
                            else cd = cd.getSubdirectory(words[2]);
                            break;
                        case "ls":
                            // do nothing, await info
                            break;
                    }
                    break;
                case "dir":
                    // Make new dir if there isn't one already
                    if(cd.getSubdirectory(words[1]) == null){
                        cd.addDir(words[1]);
                    }
                    break;
                default:
                    cd.addFile(words[1],Integer.parseInt(words[0]));
                    // is a file, size then name
            }
        }

        // Part 1
        // calculate the size of the full system
        filesystem.calculateSize();
        int sumOfAllDirSizes = findSumOfAllSubdirectories(filesystem.root);
        if(filesystem.root.size <= 100000){
            sumOfAllDirSizes += filesystem.root.size;
        }
        System.out.println(sumOfAllDirSizes);

        // Part 2
        int minSize = 30_000_000 - (70_000_000 - filesystem.root.size);
        System.out.println(minSize);

        int smallestDeletableDirectorySize = findSmallestAcceptableDirSize(filesystem.root, minSize,Integer.MAX_VALUE);
        System.out.println(smallestDeletableDirectorySize);
    }

    public static int findSumOfAllSubdirectories(FilesystemTree.Node root){
        // why is java like this???????
        final int[] sumOfDirSizes = {0};
        root.subdirectories.forEach(new BiConsumer<String, FilesystemTree.Node>() {
            @Override
            public void accept(String s, FilesystemTree.Node node) {
                if(node.size <= 100000){
                    sumOfDirSizes[0] += node.size;
                }
                sumOfDirSizes[0] += findSumOfAllSubdirectories(node);
            }
        });
        return sumOfDirSizes[0];
    }

    public static int findSmallestAcceptableDirSize(FilesystemTree.Node root, int minSize, int bestSoFar){
        final int[] tempBestSoFar = {bestSoFar};
        root.subdirectories.forEach(new BiConsumer<String, FilesystemTree.Node>() {
            @Override
            public void accept(String s, FilesystemTree.Node node) {
                if(node.size > minSize && node.size < tempBestSoFar[0]){
                    tempBestSoFar[0] = node.size;
                }
                int bestWithinSubdirectories = findSmallestAcceptableDirSize(node,minSize,tempBestSoFar[0]);
                if(bestWithinSubdirectories < tempBestSoFar[0]){
                    tempBestSoFar[0] = bestWithinSubdirectories;
                }
            }
        });
        return tempBestSoFar[0];
    }


}
