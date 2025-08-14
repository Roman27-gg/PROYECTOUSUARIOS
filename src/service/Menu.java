package service;

import java.util.Scanner;

import model.Type;
import model.User;

public class Menu {
    private String ids[];
    private String usernames[];

    public Menu() {
        ids = new String[50];
        usernames = new String[50];
    }

    public void removeOneData(Integer i) {
        ids[i] = null;
        usernames[i] = null;
    }

    public void addOneData(String id, String username) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == null && usernames[i] == null) {
                ids[i] = id;
                usernames[i] = username;
            }
        }
    }

    public Boolean checkUsername(String username) {
        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i] == null) {
                return true;
            }
            if (usernames[i].equals(username)) {
                return false;
            }
        }
        return true;
    }

    public Boolean checkId(String id) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == null) {
                return true;
            }
            if (ids[i].equals(id)) {
                return false;
            }
        }
        return true;
    }

    public User createAccount(Scanner input) {
        System.out.println("=====CREAR CUENTA=====");
        String name = Createuser.validateName(input);
        String username;
        do {
            username = Createuser.validateUserName(input);
            if (checkUsername(username)) {
                break;
            } else {
                System.out.println("Nombre de usuario ya existente");
            }
        } while (true);
        String pasword = Createuser.validatePasword(input);
        String id;
        do {
            id = Createuser.createId();
            if (checkId(id)) {
                break;
            }
        } while (true);
        // Codigo inventado 45678
        System.out.print("Para obtener el rol de administrador digite el codigo de seguridad: ");
        String role = input.nextLine();
        if (role.equals("45678")) {
            System.out.println("Codigo aceptado");
            addOneData(id, username);
            return new User(name, id, username, pasword, Type.ADMIN);
        }
        System.out.println("Codigo rechazado");
        addOneData(id, username);
        return new User(name, username, pasword, id);
    }

    public Boolean login(Scanner input, User user) {
        System.out.println("=====INICIO DE SESION=====");
        for (int i = 3; i > 0; i--) {
            System.out.println("Le quedan " + (i) + " intentos");
            System.out.print("Digite su nombre de usuario: ");
            String username = input.nextLine();
            System.out.print("Digite su contraseña: ");
            String pasword = input.nextLine();
            if (user.getUsername().equals(username) && user.getPasword().equals(pasword)) {
                System.out.println("Inicio de sesion exitoso");
                addOneData(user.getId(), user.getUsername());
                return true;
            } else {
                System.out.println("Contraseña o nombre de usuario incorrecto ");
            }
        }
        return false;
    }

    public Integer menu(Scanner input, User user) {
        System.out.println("=====MENU=====");
        if (user.getType() == Type.STANDARD) {
            do {
                System.out.println("""
                        1. Modificar contraseña
                        2. Modificar nombre
                        3. Modificar nombre de usuario
                        4. Ver informacion
                        10. Cerrar sesion
                        0. Salir del programa""");
                System.out.print("Digite su opcion: ");
                String validate = input.nextLine();
                if (validate.matches("[0-4]|10")) {
                    return Integer.parseInt(validate);
                } else {
                    System.out.println("Opcion no valida ");
                }
            } while (true);
        } else {
            do {
                System.out.println("""
                        1. Modificar contraseña propia
                        2. Modificar nombre propio
                        3. Modificar nombre de usuario propio
                        4. Ver mi informacion actual
                        5. Buscar un usuario
                        6. Modificar la informacion de un usuario especifico
                        7. Borrar un usuario
                        8. Añadir un usario
                        9. Ver la informacion de todos los usuarios
                        10. Cerrar sesion
                        0. Salir del programa""");
                System.out.print("Digite su opcion: ");
                String validate = input.nextLine();
                if (validate.matches("[0-9]|10")) {
                    return Integer.parseInt(validate);
                } else {
                    System.out.println("Opcion no valida");
                }
            } while (true);
        }

    }

    public Boolean actualPasword(Scanner input, User user) {
        do {
            for (int i = 3; i > 0; i--) {
                System.out.println("Le quedan " + (i) + " intentos");
                System.out.print("Digite la contraseña actual: ");
                String pasword = input.nextLine();
                if (user.getPasword().equals(pasword)) {
                    return true;
                }
                System.out.println("Contraseña incorrecta ");
            }
            System.out.println("No se pudo completar la validacion de contraseña");
            return false;

        } while (true);
    }

    public void modifyPasword(Scanner input, User user, Integer i, Adminfunctions admin) {
        if (actualPasword(input, user) == false) {
            return;
        }
        if (user.getType().equals(Type.STANDARD)) {
            user.setNewPasword(input);
        } else if (i != null) {
            admin.setNewPaswordByIndex(input, i);
        } else if (admin != null) {
            admin.setActualPasword(input);
        }
        System.out.println("Contraseña cambiada con exito");
    }

    public void modifyName(Scanner input, User user, Integer i, Adminfunctions admin) {
        if (actualPasword(input, user) == false) {
            return;
        }
        if (user.getType().equals(Type.STANDARD)) {
            user.setNewName(input);
        } else if (i != null) {
            admin.setNewNamedByIndex(input, i);
        } else if (admin != null) {
            admin.setActualName(input);
        }
        System.out.println("Nombre cambiado con exito");
    }

    public void modifyUserName(Scanner input, User user, Integer i, Adminfunctions admin) {
        if (actualPasword(input, user) == false) {
            return;
        }
        if (user.getType().equals(Type.STANDARD)) {
            user.setNewUserName(input);
        } else if (i != null) {
            admin.setNewUserNameByIndex(input, i);
        } else if (admin != null) {
            admin.setActualUserName(input);
        }
        System.out.println("Nombre de usuario cambiado con exito");
    }

    public void information(User user, Integer i, Adminfunctions admin) {
        System.out.println();
        if (user.getType().equals(Type.STANDARD)) {
            System.out.println(user.toString());
        } else if (i != null) {
            admin.showInformation(i);
        } else if (admin != null) {
            admin.showActualInformation();
        }
    }

    public Integer menuOpcion(Scanner input) {
        do {
            System.out.println("""
                    1. Nombre
                    2. Nombre de usuario
                    3. Id""");
            System.out.print("Digite su opcion: ");
            String validate = input.nextLine();
            if (validate.matches("[1-3]")) {
                return Integer.parseInt(validate);
            } else {
                System.out.println("Opcion no valida (Numero del 1 a el 3)");
            }
        } while (true);
    }

    public Integer searchUser(Adminfunctions admin, Scanner input) {
        if (admin == null) {
            System.out.println("No eres admin ");
            return null;
        }
        System.out.println("Digite como desea buscar el usuario ");
        String message;
        Integer opcion = menuOpcion(input);
        message = (opcion == 1) ? "Digite el nombre: "
                : (opcion == 2) ? "Digite el nombre de usuario: " : "Digite la id: ";
        Integer i = admin.searchBy(input, message);
        if (i == null) {
            return null;
        }
        return i;
    }

    public void modifyInformations(Adminfunctions admin, Scanner input) {
        if (admin == null) {
            System.out.println("No eres admin ");
            return;
        }
        if (actualPasword(input, admin.getUser()) == false) {
            return;
        }
        Integer i = searchUser(admin, input);
        if (i == null) {
            return;
        }
        System.out.println("Digite que desea modificar del usuario: ");
        Integer opcion;
        do {
            System.out.println("""
                    1. Nombre
                    2. Nombre de usuario
                    3. Contraseña""");
            System.out.print("Digite su opcion: ");
            String validate = input.nextLine();
            if (validate.matches("[1-3]")) {
                opcion = Integer.parseInt(validate);
                break;
            } else {
                System.out.println("Opcion no valida (Numero del 1 a el 3)");
            }
        } while (true);
        if (opcion == 1) {
            admin.setNewNamedByIndex(input, i);
        } else if (opcion == 2) {
            admin.setNewUserNameByIndex(input, i);
        } else {
            admin.setNewPaswordByIndex(input, i);
        }
    }

    public void deleteUser(Adminfunctions admin, Scanner input) {
        if (admin == null) {
            System.out.println("No eres admin ");
            return;
        }
        if (actualPasword(input, admin.getUser())) {
            return;
        }
        Integer i = searchUser(admin, input);
        if (i == null) {
            return;
        }
        do {
            System.out.print("¿Esta seguro que desea borrar los datos del usuario?: ");
            String validate = input.nextLine();
            if (validate.equalsIgnoreCase("no")) {
                System.out.println("No se han borrado los datos");
                return;
            } else if (validate.equalsIgnoreCase("si")) {
                break;
            } else {
                System.out.println("Opcion no valida solo si o no");
            }
        } while (true);
        removeOneData(i);
        admin.deleteUser(i);
        System.out.println("Datos borrados con exito ");
    }

    public void createUser(Adminfunctions admin, Scanner input) {
        if (admin == null) {
            System.out.println("No eres admin ");
            return;
        }
        admin.addUser(createAccount(input));
    }

    public void allInformation(Adminfunctions admin, Scanner input) {
        if (admin == null) {
            System.out.println("No eres admin ");
            return;
        }
        admin.showAllInformation();
    }

    public Integer loginOrCreate(Scanner input){
        do {
            System.out.println("Que desea hacer: ");
            System.out.println("""
                1. Iniciar sesion
                2. Crear cuenta
                    """);
            System.out.print("Digite su opcion: ");
            String validate= input.nextLine();
            if(validate.matches("1|2")){
                if (validate.matches("1")) {
                    for (int i = 0; i < ids.length; i++) {
                        if (ids[i]!=null) {
                            return Integer.parseInt(validate);
                        }
                    }
                    System.out.println("No existen datos actuales para iniciar sesion con ninguna cuenta ");
                } else {
                    return Integer.parseInt(validate);
                }
            } else {
                System.out.println("Opcion no valida");
            }
        } while (true);
    }
}