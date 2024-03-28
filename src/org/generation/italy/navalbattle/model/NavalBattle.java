package org.generation.italy.navalbattle.model;

import org.generation.italy.navalbattle.model.map.Map;
import org.generation.italy.navalbattle.model.people.Player;
import org.generation.italy.navalbattle.model.map.Ship;
import org.generation.italy.navalbattle.model.map.ShipDimension;

public class NavalBattle {
    public static void main(String[] args) {
     Map prova = new Map();
     Player p1 = new Player("pippo");
     Ship s1 = new Ship(ShipDimension.SUBMARINE);
     prova.printMap();
     //prova.insertShip(p1,s1);
     //prova.printMap();
    }
}