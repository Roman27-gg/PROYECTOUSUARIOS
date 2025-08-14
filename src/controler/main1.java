package controler;

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
            if (menu.loginOrCreate(input)==1) {
                if (menu.login(input, user) == false) {
                System.out.println("Hemos bloqueado su cuenta cree una nueva");
                continue;
                }
            } else {
                user=menu.createAccount(input);
                if (menu.login(input, user) == false) {
                System.out.println("Hemos bloqueado su cuenta cree una nueva");
                continue;
                }
            }
            x = true;
            boolean isRepeat=false;
            for (int i = 0; i < usersdata.length; i++) {
                if (usersdata[i]==user) {
                    isRepeat=true;
                }
            }
            if (!isRepeat) {
                for (int i = 0; i < usersdata.length; i++) {
                    if (usersdata[i]==null) {
                        usersdata[i]=user;
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
                        menu.modifyPasword(input, user, null, admin);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 2:
                        menu.modifyName(input, user, null, admin);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 3:
                        menu.modifyUserName(input, user, null, admin);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 4:
                        menu.information(user, null, admin);
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 5:
                        if (isAdmin) {
                            menu.searchUser(admin, input);
                        } else {
                            System.out.println("No eres admin");
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 6:
                        if (isAdmin) {
                            menu.modifyInformations(admin, input);
                        } else {
                            System.out.println("No eres admin");
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 7:
                        if (isAdmin) {
                            menu.deleteUser(admin, input);
                        } else {
                            System.out.println("No eres admin");
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 8:
                        if (isAdmin) {
                            menu.createUser(admin, input);
                        } else {
                            System.out.println("No eres admin");
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 9:
                        if (isAdmin) {
                            menu.allInformation(admin, input);
                        } else {
                            System.out.println("No eres admin");
                        }
                        System.out.print("Para volver a el menu presione enter ");
                        input.nextLine();
                        break;
                    case 10:
                        x = false;
                        break;
                    default:
                        input.close();
                        return;
                }
            } while (x == true);
        } while (true);
    }

}