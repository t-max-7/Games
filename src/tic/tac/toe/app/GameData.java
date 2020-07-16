package tic.tac.toe.app;

import java.util.Arrays;

/*actual number of levels will be one more because data structure looks like this for GameData(3)
where 0 or 1 represents index in branch[index].
 b is short for branch.
 !!!!GameData with empty branch array still can holds points which may cause errors in code!!!!

                        GameData
              /            \               \
      .branch[0]       .branch[1]      .branch[2]
         /   \           /   \            /   \
     .b[0] .b[1]     .b[0] .b[1]      .b[0] .b[1]
       |      \         |     \         |       \
    .b[0]   .b[0]    .b[0]  .b[0]   .b[0]    .b[0]
      |        \       |       \      |         \
    empty     empty  empty    empty  empty      empty
 */
public class GameData {
    GameData[] branch = null;
    int numberOfBranches = 0;
    int points = 0;
    //////////////////////////////////// feasible?
    boolean shouldAdd = false;
    ///  ///// /////////////////////////


    //Constructor that constructs GameData with array of GameData objects stored in "branch" attribute with a length of how many levels is in the GameData Object (excluding level of empty arrays).
    // Each of the GameData Objects stored in the branch is created with a value in its constructor one less then previous call which then calls the
    // constructor again to fill its "branch" attribute and so on until level of empty arrays is created.
    //
    public GameData(int numberOfLevels) {
        //changed from >1
        if (numberOfLevels > -1) {
            branch = new GameData[numberOfLevels];
            numberOfBranches = numberOfLevels;
            for (int i = 0; i < branch.length; i++) {
                branch[i] = new GameData(numberOfLevels - 1);
            }

        }
    }

    // returns which branch below the last one in the gameBoard has the highest points
    public int getBranchWithHighestPoints(int[] TheGameBoard){
        int branchWithHighestPoints = 0;

        if(TheGameBoard.length == 1){
            GameData b = this.branch[TheGameBoard[0]];
            for(int i = 0; i < b.branch.length; i++){
                if(b.branch[i].points >= b.branch[branchWithHighestPoints].points){
                    branchWithHighestPoints = i;
                    System.out.println("      branch with highest points:                      "+branchWithHighestPoints + "    points:  " + b.branch[branchWithHighestPoints].points);
                } else{
                    System.out.println("! not branch with highest points:                      " + i +"    points:  " + b.branch[i].points);
                }
            }
        }
        else if(TheGameBoard.length > 1){
            GameData b = this.branch[TheGameBoard[0]];

            for(int i = 1; i < TheGameBoard.length; i++){
                b = b.branch[TheGameBoard[i]];
            }

            for(int i = 0; i < b.branch.length; i++){
                if(b.branch[i].points >= b.branch[branchWithHighestPoints].points){
                    branchWithHighestPoints = i;
                    System.out.println("      branch with highest points with board size > 1:  " + branchWithHighestPoints +"    points:  "+ b.branch[branchWithHighestPoints].points);
                } else{
                    System.out.println("! not branch with highest points with board size > 1:  " + i +"    points:  " + b.branch[i].points);
                }
            }
        }
        System.out.println();
        return branchWithHighestPoints;
    }
    // adds same amount of points to each branch that is in the GameBoard
    public void addPoints(int[] TheGameBoard, int points){
            GameData b = this.branch[TheGameBoard[0]];
            // Just for checking point addition with System.out.println()
            int previousPoints = b.points;
            //////////////////////////
            b.points += points;
            System.out.println("level 0, branch " + TheGameBoard[0] +  ", point calculation: " + previousPoints + " + " + points + " = " + b.points);

            for(int i = 1; i < TheGameBoard.length; i++) {
                b = b.branch[TheGameBoard[i]];
                // Just for checking point addition with System.out.println()
                previousPoints = b.points;
                //////////////////////
                b.points += points;
                System.out.println("level " + i + ", branch " + TheGameBoard[i] + ", point calculation: " + previousPoints + " + " + points + " = " + b.points);
            }
            System.out.println("--------------------------------------------------------------------");
    }

    public static void main(String...args){
        GameData data = new GameData(7);
        int[] AGameBoard = new int[]{6,5,4,3,2,1,0};
        data.addPoints(AGameBoard, -3);

//        System.out.println(data.branch.length);
//        System.out.println(data.branch[2].branch[1].points);
//        System.out.println(data.branch[2].branch[1].branch[0].points);

        int[] secondGameBoard = new int[]{6,5};

        data.getBranchWithHighestPoints(secondGameBoard);
        data.addPoints(AGameBoard, -3);

//        System.out.println(data.branch[2].branch[1].points);
//        System.out.println(data.branch[2].branch[1].branch[0].points);
        //data.getBranchWithHighestPoints(secondGameBoard);

        data.addPoints(AGameBoard, -3);
        data.addPoints(AGameBoard, -3);

        int[] thirdGameBoard = new int[]{6,2,3,3,2,1,0};
        data.addPoints(thirdGameBoard, 4);
        data.addPoints(thirdGameBoard, 4);
        data.addPoints(AGameBoard, 0);


    }
}
