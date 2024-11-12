/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;


public class Labyrinth {

    public static char BLOCK_CHAR = 'X';
    public static char EMPTY_CHAR = '-';
    public static char MONSTER_CHAR = 'M';
    public static char COMBAT_CHAR = 'C';
    public static char EXIT_CHAR = 'E';
    private static int ROW = 0;
    private static int COL = 1;

    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    private char[][] labyrinth;
    private Monster[][] monsters;
    private Player[][] players;

    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        labyrinth = new char[nRows][nCols];
        players = new Player[nRows][nCols];
        monsters = new Monster[nRows][nCols];

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                labyrinth[i][j] = EMPTY_CHAR;
            }
        }

        labyrinth[exitRow][exitCol] = EXIT_CHAR;
    }

    public boolean haveAWinner() {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                Player player = players[i][j];
                if (player != null && exitPos(player.getRow(), player.getCol())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean posOK(int row, int col) {
        return (0 <= row && row < nRows && 0 <= col && col < nCols);
    }

    private boolean emptyPos(int row, int col) {
        return posOK(row, col) && labyrinth[row][col] == EMPTY_CHAR;
    }

    private boolean exitPos(int row, int col) {
        return posOK(row, col) && labyrinth[row][col] == EXIT_CHAR;
    }

    private boolean monsterPos(int row, int col) {
        return posOK(row, col) && labyrinth[row][col] == MONSTER_CHAR;
    }

    private boolean combatPos(int row, int col) {
        return posOK(row, col) && labyrinth[row][col] == COMBAT_CHAR;
    }

    private boolean canStepOn(int row, int col) {
        return posOK(row, col) && (emptyPos(row, col) || exitPos(row, col) || monsterPos(row, col));
    }

    public void addMonster(int row, int col, Monster monster) {
        if (posOK(row, col) && emptyPos(row, col)) {
            labyrinth[row][col] = MONSTER_CHAR;
            monsters[row][col] = monster;
            monster.setPos(row, col);
        }
    }

    private void updateOldPos(int row, int col) {
        if (posOK(row, col)) {
            if (labyrinth[row][col] == COMBAT_CHAR) {
                labyrinth[row][col] = MONSTER_CHAR;
            } else {
                labyrinth[row][col] = EMPTY_CHAR;
            }
        }
    }

    private int[] dir2Pos(int row, int col, Directions direction) {
        switch (direction) {
            case LEFT:
                return new int[] { row, col - 1 };
            case RIGHT:
                return new int[] { row, col + 1 };
            case UP:
                return new int[] { row - 1, col };
            case DOWN:
                return new int[] { row + 1, col };
            default:
                return new int[] { row, col };
        }
    }

    private int[] randomEmptyPos() {
        int[] pos = new int[] { Dice.randomPos(nRows), Dice.randomPos(nRows) };
        return pos;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                output.append(labyrinth[i][j]);
            }
            output.append("\n");
        }
        return output.toString();
    }

    public void spreadPlayers(ArrayList<Player> players) {
        for(int i = 0; i < players.size(); i++){
            int[] pos = randomEmptyPos();
            putPlayer2D(-1, -1, pos[ROW], pos[COL], players.get(i));
        }
    }

    public Monster putPlayer(Directions direction, Player player) {
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newPos = dir2Pos(oldRow, oldCol, direction);
        Monster output = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        return output;
    }

    public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
        int incRow = 0;
        int incCol = 0;
        if(orientation == Orientation.VERTICAL){
            incRow = 1;
        } else {
            incCol = 1;
        }
        
        int row = startRow;
        int col = startCol;
        
        while (posOK(row, col) && length > 0){
            labyrinth[row][col] = BLOCK_CHAR;
            row += incRow;
            col += incCol;
            length--;
        }
    }

    public ArrayList<Directions> validMoves(int row, int col) {
        ArrayList<Directions> output = new ArrayList<Directions>();
        if(canStepOn(row+1, col)){
            output.add(Directions.DOWN);
        }
        if(canStepOn(row-1, col)){
            output.add(Directions.UP);
        }
        if(canStepOn(row, col-1)){
            output.add(Directions.LEFT);
        }
        if(canStepOn(row, col+1)){
            output.add(Directions.RIGHT);
        }
        return output;
    }

    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player) {
        Monster output = null;
        if(canStepOn(row, col)){
            if(posOK(oldRow, oldCol)){
                Player p = players[oldRow][oldCol];
                if(p == player){
                    updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol] = null;
                }
            }
            
            if(monsterPos(row, col)){
                labyrinth[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            } else if (!exitPos(row, col)) {
                char number = player.getNumber();
                labyrinth[row][col] = number;
            }
        }
        players[row][col] = player;
        player.setPos(row, col);
        return output;
    }
}
