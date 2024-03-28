package org.generation.italy.navalbattle.model.map;

import org.generation.italy.navalbattle.model.people.Player;
import java.util.Scanner;

public class Map {
    private final static int MAX_SIZE = 10;

    private int[][] field;

    public Map() {
        this.field = new int[MAX_SIZE][MAX_SIZE];
    }

    public void insertShip(Player pl, Ship sp) {
        int shipNum = -1;
        switch (sp.getDim()) {
            case 1:
                shipNum = 4;
                break;
            case 2:
                shipNum = 3;
                break;
            case 3:
                shipNum = 2;
                break;
            case 4:
                shipNum = 1;
                break;

        }
        
        int row;
        int col;

        for (int i = 0; i < shipNum; i++) {
            System.out.println(pl.getName() + " inserisci nave da " + sp.getDim() + " numero " + (i + 1));
            for (int j = 0; j < sp.getDim(); j++) {
                
                System.out.println("Inserisci valore riga da 1 a 10");
                row = checkOutOfBounds();
                System.out.println("Inserisci valore colonna da 1 a 10");
                col = checkOutOfBounds();
                field[row][col] = 1;
            }
        }
    }

    // controlla i limiti della mappa
    //TODO controllare di non inserire su caselle già inserite
    //TODO controllare per le navi >= 2 se le coordinate sono adiacenti
    //TODO controllare che le navi siano distanti almeno 1
    public int checkOutOfBounds() {
        Scanner sc2 = new Scanner(System.in);
        int coord = sc2.nextInt();
        boolean check = coord > 1 && coord < 10;
        while(!check){
            System.out.println("Attenzione il valore inserito è fuori dal campo di gioco... Reinserisci il valore");
            coord = sc2.nextInt();
            check = coord > 1 && coord < 10;
        }
        return coord;
    }

    public void printMap(){

        for(int i = 0; i < MAX_SIZE+1; i++){
            if(i!=0){
                System.out.printf("\t %d",i);
            }
        }
        System.out.println("\n");
        for(int i = 0; i < field.length; i++){
            System.out.printf("%d",i+1);
            for(int j = 0; j < field.length; j++ ){
                System.out.printf("\t %d",field[i][j]);
            }
            System.out.println();
        }
        //  1 2 3 4 5 6 7 8 9 10
        // 1
        // 2
        // 3
        // 4
        // 5
        // 6
        // 7
        // 8
        // 9
        // 10
    }


}