package model;

import java.time.LocalDateTime;

public class History {
    private String acction;
    private LocalDateTime date;

    public History(String acction, LocalDateTime date) {
        this.acction = acction;
        this.date = date;
    }
    
    public void showHistory(){
        System.out.printf("Hora: %S Accion: %S  ",acction,date);
    }
}
