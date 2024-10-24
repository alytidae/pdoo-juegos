/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;

public class Game {
    private static final int MAX_ROUNDS = 10;
    private static final int NUM_ROWS = 20; //Not sure where else to put these values, best not to leave them as magic numbers
    private static final int NUM_COLS = 30; 
    private int currentPlayerIndex;
    private String log;
    private Labyrinth labyrinth;
    private ArrayList<Monster> monsters;
    private Player currentPlayer;
    private ArrayList<Player> players;
    
    public Game(int nplayers){
        players = new ArrayList<Player>();
        for(int i = 0; i < nplayers; i++){
            assert(0 < nplayers && nplayers <= 10);
            char number = Integer.toString(i).charAt(0);
            Player thisPlayer = new Player(number, Dice.randomIntelligence(), Dice.randomStrength());
            players.add(thisPlayer);
        }
        currentPlayerIndex = Dice.whoStarts(nplayers);
        currentPlayer = players.get(currentPlayerIndex);
        monsters = new ArrayList<Monster>();
        labyrinth = new Labyrinth(NUM_ROWS, NUM_COLS, NUM_ROWS - 1, NUM_COLS - 1); //Values for exit position are placeholders
        configureLabyrinth();
        labyrinth.spreadPlayers(players);
        log = "";
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    private String playersString(){
        String output = "{";
        for(int i = 0; i < players.size(); i++){
            Player p = players.get(i);
            output += p.toString();
            if (i < players.size() - 1) output += "; ";
        }
        output += '}';
        return output;
    }
    
    private String monstersString(){
        String output = "{";
        for(int i = 0; i < monsters.size(); i++){
            Monster m = monsters.get(i);
            output += m.toString();
            if (i < monsters.size() - 1) output += "; ";
        }
        output += '}';
        return output;
    }
    
    public GameState getGameState(){
        return new GameState(labyrinth.toString(), playersString(), monstersString(), currentPlayerIndex, finished(), log);
    }
    
    private void configureLabyrinth(){
        //placeholder (obviously)
        final int NUM_MONSTERS = 2;
        labyrinth.addBlock(Orientation.HORIZONTAL, 2, 2, 2);
        for(int i = 0; i < NUM_MONSTERS; i++){
            Monster m = new Monster("MONSTER " + Integer.toString(i), Dice.randomIntelligence(), Dice.randomStrength());
            monsters.add(m);
            labyrinth.addMonster(Dice.randomPos(NUM_ROWS), Dice.randomPos(NUM_COLS), m);
        }
    }
    
    private void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    private void logPlayerWon(){
        log += "El jugador ha ganado el combate.\n";
    }
    
    private void logMonsterWon(){
        log += "El monstruo ha ganado el combate.\n";
    }
    
    private void logResurrected(){
        log += "El jugador ha sido resucitado.\n";
    }
    
    private void logPlayerSkipTurn(){
        log += "El jugador está muerto. Saltando turno...\n";
    }
    
    private void logPlayerNoOrders(){
        log += "El jugador no ha podido seguir las instrucciones dadas.\n";
    }
    
    private void logNoMonster(){
        log += "El jugador se ha movido a una celda vacía, o no ha podido moverse.\n";
    }
    
    private void logRounds(int rounds, int max){
        log += "Se han producido " + Integer.toString(rounds) + "/" + Integer.toString(max) + "rondas de combate.\n";
    }
    
    public boolean nextStep(Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    
    private Directions actualDirection(Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    
    private GameCharacter combat(Monster monster){
        throw new UnsupportedOperationException();
    }
    
    private void manageReward(GameCharacter winner){
        throw new UnsupportedOperationException();
    }
    
    private void manageResurrection(){
        throw new UnsupportedOperationException();
    }
}

