package model;

import java.time.LocalDateTime;

/**
 * Repreta una accion de cualquier usuario en el sistema 
 * Se compone de la accion hecha y la hora en que se realizo
 */
public class History {
    private String acction;
    private LocalDateTime date;

    public History(String acction, LocalDateTime date) {
        this.acction = acction;
        this.date = date;
    }
    
    public void showHistory(){
        System.out.printf("Accion: %S Hora: %S %n",acction,date);
    }
}
