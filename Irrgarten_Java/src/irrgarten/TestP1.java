/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

public class TestP1 {
    
    public static void main(String[] args) {
        System.out.println("== SHIELD / WEAPON TESTS ==");
        shield_weapon_tests();
        System.out.println("== DICE TESTS ==");
        dice_tests();
        System.out.println("== GAMESTATE CLASS TESTS ==");
        gamestate_tests();
    }
    
    private static void shield_weapon_tests() {
        Shield S = new Shield(5.0f,3);
        System.out.printf("%s has been added\n", S.toString());
        System.out.println("protecting...");
        for(int i = 0; i < 5; i++){
            System.out.println(S.protect());
        }
        System.out.print("\n");
        
        Weapon W = new Weapon(5.0f, 3);
        System.out.printf("%s has been added\n", W.toString());
        System.out.println("attacking...");
        for(int i = 0; i < 5; i++){
            System.out.println(W.attack());
        }
    }
    
    private static void dice_tests(){
        final int NUM_TESTS = 100;
        System.out.println("Testing method randomPos...");
        {
            float average = 0.0f;
            for(int i = 0; i < NUM_TESTS; i++)
                average += Dice.randomPos(10);
            average /= NUM_TESTS;
            System.out.printf("    Average value: %f\n", average);
        }
        
        System.out.println("Testing method randomIntelligence...");
        {
            float average = 0.0f;
            for(int i = 0; i < NUM_TESTS; i++)
                average += Dice.randomIntelligence();
            average /= NUM_TESTS;
            System.out.printf("    Average value: %f\n", average);
        }
        
        System.out.println("Testing method resurrectPlayer...");
        {
            int timesResurrected = 0;
            int timesNotResurrected = 0;
            for(int i = 0; i < NUM_TESTS; i++) {
                if(Dice.resurrectPlayer()){
                    timesResurrected++;
                } else {
                    timesNotResurrected++;
                }
            }
            System.out.printf("    %d times resurrected, %d times not resurrected \n", timesResurrected, timesNotResurrected);
        }
        
        System.out.println("Testing method shieldsReward...");
        {
            float average = 0.0f;
            int max = Dice.shieldsReward();
            int min = max;
            for(int i = 0; i < NUM_TESTS - 1; i++){
                int newValue = Dice.shieldsReward();
                average += newValue;
                if(newValue > max) max = newValue;
                else if(newValue < min) min = newValue;
            }
            average /= NUM_TESTS;
            System.out.printf("    Average value: %f\n", average);
            System.out.printf("    Minimum and maximum: %d, %d\n", min, max);
        }
        
        System.out.println("Testing method discardElement...");
        {
           final int totalUses = 4;
           for(int uses = totalUses; uses >= 0; uses--){
               System.out.printf("    Testing with %d/%d uses...\n", uses, totalUses);
               int timesDiscarded = 0, timesNotDiscarded = 0;
               for(int i = 0; i < NUM_TESTS/(1+totalUses); i++){
                   if(Dice.discardElement(uses)){
                       timesDiscarded++;
                   } else {
                       timesNotDiscarded++;
                   }
               }
               System.out.printf("      %d times discarded, %d times not discarded \n", timesDiscarded, timesNotDiscarded);
           }
        }
    }
    
    
    private static void gamestate_tests(){
        GameState Game = new GameState("Labyrinth(...)", "[P1, P2, P3]", "[M1]", 2, false, "(No entries)");
        System.out.println("The following gamestate has been created, with:");
        System.out.printf("\tLabyrinth: %s\n"
                + "\tPlayers: %s\n"
                + "\tMonsters: %s\n"
                + "\tCurrent Player: %d\n"
                + "\tWinner Check: %b\n"
                + "\tLog: %s\n", Game.getLabyrinth(), Game.getPlayers(), Game.getMonsters(), Game.getCurrentPlayer(), Game.isWinner(), Game.getLog());
    }
}
