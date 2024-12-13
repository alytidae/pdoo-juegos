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
    
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        Directions ideal_move = super.move(direction, validMoves);
        return Dice.nextStep(ideal_move, validMoves, getIntelligence());
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
