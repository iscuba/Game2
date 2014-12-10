/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2;

import java.util.ArrayList;
import static Game2.Diner.testDiner;
import static Game2.Grid.testGrid;

/**
 *
 * @author Isabella
 */
public class Game2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testGrid();
        testDiner();
        ArrayList<Soul> souls = new ArrayList<Soul>();
        Soul soul1 = new Soul(7, 3, new Order(1, 2, 3, 4), false);
        Soul soul2 = new Soul(10, 5, new Order(4, 3, 1, 4), false);
        Soul soul3 = new Soul(13, 11, new Order(6, 2, 0, 1), false);
        Soul soul4 = new Soul(2, 4, new Order(1, 5, 2, 3), false);
        
        souls.add(soul1);
        souls.add(soul2);
        souls.add(soul3);
        souls.add(soul4);
        Diner game = new Diner(new Soul(1, 15), souls);
        game.bigBang(1080, 500, .1);
//        ArrayList<Ingredient> arr = new ArrayList();
//        Ingredient bun = new Ingredient(9,21,"white",true);
//        arr.add(bun);
//        Grid game = new Grid(arr, new Order(4,2,3,0));
//        game.bigBang(1080, 420, .1);
    }

}
