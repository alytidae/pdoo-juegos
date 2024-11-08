/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irrgarten;


public class Shield {
    private float protection;
    private int uses;
    
    public Shield (float protection, int uses){
        this.protection = protection;
        this.uses = uses;
    }

    public float protect() {
        if (uses > 0) {
            uses--; 
            return protection; 
        } else {
            return 0;
        }
    }

    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    @Override
    public String toString() {
        return "S[" + protection + ", " + uses + "]";
    }
}
