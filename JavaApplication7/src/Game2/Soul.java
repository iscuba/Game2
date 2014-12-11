/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2;

import javalib.colors.*;
import javalib.worldimages.DiskImage;
import javalib.worldimages.Posn;

/**
 *
 * @author Isabella
 */
public class Soul {

    public int x;
    public int y;
    public Order order;
    public boolean saved;

    public Soul(int x, int y, Order wish, boolean saved) {
        this.x = x;
        this.y = y;
        this.order = wish;
        this.saved = saved;
    }

    //used to make the User character 
    public Soul(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public DiskImage drawSoul() {
        if (saved){
            return new DiskImage(new Posn(this.x * 20, this.y * 20), 10, new Green());
        }
        return new DiskImage(new Posn(this.x * 20, this.y * 20), 10, new Red());
    }

    public DiskImage drawPlayer() {
        return new DiskImage(new Posn(this.x * 20, this.y * 20), 10, new Blue());
    }
}
