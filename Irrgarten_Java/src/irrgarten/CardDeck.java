/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;
import java.util.Collections;

public abstract class CardDeck<TYPE>{ //TODO: after implementing class CombatElement, add restriction TYPE extends CombatElement
    private ArrayList<TYPE> cardDeck;
    protected final int NUMBER_OF_CARDS = 10;
    
    public CardDeck(){
        cardDeck = new ArrayList<>();
    }
    
    protected abstract void addCards();
    
    protected void addCard(TYPE card){
        cardDeck.add(card);
    }
    
    public TYPE nextCard(){
        if(cardDeck.isEmpty()){
            addCards();
            Collections.shuffle(cardDeck);
        }
        TYPE output = cardDeck.get(0);
        cardDeck.remove(0);
        return output;
    }
    
}
