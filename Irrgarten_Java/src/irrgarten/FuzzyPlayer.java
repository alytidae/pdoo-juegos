/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;
import java.util.Arrays;

public class FuzzyPlayer extends Player {
    
    public FuzzyPlayer(Player otro){
        super(otro);
    }
    
    public Directions move(Directions direction, Directions[] validMoves){
        ArrayList<Directions> validMovesList = new ArrayList<>(Arrays.asList(validMoves));
        Directions ideal_move = super.move(direction, validMovesList);
        return Dice.nextStep(ideal_move, validMovesList, getIntelligence());
    } 
    
    @Override
    public float attack(){
        return Dice.intensity(getStrength()) + sumWeapons();
    }
    
    @Override
    protected float defensiveEnergy(){
        return Dice.intensity(getIntelligence()) + sumShields();
    }
    
    @Override
    public String toString(){
        return "FUZZY" + super.toString();
    }
}
