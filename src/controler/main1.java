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
                if (menu.login(input, user) == false) {
                    System.out.println("Hemos bloqueado su cuenta ");
                    continue;
                } else {
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
                if (menu.login(input, user) == false) {
                    System.out.println("Hemos bloqueado su cuenta cree una nueva");
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
            if (menu.isRepeat(user.getId(), user.getUsername())) {
                for (int i = 0; i < usersdata.length; i++) {
                    if (usersdata[i] == null) {
                        usersdata[i] = user;
                        j = i;
                        break;
                    }
                }
            }
            if (user.getType().equals(Type.ADMIN)) {
                admin = new Adminfunctions(user);
                admin.addArrayUsers(usersdata);
                isAdmin = true;
            }
            do {
                Integer opcion = menu.menu(input, user);
                switch (opcion) {
                    case 1:
                        if (menu.modifyPasword(input, user, admin)) {
                            if (isAdmin) {
                                admin.addActtionAdmin("Modifico su contraseña", LocalDateTime.now());
                            } else {
                                usersdata[j].addAcction("Modifico su contraseña", LocalDateTime.now());
                            }
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 2:
                        if (menu.modifyName(input, user, admin)) {
                            if (isAdmin) {
                                admin.addActtionAdmin("Modifico su nombre", LocalDateTime.now());
                            } else {
                                usersdata[j].addAcction("Modifico su nombre", LocalDateTime.now());
                            }
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 3:
                        if (menu.modifyUserName(input, user, admin)) {
                            if (isAdmin) {
                                admin.addActtionAdmin("Modifico su nombre de usuario", LocalDateTime.now());
                            } else {
                                usersdata[j].addAcction("Modifico su nombre de usuario", LocalDateTime.now());
                            }
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 4:
                        menu.information(user, null, admin);
                        if (isAdmin) {
                            admin.addActtionAdmin("Consulto su informacion", LocalDateTime.now());
                        } else {
                            usersdata[j].addAcction("Consulto su informacion", LocalDateTime.now());
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 5:
                        if (menu.showHistory(user, admin, input, false)) {
                            if (isAdmin) {
                                admin.addActtionAdmin("Consulto su log", LocalDateTime.now());
                            } else {
                                usersdata[j].addAcction("Consulto su log", LocalDateTime.now());
                            }
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 6:
                        if (menu.searchUser(admin, input) != null) {
                            admin.addActtionAdmin("Busco un usuario", LocalDateTime.now());
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 7:
                        if (menu.modifyInformations(admin, input)) {
                            admin.addActtionAdmin("Modifico la informacion de un usuario", LocalDateTime.now());
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 8:
                        if (menu.deleteUser(admin, input)) {
                            admin.addActtionAdmin("Borro un usuario", LocalDateTime.now());
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 9:
                        menu.createUser(admin, input);
                        admin.addActtionAdmin("Creo un nuevo usuario", LocalDateTime.now());
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 10:
                        menu.allInformation(admin, input);
                        admin.addActtionAdmin("Mostro la informacion de todos los usuarios", LocalDateTime.now());
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 11:
                        if (menu.showHistory(user, admin, input, true)) {
                            admin.addActtionAdmin("Busco el log de un usuario", LocalDateTime.now());
                        }
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