package model;

import java.util.Scanner;

import service.Createuser;

public class User {
    private String name;
    private String id;
    private String username; 
    private String pasword;
    private Type type;

    
    public User(String name, String username, String pasword, String id) {
        this(name, id, username, pasword, Type.STANDARD);
    }


    public User(String name, String id, String username, String pasword, Type type) {
        this.name = name;
        this.id = id;
        this.username = username;
        this.pasword = pasword;
        this.type = type;
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
        System.out.print("Digite el nuevo nombre de usuario ");
        username=(Createuser.validateUserName(input));
    }

    public void setNewPasword(Scanner input){
        System.out.print("Digite la nueva contraseña ");
        pasword=(Createuser.validatePasword(input));
    }

    public void setNewName(Scanner input){
        System.out.println("Digite el nuevo nombre ");
        name=(Createuser.validateName(input));
    }


    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("""
        Nombre: %S
        Nombre de usuario: %S
        Id: %S
        Rol: %S
        """, getName(), getUsername(), getId(),(getType()==Type.ADMIN)?"Administrador":"Estandar");
    }
}