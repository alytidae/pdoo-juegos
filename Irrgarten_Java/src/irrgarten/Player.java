/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

public class Player extends LabyrinthCharacter{
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    private char number;
    private int consecutiveHits = 0;
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    public Player(char number, float intelligence, float strength){
        super("Player #" + number, intelligence, strength, INITIAL_HEALTH);
        this.number = number;
        this.weapons = new ArrayList<Weapon>();
        this.shields = new ArrayList<Shield>();
    }
    
    public Player(Player other){
        super(other);
        this.number = other.number;
        this.weapons = other.weapons; //Copia superficial pero no pasa nada porque esto se llama antes de borrar el jugador copiado
        this.shields = other.shields;
        this.consecutiveHits = other.consecutiveHits;
    }
    
    public void ressurect(){
        setHealth(INITIAL_HEALTH);
        consecutiveHits = 0;
        weapons = new ArrayList<Weapon>();
        shields = new ArrayList<Shield>();
    }
    
    public char getNumber(){
        return number;
    }
    
    protected float sumWeapons(){
        float output = 0f;
        for(int i = 0; i < weapons.size(); i++){
            Weapon wpn = weapons.get(i);
            output += wpn.attack();
        }
        return output;
    }
    
    
    @Override
    public float attack(){
        return (getStrength() + sumWeapons());
    }
    
    private boolean manageHit(float receivedAttack){
        float defend = defensiveEnergy();
        if (defend < receivedAttack){
            gotWounded();
            incConsecutiveHits();
        }else{
            resetHits();
        }
        
        boolean lose;
        if ((consecutiveHits == HITS2LOSE) || dead()){
            resetHits();
            lose = true;
        }else{
            lose = false;
        }
        
        return lose;
    }
    
    @Override
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    @Override
    public String toString(){
        String output = super.toString();
        output = output.substring(0, output.length() - 1);
        output += ", consecutive hits: " + Integer.toString(consecutiveHits) + ")";
        return output;
    }
    
    private Weapon newWeapon(){
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }
    
    private Shield newShield(){
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }
    
    protected float sumShields(){
        float output = 0f;
        for(int i = 0; i < shields.size(); i++){
            Shield shl = shields.get(i);
            output += shl.protect();
        }
        return output;
    }
    
    protected float defensiveEnergy(){
        return (getIntelligence() + sumShields());
    }
    
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        if (size > 0 && !contained){
            return validMoves.get(0);
        }else{
            return direction;
        }
    }
    
    public void receiveReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for (int i = 1; i < wReward; i++){
            Weapon wnew = newWeapon();
            receiveWeapon(wnew);
        }
        for (int i = 1; i < sReward; i++){
            Shield snew = newShield();
            receiveShield(snew);
        }
        
        int extraHealth = Dice.healthReward();
        setHealth(getHealth() + extraHealth);
    }
    
    private void receiveWeapon(Weapon w){
        for (int i = 0; i < weapons.size(); i++){
            if (weapons.get(i).discard()){
                weapons.remove(i);
            }
        }
        
        if (weapons.size() < MAX_WEAPONS){
            weapons.add(w);
        }
    }
    
    private void receiveShield(Shield s){
        for (int i = 0; i < shields.size(); i++){
            if (shields.get(i).discard()){
                shields.remove(i);
            }
        }
        
        if (shields.size() < MAX_SHIELDS){
            shields.add(s);
        }
    }
}
