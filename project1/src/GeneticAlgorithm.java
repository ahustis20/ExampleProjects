import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//TODO: if fitness function is equal to 27, then the puzzle has been solved. at the end of the function an if
// statement that is set to a variable, if the variable is null, solution hasn't been found yet
//TODO: decide how i want to mutate things

public class GeneticAlgorithm {

    private static int fitnessScore;
    public static char [][] initialMatrix;
    public static char [][] solutionMatrix;
    public static ArrayList<char[][]> population = new ArrayList<>();
    public static ArrayList<char[][]> newGeneration = new ArrayList<>();
    private static boolean solutionFound = false;
    public static int stepCounter;


    public GeneticAlgorithm(){

    }

    /**
     * Uses the matrix, m, and solves it using the genetic algorithm
     * @param m The matrix that we're solving
     */
    public static void geneticAlgorithm(char[][] m){
        initialMatrix = m.clone();
        createPopulation(m);

        while(!solutionFound){
            stepCounter++;
            tournamentSelection(population);
            crossover();
        }

        System.out.println(stepCounter);
        System.out.println(Arrays.deepToString(solutionMatrix));

    }

    /**
     * This is to create a population of random solutions based on the initial state, initialMatrix.
     * The size of the population can be adjusted by changing the size of the for loop
     * @param initialMatrix The initial matrix that we're solving
     */
    public static void createPopulation(char[][] initialMatrix){
        for (int i = 0; i < 160; i++) {
            char[][] temp = randomizeMatrix(initialMatrix);
            population.add(temp);
        }
    }

    /**
     * This takes in the initial state, m, and fills in the blank states –- where a '?' is -- with a random
     * number 1 through 9
     * @param m the initial matrix that will be filled in
     * @return the random solution
     */
    public static char[][] randomizeMatrix(char[][] m){
        Random rand = new Random();
        char[][] temp = new char[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(m[i][j] == '?'){
                    int randNum = rand.nextInt(9) + 1;
                    temp[i][j] = (char)(randNum + '0');
                }
                else {
                    temp[i][j] = m[i][j];
                }
            }
        }
        return temp;
    }


    /**
     * This runs a solution through our constraint checker and determines how many constraints that
     * it doesn't violate. The higher the fitness score the "better" of a solution it is.
     * @param matrix The solution that we are evaluating
     * @return the fitness score
     */
    public static int fitnessFunction(char[][] matrix){

        int totalPossibleConstraints = 27;
        ConstraintChecker.checkConstraints(matrix);
        fitnessScore = totalPossibleConstraints - ConstraintChecker.getConstraintsFailed();
        //System.out.println(Arrays.deepToString(matrix));
        //System.out.println("fitness function: " + fitnessScore);
        if(fitnessScore == 27){
            solutionFound = true;
            solutionMatrix = matrix.clone();
        }


        return fitnessScore;
    }

    /**
     * This function selects a random portion of the population and puts them into a competitiors ArrayList
     * and then puts them into a bracket.
     * @param population This is the current population that have yet to go into a tournament
     */
    public static void tournamentSelection(ArrayList<char[][]> population){
        Random random = new Random();
        ArrayList<char[][]> competitors = new ArrayList<>();
        char[][] winner;
        int numOfBrackets = 10; //what proportion of the population will be in each bracket (1/10)
        int sizeOfTourny = population.size() / numOfBrackets;

        for (int i = 0; i < numOfBrackets; i++) {

            if (!competitors.isEmpty()) {
                competitors.remove(0);
            }

            for (int j = 0; j < sizeOfTourny; j++) {
                int randomMember = random.nextInt(population.size());
                competitors.add(population.get(randomMember));
                population.remove(randomMember);
            }

            bracket(competitors);
        }

    }

    /**
     * This function looks at a portion of the population and compares their fitness, in a bracket style of
     * play, the individual that has the best fitness score wins the bracket and is then placed into the new
     * generation of the population
     * @param competitors The portion of the population that is going to be in the bracket
     * @return The winner of the bracket
     */
    public static char[][] bracket(ArrayList<char[][]> competitors){

        if(competitors.size() == 1){
            newGeneration.add(competitors.get(0));
            //System.out.println("winner is" + Arrays.deepToString(competitors.get(0)));
            return competitors.get(0);
        }

        if((competitors.size() % 2) != 0){
            competitors.remove(0);
        }
        for (int i = 0; i < competitors.size(); i++) {
            if(fitnessFunction(competitors.get(i)) > fitnessFunction(competitors.get(i+1))){
                competitors.remove(i+1);
            }else{
                competitors.remove(i);
            }
        }
        return bracket(competitors);

//        for (int i = 0; i < competitors.size(); i++) {
//            if(fitnessFunction(competitors.get(i)) > fitnessFunction(competitors.get(i+1))){
//                competitors.remove(i+1);
//            }else{
//                competitors.remove(i);
//            }
//        }
//        return bracket(competitors);

    }

    public static void crossover(){
        char[][] firstChild = new char[9][9];
        char[][] secondChild = new char[9][9];

        for (int n = 0; n < newGeneration.size()-1; n++) {
            //parentA is n, parentB is n+1

            //top half of parent A
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if(newGeneration.get(n)[i][j] == initialMatrix[i][j]) {
                        firstChild[i][j] = initialMatrix[i][j];
                        secondChild[i][j] = initialMatrix[i][j];
                    }else {
                        firstChild[i][j] = newGeneration.get(n)[i][j];
                        secondChild[i][j] = newGeneration.get(n+1)[i][j];
                    }
                }
            }
            //bottom half of parent b
            for (int i = 5; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(newGeneration.get(n)[i][j] == initialMatrix[i][j]) {
                        firstChild[i][j] = initialMatrix[i][j];
                        secondChild[i][j] = initialMatrix[i][j];
                    }else {
                        firstChild[i][j] = newGeneration.get(n+1)[i][j];
                        secondChild[i][j] = newGeneration.get(n)[i][j];
                    }
                }
            }
            population.add(firstChild);
            population.add(secondChild);
            population.add(newGeneration.get(n));
            newGeneration.remove(n);


        }

    }

}
