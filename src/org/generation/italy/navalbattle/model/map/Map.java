package org.generation.italy.navalbattle.model.map;

import org.generation.italy.navalbattle.model.people.Player;
import java.util.Scanner;

public class Map {
    private final static int MAX_SIZE = 10;
    private final static int MIN_SIZE = 1;

    private final int[][] field;

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
                row = checkOutOfBounds() - 1;
                System.out.println("Inserisci valore colonna da 1 a 10");
                col = checkOutOfBounds() - 1;


                //FIXME qua dobbiamo verificare che la posizione inserita non sia già stata messa
                //potremmo usare un do/while
                /*
                do {
                    System.out.println("Inserisci valore riga da 1 a 10");
                    row = checkOutOfBounds() - 1;
                    System.out.println("Inserisci valore colonna da 1 a 10");
                    col = checkOutOfBounds() - 1;

                    if(field[row][col] != 0) {
                        System.out.println("Hai già inserito questa posizione!! Ritenta...);
                    }
                } while(field[row][col] != 0);
                 */



                //Aggiunta stampa(non so perché ma il primo %n non lo prende)
                System.out.printf("Hai inserito una casella in posizione (%d,%d)%n%n", row + 1, col + 1);

                field[row][col] = 1;
                //TODO da decidere se lasciarla o no(utile per debug)
                printMap();
            }
        }
    }


    //TODO controllare di non inserire su caselle già inserite
    //TODO controllare per le navi >= 2 se le coordinate sono adiacenti
    //TODO controllare che le navi siano distanti almeno 1
    //controlla i limiti della mappa
    public int checkOutOfBounds() {
        Scanner sc2 = new Scanner(System.in);
        int coord = sc2.nextInt();
        boolean check = coord >= MIN_SIZE && coord <= MAX_SIZE;

        while(!check){
            System.out.println("Attenzione il valore inserito è fuori dal campo di gioco... Reinserisci il valore");
            coord = sc2.nextInt();
            check = coord >= MIN_SIZE && coord <= MAX_SIZE;

        }
        return coord;
    }

    public void printMap(){
        //stampa le colonne della mappa (numeri da 1 a 10)
        for(int i = 0; i < MAX_SIZE; i++){
            System.out.printf("\t %d", i + 1);
        }

        System.out.println("\n");
        //stampiamo i valori della mappa (tutti 0 nell' inizializzazione)
        for(int i = 0; i < field.length; i++) {
            System.out.printf("%d", i + 1);
            for(int j = 0; j < field.length; j++) {
                System.out.printf("\t %d", field[i][j]);
            }
            System.out.println();
        }
    }


}