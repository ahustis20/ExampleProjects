import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Arrays;

public class BackTrack {

    public static int simpleStepCounter;
    public static int forwardStepCounter;
    public static ArrayList<ArrayList<Character>> domains = new ArrayList<>();


    /**
     * This goes through the matrix and assigns values, if any constraints are violated then it backtracks and
     * tries another
     * @param m the matrix that we're solving
     * @return returns if the matrix is solved or not
     */
    public static boolean simpleBacktracking(char[][] m) {

        boolean solved = true;
        simpleStepCounter ++;
        int row = 0;
        int col = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (m[i][j] == '?') {
                    row = i;
                    col = j;
                    solved = false;
                    break;
                }
                if(!solved){
                    break;
                }
            }
        }
        if (solved) {
            //System.out.println(Arrays.deepToString(m));
            return true;
        }

        for (int n = 1; n < 10; n++) {

            if(ConstraintChecker.checkConstraints(m)) {
                m[row][col] = (char)(n + '0');

                        if(simpleBacktracking(m)){
                            return true;
                        }
                        else {
                            m[row][col] = '?';
                        }
            }
        }

        return false;
    }

    /**
     * This creates the domains for all of the elements in the matrix by giving them their own ArrayList
     * @param m
     */
    public static void forwardCheckingSetup(char[][] m){

        for (int i = 0; i < 81; i++) {

            ArrayList<Character> temp = new ArrayList<>();
            temp.add('1');
            temp.add('2');
            temp.add('3');
            temp.add('4');
            temp.add('5');
            temp.add('6');
            temp.add('7');
            temp.add('8');
            temp.add('9');

            BackTrack.domains.add(temp);

        }

        for (int i = 0; i < 81; i++) {
            int currentElementRow = i / 9;
            int currentElementCol = i % 9;

            if(m[currentElementRow][currentElementCol] != '?') {
                removeDomain(currentElementRow, currentElementCol, m[currentElementRow][currentElementCol]);
            }
        }

    }

    /**
     * This function goes through the matrix and assigns values and edits the domain of all the other affected
     * elements
     * @param m the matrix that we're solving
     * @return Returning if the matrix has been solved or not
     */
    public static boolean backtrackForwardChecking(char[][] m){

        boolean solved = true;
        forwardStepCounter ++;
        int row = -1;
        int col = -1;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (m[i][j] == '?') {
                    row = i;
                    col = j;
                    solved = false;
                    break;
                }
                if(!solved){
                    break;
                }
            }
        }
        if (solved) {
            return true;
        }

        for (int n = 1; n < 10; n++) {
            if(ConstraintChecker.checkConstraints(m)) {
                m[row][col] = (char)(n + '0');
                removeDomain(row, col, (char)(n + '0'));
                for (int i = 0; i < 81; i++) {
                    int currentDomainRow = i / 9;
                    int currentDomainCol = i % 9;
                    if(domains.get(i).isEmpty() && m[currentDomainRow][currentDomainCol] == '?'){
                        System.out.println("got here");
                        m[row][col] = '?';
                        addDomain(row, col, (char)(n + '0'));
                        return false;
                    }
                }

                if(backtrackForwardChecking(m)){
                    return true;
                }
                else {
                    m[row][col] = '?';
                    addDomain(row, col, (char)(n + '0'));
                }
            }
        }

        return false;
    }

    public static void removeDomain(int row, int column, char value){
        int tempIndex;
        for (int i = 0; i < 9; i++) {
            if(i != row){
                tempIndex = domains.get((row * 9) + i).indexOf(value);
                try{
                    domains.get((row * 9) + i).remove(tempIndex);
                }catch (IndexOutOfBoundsException e){
                    continue;
                }
            }else{
                continue;
            }

            if(i != column){
                tempIndex = domains.get((i * 9) + column).indexOf(value);
                try{
                    domains.get((i * 9) + column).remove(tempIndex);
                }catch (IndexOutOfBoundsException e){
                    continue;
                }
            }else{
                continue;
            }

        }

    }

    public static void addDomain(int row, int column, char value){

        for (int i = 0; i < 9; i++) {
            if (i != row) {
                if (domains.get((row * 9) + i).contains(value)) {
                    continue;
                } else {
                    domains.get((row * 9) + i).add(value);
                }
            } else {
                continue;
            }

            if (i != column) {
                if (domains.get((i * 9) + column).contains(value)) {
                    continue;
                } else {
                    domains.get((i * 9) + column).add(value);
                }
            }else{
                continue;
            }

        }
    }


}
