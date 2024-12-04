/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irrgarten;


public class Shield extends CombatElement{
    public Shield (float protection, int uses){
        super(protection, uses);
    }

    public float protect() {
        return produceEffect();
    }

    @Override
    public String toString() {
        return "S" + super.toString();
    }
}
