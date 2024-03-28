package org.generation.italy.navalbattle.model.people;

import org.generation.italy.navalbattle.model.map.Map;

public class Player {
    private String name;
    private Map field;
    
    public Player (String name){
        this.name = name;
        this.field = new Map();
    }

    public String getName(){
        return name;
    }
}