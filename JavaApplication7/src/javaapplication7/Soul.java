/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication7;

import javalib.colors.Blue;
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
    
    public Soul(int x, int y, Order wish){
        this.x = x;
        this.y = y;
        this.order = wish;
    }
    
    //used to make the User character 
    public Soul(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public DiskImage drawSoul(){
        return new DiskImage(new Posn(this.x, this.y), 20, new Blue());
    }
}
