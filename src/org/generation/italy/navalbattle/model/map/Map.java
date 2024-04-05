package org.generation.italy.navalbattle.model.map;

import org.generation.italy.navalbattle.model.people.Player;

import java.util.ArrayList;
import java.util.Collections;
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

        int row = -1;
        int col = -1;
        ArrayList<Integer> rowCol;

        //ci servono per tenere traccia delle coordinate inserite in precedenza
        ArrayList<Integer> rowList = new ArrayList<>(4);
        ArrayList<Integer> colList = new ArrayList<>(4);

        for (int i = 0; i < shipNum; i++) {
            System.out.println(pl.getName() + " inserisci nave da " + sp.getDim() + " numero " + (i + 1));
            for (int j = 0; j < sp.getDim(); j++) {


                if (j <= 1) {
                    //qua abbiamo row e col DEVE SEMPRE RIMANERE DI DIM 2
                    rowCol = checkSamePosition(row, col, 0);


                    row = rowCol.remove(0);
                    col = rowCol.remove(0);

                    rowList.add(row);
                    colList.add(col);
                }




                //casella 1 senza controlli
                //if (rowList.size() == 1 && colList.size() == 1) {
                if (j == 0) {
                    field[row][col] = 1;
                    System.out.printf("Hai inserito una casella in posizione (%d,%d)%n%n", row + 1, col + 1);
                }
                //} else if (rowList.size() == 2 && colList.size() == 2) {
                else if (j == 1) {
                    //possiamo andare su/giu/sx/dx
                    int oldRowIndex = rowList.size() - 2;
                    int oldColIndex = colList.size() - 2;

                    //al primo giro di sicuro le calcola
                    boolean rowOnePos = (row == rowList.get(oldRowIndex) - 1) || (row == rowList.get(oldRowIndex) + 1);
                    boolean colOnePos = (col == colList.get(oldColIndex) - 1) || (col == colList.get(oldColIndex) + 1);
                    boolean sameRow = row == rowList.get(oldRowIndex);
                    boolean sameCol = col == colList.get(oldColIndex);

                    //controlla che la posizione corrente sia adiacente alla precedente
                    boolean checkAlignment = (rowOnePos && sameCol) || (colOnePos && sameRow);


                    //ricalcolo ogni volta che inserisco una posizione non allineata
                    while (!checkAlignment) {
                        System.out.println("Devi inserire la casella in una posizione adiacente alla precedente!! Ritenta...");
                        rowList.remove(rowList.size() - 1);
                        colList.remove(colList.size() - 1);

                        rowCol = checkSamePosition(row, col, 0);

                        row = rowCol.remove(0);
                        col = rowCol.remove(0);

                        rowList.add(row);
                        colList.add(col);

                        //aggiornamento condizioni
                        rowOnePos = (row == rowList.get(oldRowIndex) - 1) || (row == rowList.get(oldRowIndex) + 1);
                        colOnePos = (col == colList.get(oldColIndex) - 1) || (col == colList.get(oldColIndex) + 1);
                        sameRow = row == rowList.get(oldRowIndex);
                        sameCol = col == colList.get(oldColIndex);

                        checkAlignment = (rowOnePos && sameCol) || (colOnePos && sameRow);
                    }

                    //caso buono
                    field[row][col] = 1;
                    System.out.printf("Hai inserito una casella in posizione (%d,%d)%n%n", row + 1, col + 1);





                //questo else è per le navi con dim >= 3
                } else {

                    boolean sameRow = false;
                    boolean sameCol = false;

                    if (rowList.get(0).equals(rowList.get(1))) {
                        sameRow = true;
                    } else if (colList.get(0).equals(colList.get(1))) {
                        sameCol = true;
                    }

                    ArrayList<Integer> orderedCoord;

                    if (sameRow) {
                        System.out.println("La prossima casella deve essere inserita in riga " + (row + 1));
                        rowList.add(row);

                        rowCol = checkSamePosition(row, col, -1);

                        col = rowCol.remove(1);

                        //qua il clear() è obbligatorio
                        rowCol.clear();

                        //vogliamo avere una copia dell'arraylist perché in colList ci serve sapere l'ordine di inserimento dei valori
                        //dato che vogliamo ordinare i valori, non colpiamo colList

                        orderedCoord = new ArrayList<>(colList);
                        //valori ordinati in modo crescente
                        Collections.sort(orderedCoord);

                        colList.add(col);

                        boolean colWest = col == orderedCoord.get(0) - 1;
                        boolean colEast = col == orderedCoord.get(orderedCoord.size() - 1) + 1;

                        boolean colOnePos = colWest || colEast;

                        while (!colOnePos) {
                            System.out.println("Devi inserire la casella in una posizione adiacente alla sinistra o alla destra della nave!! Ritenta l'inserimento della colonna...");
                            colList.remove(colList.size() - 1);

                            rowCol = checkSamePosition(row, col, -1);
                            col = rowCol.remove(1);

                            //qua il clear() è obbligatorio
                            rowCol.clear();

                            colList.add(col);

                            //aggiornamento condizioni
                            colWest = col == orderedCoord.get(0) - 1;
                            colEast = col == orderedCoord.get(orderedCoord.size() - 1) + 1;
                            colOnePos = colWest || colEast;

                            //aggiornamento orederCoord
                            orderedCoord.clear();
                        }

                    } else if (sameCol) {
                        System.out.println("La prossima casella deve essere inserita in colonna " + (col + 1));

                        //vogliamo avere una copia dell'arraylist perché in colList ci serve sapere l'ordine di inserimento dei valori
                        //dato che vogliamo ordinare i valori, non colpiamo colList

                        orderedCoord = new ArrayList<>(rowList);
                        //valori ordinati in modo crescente
                        Collections.sort(orderedCoord);

                        boolean rowSouth = row == orderedCoord.get(0) - 1;
                        boolean rowNorth = row == orderedCoord.get(orderedCoord.size() - 1) + 1;

                        boolean rowOnePos = rowSouth || rowNorth;

                        while (!rowOnePos) {
                            System.out.println("Devi inserire la casella in una posizione adiacente alla cima o al fondo della nave!! Ritenta l'inserimento della riga...");
                            rowList.remove(rowList.size() - 1);

                            rowCol = checkSamePosition(row, col, 1);
                            row = rowCol.remove(0);

                            //qua il clear() è obbligatorio
                            rowCol.clear();

                            rowList.add(row);

                            //aggiornamento orederCoord
                            orderedCoord.clear();
                            orderedCoord.addAll(rowList);
                            Collections.sort(orderedCoord);

                            //aggiornamento condizioni
                            rowSouth = row == orderedCoord.get(0) - 1;
                            rowNorth = row == orderedCoord.get(orderedCoord.size() - 1) + 1;
                            rowOnePos = rowSouth || rowNorth;
                        }
                    }

                    //caso buono
                    field[row][col] = 1;
                    System.out.printf("Hai inserito una casella in posizione (%d,%d)%n%n", row + 1, col + 1);












































                }




                //TODO da decidere se lasciarla o no(utile per debug)
                printMap();
            }

            //vanno ripulite dopo un inserimento di una nave
            rowList.clear();
            colList.clear();



        }
    }



    //TODO controllare che le navi siano distanti almeno 1


    //row stà in posizione 0 e col in posizione 1
    //checkOnePos == 0 caso generale, checkOnePos == -1 quando abbiamo la riga bloccata, checkOnePos == 1 quando abbiamo la colonna bloccata
    private ArrayList<Integer> checkSamePosition(int row, int col, int checkOnePos) {
        ArrayList<Integer> rowCol = new ArrayList<>(2);
        do {
            switch(checkOnePos) {
                case 0:
                    System.out.println("Inserisci valore riga da 1 a 10");
                    row = checkOutOfBounds() - 1;
                    System.out.println("Inserisci valore colonna da 1 a 10");
                    col = checkOutOfBounds() - 1;
                    break;
                case -1:
                    System.out.println("Inserisci valore colonna da 1 a 10");
                    col = checkOutOfBounds() - 1;
                    break;
                case 1:
                    System.out.println("Inserisci valore riga da 1 a 10");
                    row = checkOutOfBounds() - 1;
                    break;
            }

            if(field[row][col] != 0) {
                System.out.println("Hai già inserito questa posizione!! Ritenta...");
            }
        } while(field[row][col] != 0);
        rowCol.add(row);
        rowCol.add(col);
        return rowCol;
    }

    //Controlla i limiti della mappa
    private int checkOutOfBounds() {
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
        System.out.println();
    }


}