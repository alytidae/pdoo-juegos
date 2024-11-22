/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

public abstract class LabyrinthCharacter {
    private final int UNINITIALIZED_POS = -1;
    private String name;
    private float health;
    private float intelligence;
    private float strength;
    private int row, col;
    
    public LabyrinthCharacter(String name, float intelligence, float strength, float health){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.name = name;
        row = col = UNINITIALIZED_POS;
    }
    
    public LabyrinthCharacter(LabyrinthCharacter other){
        this.name = other.name;
        this.strength = other.strength;
        this.intelligence = other.intelligence;
        this.health = other.health;
        this.row = other.row;
        this.col = other.col;
    }
    
    public boolean dead(){
        return (health <= .0f);
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    protected float getIntelligence(){
        return intelligence;
    }
    
    protected float getStrength(){
        return strength;
    }
    
    protected float getHealth(){
        return health;
    }
    
    protected void setHealth(float health){
        this.health = health;
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    @Override
    public String toString(){
        String output = name + "(";
        output += "health: " + Float.toString(health) + ", ";
        output += "intelligence: " + Float.toString(intelligence) + ", ";
        output += "strength: " + Float.toString(strength) + ", ";
        output += "pos: [" + Integer.toString(row) + "," + Integer.toString(col) + "])";
        return output;
    }
    
    protected void gotWounded(){
        health -= 1f;
    }
    
    public abstract float attack();
    
    public abstract boolean defend(float attack);
}
