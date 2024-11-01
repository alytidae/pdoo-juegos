/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

public class Player {
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row, col;
    private int consecutiveHits = 0;
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    public Player(char number, float intelligence, float strength){
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.name = "Player #" + number;
        this.health = INITIAL_HEALTH;
    }
    
    public void ressurect(){
        health = INITIAL_HEALTH;
        consecutiveHits = 0;
        weapons = new ArrayList<Weapon>();
        shields = new ArrayList<Shield>();
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public char getNumber(){
        return number;
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col; // TODO: this probably needs some checking before setting the values
    }
    
    public boolean dead(){
        return (health <= 0);
    }
    
    private float sumWeapons(){
        float output = 0f;
        for(int i = 0; i < weapons.size(); i++){
            Weapon wpn = weapons.get(i);
            output += wpn.attack();
        }
        return output;
    }
    
    public float attack(){
        return (strength + sumWeapons());
    }
    
    private boolean manageHit(float receivedAttack){
        throw new UnsupportedOperationException();
    }
    
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    @Override
    public String toString(){
        String output = name + "(";
        output += "health: " + Float.toString(health) + ", ";
        output += "intelligence: " + Float.toString(intelligence) + ", ";
        output += "strength: " + Float.toString(strength) + ", ";
        output += "pos: " + "(" + Integer.toString(row) + ", " + Integer.toString(col) + "), ";
        output += "consecutive hits: " + Integer.toString(consecutiveHits) + ")";
        return output;
    }
    
    private Weapon newWeapon(){
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }
    
    private Shield newShield(){
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }
    
    private float sumShields(){
        float output = 0f;
        for(int i = 0; i < shields.size(); i++){
            Shield shl = shields.get(i);
            output += shl.protect();
        }
        return output;
    }
    
    private float defensiveEnergy(){
        return (intelligence + sumShields());
    }
    
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    private void gotWounded(){
        health -= 1f;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        throw new UnsupportedOperationException();
    }
    
    public void receiveReward(){
        throw new UnsupportedOperationException();
    }
    
    private void receiveWeapon(Weapon w){
        throw new UnsupportedOperationException();
    }
    
    private void receiveShield(Shield s){
        throw new UnsupportedOperationException();
    }
}
