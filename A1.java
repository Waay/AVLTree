package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;




/**

 * Jose Luis Matias Pereira, 2014202020
 * Solucao A1 - AVL Trees based solution
 *
 * BIBLIOGRAPHY:

    * https://www.sanfoundry.com/java-program-implement-avl-tree/
    * https://github.com/eugenp/tutorials/blob/master/data-structures/src/main/java/com/baeldung/avltree/AVLTree.java
    * https://www.javatpoint.com/java-string-compareto
    * https://www.youtube.com/watch?v=ygZMI2YIcvk&feature=emb_title


    * Beside the links above, was used:
        * AED 5A AVL VP.pdf (Given by the teacher)

 */

public class A1 {

    public static int max_readLn = 1000000;

    public static void main(String[] args) {

        //##### INPUT HANDLER #####//
        AVLTree avlTree = new AVLTree();

        String str = "";
        int i = 0;
        ArrayList<ArrayList<String>> wordIndex = new ArrayList<>();


        while (!str.equals("TCHAU")){
            str = readLn(max_readLn);
            String[] strArray = str.split(" ");



            if (strArray[0].equals("TEXTO")){
                

                String strAux = "";
                while (!strAux.equals("FIM.")){
                    strAux = readLn(max_readLn);
                    String aux = strAux.replaceAll("[.,;)(]","").trim();
                    String[] line = aux.split(" ");

                    //System.out.println("Str: "+aux);

                    for (String word : line){
                        totalWordCounter++;
                        avlTree.root = avlTree.insertNewNode(avlTree.root, word, i);
                    }
                    i++;
                }


                System.out.println("GUARDADO.");
            }


           //--------------------------------------------------------------------------------------------------------//
            //##### LINHAS #####//
            else if (strArray[0].equals("LINHAS")){

                String wordToFindL = strArray[1];
                String toPrint = "";
                int[] occurrencesAux = avlTree.findNode(wordToFindL);

                if (occurrencesAux.length > 0){
                    for (i = 0; i < occurrencesAux.length; i++){
                        toPrint += String.valueOf(occurrencesAux[i]) + " ";
                    }
                }
                else {
                    toPrint = "-1";
                }
                System.out.println(toPrint);
                
            }

            //--------------------------------------------------------------------------------------------------------//
            //##### ASSOC #####//
            else if (strArray[0].equals("ASSOC")){
            
                String wordToFindA = strArray[1];
                int numberOfLine = Integer.parseInt(strArray[2]);
                int check = 1;
                int[] occurrencesAuxAssoc = avlTree.findNode(wordToFindA);

                if (occurrencesAuxAssoc.length > 0) {
                    for (i = 0; i < occurrencesAuxAssoc.length; i++){
                        if (occurrencesAuxAssoc[i] == numberOfLine){
                            check = 0;
                        }
                    }
                }

                if (check == 0){
                    System.out.println("ENCONTRADA.");
                } else {
                    System.out.println("NAO ENCONTRADA.");
                }

          
            }
        }

    }



// Defining AVLTree Node
class Node {
    String key;
    int[] textOccurrences;
    int height;
    Node left, right;

    Node (String key){
        this.key = key;
        left = right = null;
    }

    Node (int[] textOccurrences){
        this.textOccurrences = textOccurrences;
    }
}

// Defining AVLTree class
class AVLTree {
    Node root;

    // Get height of Tree
    int getHeight (Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }

