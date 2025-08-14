package controler;

import java.util.Scanner;

import model.Type;
import model.User;
import service.Adminfunctions;
import service.Menu;
import service.Usersdata;

public class main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        Usersdata data = new Usersdata();
        Menu menu = new Menu();
        Adminfunctions admin = null;
        boolean x;
        do {
            x=true;
            User user = menu.createAccount(input);
            Boolean y=menu.login(input, user);
            if (y==false) {
                System.out.println("Hemos bloqueado su cuenta cree una nueva");
                continue;
            }
            data.addUser(user);
            if (user.getType().equals(Type.ADMIN)) {
                admin = new Adminfunctions(user);
            } 
            do {
                Integer opcion=menu.menu(input, user);
                switch (opcion) {
                case 1:
                    menu.modifyPasword(input, user, null, null);
                    break;
                case 2:
                    menu.modifyName(input, user, null, null);
                    break;
                case 3:
                    menu.modifyUserName(input, user, null, null);
                    break;
                case 4:
                    menu.information(user, null, null);
                    break;
                case 5:
                    menu.searchUser(admin, input);
                    break;
                case 6:
                    menu.modifyInformations(admin, input);
                    break;
                case 7:
                    menu.deleteUser(admin, input);
                    break;
                case 8:
                    menu.createAccount(input);
                    break;
                case 9:
                    menu.allInformation(admin, input);
                    break;
                case 10:
                    x=false;
                    break;
                default:
                    return;
            }
            } while (x==true);
        } while (true);
    }
    
}