package controler;

import java.time.LocalDateTime;
import java.util.Scanner;

import model.Type;
import model.User;
import service.Adminfunctions;
import service.Menu;

public class main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        User usersdata[] = new User[50];
        Menu menu = new Menu();
        Integer j = 0;
        boolean x;
        boolean isAdmin = false;
        Adminfunctions admin = null;
        User user = null;
        do {
            if (isAdmin) {
                usersdata = null;
                usersdata = admin.getUsers();
                isAdmin = false;
            }
            if (menu.loginOrCreate(input) == 1) {
                Integer l=menu.login(input, usersdata);
                if (l == null) {
                    System.out.println("No se pudo iniciar sesion");
                    continue;
                } else {
                    user=usersdata[l];
                    if (user.getType().equals(Type.ADMIN)) {
                        admin = new Adminfunctions(user);
                        admin.addArrayUsers(usersdata);
                        isAdmin = true;
                        admin.addActtionAdmin("Inicio sesion", LocalDateTime.now());
                    } else {
                        user.addAcction("Inicio sesion", LocalDateTime.now());
                    }
                }
            } else {
                user = menu.createAccount(input);
                for (int i = 0; i < usersdata.length; i++) {
                        if (usersdata[i] == null) {
                            usersdata[i] = user;
                            j = i;
                            break;
                        }
                    }
                if (menu.login(input, usersdata) == null) {
                    System.out.println("Hemos bloqueado su cuenta cree una nueva");
                    usersdata[j]=null;
                    continue;
                } else {
                    if (user.getType().equals(Type.ADMIN)) {
                        admin = new Adminfunctions(user);
                        admin.addArrayUsers(usersdata);
                        isAdmin = true;
                        admin.addActtionAdmin("Creo su cuenta", LocalDateTime.now());
                    } else {
                        user.addAcction("Creo su cuenta", LocalDateTime.now());
                    }
                }
            }
            x = true;
            do {
                Integer opcion = menu.menu(input, user);
                switch (opcion) {
                    case 1:
                        menu.modifyPasword(input, user, admin);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 2:
                        menu.modifyName(input, user, admin);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 3:
                        menu.modifyUserName(input, user, admin);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 4:
                        menu.information(user, null, admin);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 5:
                        menu.showHistory(user, admin, input, false);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 6:
                        menu.searchUser(admin, input);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 7:
                        menu.modifyInformations(admin, input);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 8:
                        menu.deleteUser(admin, input);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 9:
                        menu.createUser(admin, input);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 10:
                        menu.allInformation(admin, input);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 11:
                        menu.showHistory(user, admin, input, true);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 12:
                        x = false;
                        System.out.println("Cerrando sesion.....");
                        if (isAdmin) {
                            admin.addActtionAdmin("Cerro sesion", LocalDateTime.now());
                        } else {
                            usersdata[j].addAcction("Cerro sesion", LocalDateTime.now());
                        }
                        break;
                    default:
                        input.close();
                        return;
                }
            } while (x == true);
        } while (true);
    }
}