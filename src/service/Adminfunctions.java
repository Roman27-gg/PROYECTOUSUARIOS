package service;

import java.util.Scanner;

import model.User;

public class Adminfunctions extends Usersdata {
    private User user;

    public Adminfunctions(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void showActualInformation() {
        System.out.println(user.toString());
    }

    public void showInformation(int i) {
        System.out.println(users[i].toString());
    }

    public void showAllInformation() {
        for (int i = 0; i < 50; i++) {
            if (users[i] == null) {
                continue;
            }
            showInformation(i);
        }
    }

    public void setActualPasword(Scanner input) {
        user.setNewPasword(input);
    }

    public void setNewPaswordByIndex(Scanner input, int i) {
        users[i].setNewPasword(input);
    }

    public void setNewNamedByIndex(Scanner input, int i) {
        users[i].setNewName(input);
    }

    public void setActualName(Scanner input) {
        user.setNewName(input);
    }

    public void setNewUserNameByIndex(Scanner input, int i){
        users[i].setNewUserName(input);
    }

    public void setActualUserName(Scanner input){
        user.setNewUserName(input);
    }


    public void deleteUser(int i) {
        users[i] = null;
    }

    public void createUser(Scanner input) {
        addUser(new User(Createuser.validateName(input), Createuser.validateUserName(input),
                Createuser.validatePasword(input), Createuser.createId()));
    }

    public Integer searchBy(Scanner input, String message) {
        System.out.println(message);
        String validate = input.nextLine();
        System.out.println("Buscando.....");
        for (int i = 0; i < users.length; i++) {
            if (message.equals("Digite el nombre: ")) {
                if (users[i].getName().equals(validate)) {
                System.out.println("Persona encontrada ");
                System.out.println(users[i].toString());
                return i;
            }
            } else if(message.equals("Digite el nombre de usuario: ")){
                if (users[i].getUsername().equals(validate)) {
                System.out.println("Persona encontrada ");
                System.out.println(users[i].toString());
                return i;
            }
            } else if(message.equals("Digite la id: ")){
                if (users[i].getId().equals(validate)) {
                System.out.println("Persona encontrada ");
                System.out.println(users[i].toString());
                return i;
            }
            }
        }
        System.out.println("No se han encontrado resultados ");
        return null;
    }


}
