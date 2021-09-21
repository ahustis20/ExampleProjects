import java.util.Arrays;
import java.util.Random;

public class SimulatedAnnealing {
    public static char [][] initialMatrix = new char[9][9];
    public static char [][] solutionMatrix;

    public static void annealing(char[][] m){
        //creates duplicate of original matrix for comparisons
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                initialMatrix[i][j] = m[i][j];
            }
        }

        solutionMatrix = m.clone();
        randomize(solutionMatrix);
        //System.out.println(Arrays.deepToString(initialMatrix));


        double temperature = 1000;
        double cooling = 0.9;
        Random rando = new Random();

        for(double t = temperature; t > 1; t *= cooling){
            //System.out.println("t " + t);

            int r1 = rando.nextInt(8);
            int r2 = rando.nextInt(8);

            //makes sure it is valid spot for a new random value
            if(initialMatrix[r1][r2] == '?'){
                int originalValue = solutionMatrix[r1][r2];
                int newValue = rando.nextInt(9) + 1;

                //original constraints
                ConstraintChecker.checkConstraints(solutionMatrix);
                int originalConstraints = ConstraintChecker.getConstraintsFailed();

                solutionMatrix[r1][r2] = (char)(newValue + '0');

                //new constraints
                ConstraintChecker.checkConstraints(solutionMatrix);
                int newConstraints = ConstraintChecker.getConstraintsFailed();


                //System.out.println("oc " + originalConstraints);
                System.out.println("nc " + newConstraints);

                //calculates the chance of changing
                int change = originalConstraints - newConstraints;
                int k = 1;
                double chanceOfChange = Math.exp(change/(k*t));
                //System.out.println("chance " + chanceOfChange);

                if(change > 0){
                    break;
                    //leave value
                }
                else{
                    solutionMatrix[r1][r2] = (char)(originalValue + '0');

                    //if chance is < random value from 0 to 1
                    if(chanceOfChange < Math.random()){
                        solutionMatrix[r1][r2] = (char)(newValue + '0');
                    }
                }
            }
        }
        //System.out.println(Arrays.deepToString(solutionMatrix));

    }

    //adds random values to every empty space
    public static void randomize(char[][] solutionMatrix){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(solutionMatrix[i][j] == '?'){
                    Random rand = new Random();
                    int randNum = rand.nextInt(9) + 1;
                    //System.out.println(randNum);
                    solutionMatrix[i][j] = (char)(randNum + '0');

                }
            }
        }

    }
}


