/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2;

import java.util.ArrayList;
import static Game2.Diner.testDiner;
import static Game2.Grid.testGrid;
import static Game2.Ingredient.testIngredient;
import static Game2.Order.testOrder;

/**
 *
 * @author Isabella
 */
public class Game2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testOrder();
        testIngredient();
        testGrid();
        testDiner();
        
        ArrayList<Soul> souls = new ArrayList<>();        
        //REMEMBER TO UNCOMMENT THIS AND DELETE THE TESTING SOULS
        Soul soul1 = new Soul(7, 3, new Order(1, 2, 3, 4), false);
        Soul soul2 = new Soul(10, 5, new Order(4, 3, 1, 2), false);
        Soul soul3 = new Soul(13, 11, new Order(3, 2, 0, 1), false);
        Soul soul4 = new Soul(2, 4, new Order(2, 4, 2, 3), false);
        
//        Soul soul1 = new Soul(7, 3, new Order(1, 1, 1, 1), false);
//        Soul soul2 = new Soul(10, 5, new Order(1, 1, 1, 1), false);
//        Soul soul3 = new Soul(13, 11, new Order(1, 1, 1, 1), false);
//        Soul soul4 = new Soul(2, 4, new Order(1, 1, 1, 1), false);
//        
//        
        souls.add(soul1);
        souls.add(soul2);
        souls.add(soul3);
        souls.add(soul4);
        Diner game = new Diner(new Soul(1, 15), souls);
        game.bigBang(1080, 450, .15);
    }

}
