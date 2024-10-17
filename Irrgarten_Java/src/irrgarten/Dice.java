/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.Random;
public class Dice {
    private static int MAX_USES = 5;
    private static float MAX_INTELLIGENCE = 10.0f;
    private static float MAX_STRENGTH = 10.0f;
    private static float RESURRECT_PROB = 0.3f;
    private static int WEAPONS_REWARD = 2;
    private static int SHIELDS_REWARD = 3;
    private static int HEALTH_REWARD = 5;
    private static float MAX_ATTACK = 3.0f;
    private static float MAX_SHIELD = 2.0f;
    
    private static Random generator = new Random();
    
    public static int randomPos(int max){ return generator.nextInt(max); }
    public static int whoStarts(int nplayers){ return generator.nextInt(nplayers); }
    public static float randomIntelligence(){ return generator.nextFloat() * MAX_INTELLIGENCE; } // Nota: nextFloat siempre devuelve un valor de 0.0 a 1.0
    public static float randomStrength(){ return generator.nextFloat() * MAX_STRENGTH; }
    public static boolean resurrectPlayer(){ return (generator.nextFloat() <= RESURRECT_PROB); }
    public static int weaponsReward(){ return generator.nextInt(1 + WEAPONS_REWARD); }
    public static int shieldsReward(){ return generator.nextInt(1 + SHIELDS_REWARD); }
    public static int healthReward(){ return generator.nextInt(1 + HEALTH_REWARD); }
    public static float weaponPower(){ return generator.nextFloat() * MAX_ATTACK; }
    public static float shieldPower(){ return generator.nextFloat() * MAX_SHIELD; }
    public static int usesLeft(){ return generator.nextInt(1 + MAX_USES); }
    public static float intensity(float competence){ return generator.nextFloat() * competence; }
    public static boolean discardElement(int uses_left){
        double keepProbability = (1.0*uses_left)/MAX_USES;
        return (generator.nextFloat() >= keepProbability);
    }
    
    
}