    void updateHeight(Node node){
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    // Get Balance Factor
    // If BF = +/- 2, changes must be made
    int getBalanceFactor(Node node){
        if (node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // Get maximum of 2 numbers
    int maxNum (int num1, int num2){
        if (num1 > num2){
            return num1;
        } else if (num1 < num2){
            return num2;
        } else
            return num1;
    }

    // Get words by alphabetical order
    // Using compareTo():
    // str1 > str2, returns positive number, that is, str2 comes first than str1
    // str1 < str2, returns negative number, that is, str1 comes first than str2
    // str1 = str2, returns 0
    int maxString(String str1, String str2){
        int res = str1.compareToIgnoreCase(str2);
        return res;
    }

    // Update array
    int[] updateArray(int[] array, int newElement){
        int check = 0;
        for (int i = 0; i < array.length; i++){
            if (array[i] == newElement){
                check = 1;
                return array;
            }
        }
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = newElement;
        return array;
    }

    /** ##### Simple Rotations #####
     *
     * Right Rotation ( y is the root of the subtree )
              y                                 x
             / \                               / \
            x  b2      Right Rotating (y)     b1  y
           / \                                   / \
         b1   z                                 z   b2

     */

    Node rightRotation(Node y){

        Node x = y.left;
        Node z = x.right;

        // Rotation core
        x.right = y;
        y.left = z;

        updateHeight(y);
        updateHeight(x);

        // New Root updated takes place
        return x;
    }


    /**
     *
     * Left Rotation ( x is the root of the subtree )
              y                                 x
             / \                               / \
            b1  x     Left Rotating (y)       y  b2
               / \                           / \
              z  b2                         b1  z

     */

    Node leftRotation(Node x){

        Node y = x.right;
        Node z = y.left;

        // Rotation core
        y.left = x;
        x.right = z;

        updateHeight(x);
        updateHeight(y);

        // New Root updated taking place
        return y;

    }

    /**          ##### Complex Rotations #####
         RIGHT - LEFT
              z                                 z                                      x
             / \                               / \                                   /   \
            b1  y     Right Rotating (y)      b1  x        Left Rotation (z)        z     y
               / \                               / \                               / \   / \
              x  b2                             b2  y                             b1 b2 b3 b4
             / \                                   / \
            b3 b4                                 b3 b4

         LEFT - RIGHT
              z                                 z                                      x
             / \                               / \                                   /   \
            y  b1     Left Rotating (y)       x  b1        Right Rotation (z)       y     z
           / \                               / \                                   / \   / \
          b2  x                             y  b2                                 b4 b3 b2 b1
             / \                           / \
            b3 b2                         b4 b3

     */
    

    // Perform Insertion on Tree
    Node insertNewNode(Node node, String key, int textPos){

        if (node == null){
            int[] ocurrencesAux = new int[1];
            ocurrencesAux[0] = textPos;
            Node newOne = new Node(key);
            newOne.textOccurrences = Arrays.copyOf(ocurrencesAux, ocurrencesAux.length);
            A1.distinctWordCounter++;
            return newOne;
        }

        if (maxString(key, node.key) < 0){
            node.left = insertNewNode(node.left, key, textPos);
        }
        else if (maxString(key, node.key) > 0){
            node.right = insertNewNode(node.right, key, textPos);
        }
        //Duplicate key case
        else {
            node.textOccurrences = updateArray(node.textOccurrences, textPos);
            return node;
        }

        updateHeight(node);


        //RL ROTATION - key comes first
        if (getBalanceFactor(node) < -1 && maxString(key, node.right.key) < 0){
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        //RR ROTATION - key comes after
        if (getBalanceFactor(node) < -1 && maxString(key, node.right.key) > 0){
            A1.totalNumberRotationsCounter += 1;
            return leftRotation(node);
        }
        //LR ROTATION - key comes first
        if (getBalanceFactor(node) > 1 && maxString(key, node.left.key) > 0){
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }
        //LL ROTATION - key comes after
        if (getBalanceFactor(node) > 1 && maxString(key, node.left.key) < 0){
            A1.totalNumberRotationsCounter += 1;
            return rightRotation(node);
        }

        return node;
    }


    // Perform the search of a given key in the Tree - LINHAS
    int[] findNode(String key){
        Node currentNode = root;
        while (currentNode != null){

            if (maxString(key, currentNode.key) == 0){
                return currentNode.textOccurrences;
            }

            else if (maxString(key, currentNode.key) < 0){
                currentNode = currentNode.left;
            }
            else {
                currentNode = currentNode.right;
            }

        }

        int[] nonExistingOccurrence = new int[0];
        return nonExistingOccurrence;
    }


    // Print tree in order
    void inOrder(){
        inOrder(root);
    }

    void inOrder(Node node){
        if (node != null){
            inOrder(node.left);
            System.out.println(node.key + " ");
            inOrder(node.right);
        }
    }


}

    
    // Read input per line
    // Can also read input from files on terminal: java filename_java.java < filename_txt.txt
    static String readLn (int maxLg){ //utility function to read from stdin
        byte lin[] = new byte [maxLg]; int lg = 0, car = -1;
        String line = "";
        try {
            while (lg < maxLg){
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break; lin [lg++] += car;
            } }
        catch (IOException e){
            return (null);
        }
        if ((car < 0) && (lg == 0)) return (null); // eof
        return (new String (lin, 0, lg));
    }

}
