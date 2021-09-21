import java.util.Arrays;

public class ConstraintChecker {

    public static int constraintsFailed;

    public static boolean checkColumn(char[][]matrix, int col){
        char [] currentCol = new char[9];

        //creates array for col
        for(int i = 0; i < 9; i++){
            currentCol[i] = matrix[i][col];
        }

        //sorts array
        Arrays.sort(currentCol);

        //checks for duplicates
        for(int i = 0; i < 8; i++){
            if(currentCol[i] == currentCol[i+1] & currentCol[i] != '?'){
                return false;

            }
        }
        return true;
    }

    public static boolean checkRow(char[][]matrix, int row){
        char [] currentRow = new char[9];

        //creates array for row
        for(int i = 0; i < 9; i++){
            currentRow[i] = matrix[row][i];
        }

        //sorts array
        Arrays.sort(currentRow);

        //checks for duplicates
        for(int i = 0; i < 8; i++){
            if(currentRow[i] == currentRow[i+1] & currentRow[i] != '?'){
                return false;
            }
        }
        return true;
    }

    public static boolean checkBoxes(char[][]matrix, int box){

        //gets the start index, top left, for the box we're checking
        char [] boxStart = new char[2];
        switch (box){
            case 0:
                boxStart[0] = 0;
                boxStart[1] = 0;
                break;
            case 1:
                boxStart[0] = 0;
                boxStart[1] = 3;
                break;
            case 2:
                boxStart[0] = 0;
                boxStart[1] = 6;
                break;
            case 3:
                boxStart[0] = 3;
                boxStart[1] = 0;
                break;
            case 4:
                boxStart[0] = 3;
                boxStart[1] = 3;
                break;
            case 5:
                boxStart[0] = 3;
                boxStart[1] = 6;
                break;
            case 6:
                boxStart[0] = 6;
                boxStart[1] = 0;
                break;
            case 7:
                boxStart[0] = 6;
                boxStart[1] = 3;
                break;
            case 8:
                boxStart[0] = 6;
                boxStart[1] = 6;
                break;
            default:
                System.out.println("Trying to check box outside matrix");
                break;
        }

        char [] currentBox = new char[9];

        int nineCounter =0;
        for(int i = boxStart[0]; i < boxStart[0] + 3; i++){
            for(int j = boxStart[1]; j < boxStart[1] + 3; j++){
                currentBox[nineCounter] = matrix[i][j];
                nineCounter++;
            }
        }


        //sorts array
        Arrays.sort(currentBox);

        //checks for duplicates
        for(int i = 0; i < 8; i++){
            if(currentBox[i] == currentBox[i+1] & currentBox[i] != '?'){
                return false;
            }
        }
        return true;
    }

    public static boolean checkConstraints(char[][] matrix){
        constraintsFailed = 0;
        for(int i = 0; i < 9; i ++){
            if(checkRow(matrix, i) == false){
                constraintsFailed ++;
            }
            if(checkColumn(matrix, i) == false){
                constraintsFailed ++;
            }
            if(checkBoxes(matrix, i) == false){
                constraintsFailed ++;
            }
        }

        if (constraintsFailed > 0){
            return false;
        }
        return true;
    }

    public static int getConstraintsFailed(){
        return constraintsFailed;
    }
}
