package model;

import java.time.LocalDateTime;
import java.util.Scanner;

import service.Createuser;


/**
 * Representa un usuario dentro del sistema.
 * Contiene información básica como nombre, usuario, contraseña e historial.
 */
public class User {
    private String name;
    private String id;
    private String username; 
    private String pasword;
    private Type type;
    private History stockhistory[];

    
    public User(String name, String username, String pasword, String id) {
        this(name, id, username, pasword, Type.STANDARD);
    }


    public User(String name, String id, String username, String pasword, Type type) {
        this.name = name;
        this.id = id;
        this.username = username;
        this.pasword = pasword;
        this.type = type;
        stockhistory= new History[200]; 
    }

    public String getName() {
        return name;
    }


    public String getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public String getPasword() {
        return pasword;
    }


    public Type getType() {
        return type;
    }

    public void setNewUserName(Scanner input){
        System.out.println("Digite el nuevo nombre de usuario ");
        username=(Createuser.validateUserName(input));
    }

    public void setNewPasword(Scanner input){
        System.out.println("Digite la nueva contraseña ");
        pasword=(Createuser.validatePasword(input));
    }

    public void setNewName(Scanner input){
        System.out.println("Digite el nuevo nombre ");
        name=(Createuser.validateName(input));
    }


    public void setType(Type type) {
        this.type = type;
    }

    public void addAcction(String acction, LocalDateTime date){
        for (int i = 0; i < stockhistory.length; i++) {
            if (stockhistory[i]==null) {
                stockhistory[i]= new History(acction, date);
                return;
            }
        }
    }

    public void showHistory(){
        for (int i = 0; i < stockhistory.length; i++) {
            if (stockhistory[i]==null){
                continue;
            }
            stockhistory[i].showHistory();
        }
    }

    @Override
    public String toString() {
        return String.format("""
        Nombre: %s
        Nombre de usuario: %s
        Id: %S
        Rol: %S
        """, getName(), getUsername(), getId(),(getType()==Type.ADMIN)?"Administrador":"Estandar");
    }
}