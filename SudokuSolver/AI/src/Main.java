import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {


    public static char[][] read() throws FileNotFoundException {
        //Scanner sc = new Scanner(new BufferedReader(
                //new FileReader("/Users/abbeyhustis/MSU2122/CSCI446/project1/testMatrix.txt")));

        ///Users/abbeyhustis/MSU2122/CSCI446/project1/puzzles/Easy-P1.csv
        // C:\Users\maxli\OneDrive\Desktop\Easy-p1.csv

        Scanner sc = new Scanner(new BufferedReader(
                new FileReader("/Users/abbeyhustis/MSU2122/CSCI446/project1/puzzles/evil-p2.csv")));

        sc.useDelimiter("[,\\n]");   //sets the delimiter pattern

        //closes the scanner

        char [][] matrix = new char[9][9];
        int counter = 0;
        while (sc.hasNext()) { //returns a boolean value
            // read information from somewhere
            int i = counter / 9;    //integer division to get the row
            int j = counter % 9;    //remainder division to get the column

            matrix[i][j] = sc.next().charAt(0);
            counter++;
        }

        //System.out.println((matrix[1][0]));

        sc.close();

        return matrix;
    }



    public static void main(String[] args) throws Exception {
        char [][] m = read();
        //System.out.println(Arrays.deepToString(m));
        //GeneticAlgorithm.geneticAlgorithm(m);

//        char[][] temp = GeneticAlgorithm.randomizeMatrix(m);
//        System.out.println(Arrays.deepToString(temp));
//        int tempFitness = GeneticAlgorithm.fitnessFunction(temp);
//        System.out.println(tempFitness);


        SimulatedAnnealing.annealing(m);


//        if(BackTrack.simpleBacktracking(m)){
//            System.out.println(Arrays.deepToString(m));
//            System.out.println("simple: " + BackTrack.simpleStepCounter);
//        }

//        BackTrack.forwardCheckingSetup(m);
//        if(BackTrack.backtrackForwardChecking(m)){
//            System.out.println(Arrays.deepToString(m));
//            System.out.println("forward: " + BackTrack.forwardStepCounter);
//        }


    }
}


