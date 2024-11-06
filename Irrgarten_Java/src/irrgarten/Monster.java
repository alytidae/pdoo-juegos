/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

public class Monster {
    private static int INITIAL_HEALTH = 5;
    
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row = -1;
    private int col = -1;
    
    public Monster(String name, float intelligence, float strength) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
    }
    
    public boolean dead(){
        return this.health <= 0;
    }
    
    public float attack(){
        return Dice.intensity(strength);
    }
    
    public boolean defend(float receivedAttack){
        boolean isDead = dead();
        if (!isDead){
            float defensiveEnergy = Dice.intensity(intelligence);
            if (defensiveEnergy < receivedAttack){
                gotWounded();
                isDead = dead();
            }
        }
        
        return isDead;
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    @Override
    public String toString() {
        return "Monster: " + name + ", intelligence: " + intelligence + ", strength: " + strength +
                ", health: " + health + ", [row, col]: " + "[" + row + ", " + col + "]";
    } 
    
    public void gotWounded(){
        health -= 1;
    }
}


