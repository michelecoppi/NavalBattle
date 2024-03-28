package org.generation.italy.navalbattle.model.map;
//4 navi da 1, 3 navi da 2, 2 navi da 3, 1 da 4
 public class Ship{
    private ShipDimension dimension;
    private boolean sunk;
    //TODO implementazione colpito
    
     public Ship(ShipDimension dimension){
        this.dimension = dimension;
        this.sunk=false;
    }

    public int getDim(){
        return dimension.getDimension();
    }
}