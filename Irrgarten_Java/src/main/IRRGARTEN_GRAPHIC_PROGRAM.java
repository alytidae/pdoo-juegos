/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import irrgarten.Game;
import irrgarten.UI.GraphicUI;
import irrgarten.controller.Controller;

public class IRRGARTEN_GRAPHIC_PROGRAM {
    public static void main(String[] args){
        int nPlayers;
        if(args.length == 0){
            nPlayers = 1;
        } else {
            nPlayers = Integer.parseInt(args[0]);
        }
        GraphicUI ui = new GraphicUI();
        Game game = new Game(nPlayers);
        Controller ctrlr = new Controller(game, ui);
        ctrlr.play();
    }
}
