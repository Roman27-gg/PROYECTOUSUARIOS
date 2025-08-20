package service;

import java.util.Scanner;

import model.Type;
import model.User;

public class Modifyinformation {
    private Log log;

    public Modifyinformation(Log log){
        this.log=log;
    }

    /**
     * Verifica si la contraseña actual ingresada por el usuario coincide con la
     * almacenada.
     *
     * @param input objeto {@link Scanner} para leer la entrada
     * @param user  usuario al que se le valida la contraseña
     * @return {@code true} si la contraseña es correcta, {@code false} si falla
     */
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

    /**
     * Permite cambiar la contraseña de un usuario estándar o de un administrador.
     *
     * @param input objeto {@link Scanner}
     * @param user  usuario estándar
     * @param admin Adminfunctions (puede ser {@code null})
     * @see #actualPasword(Scanner, User)
     */
    public void modifyPasword(Scanner input, User user, Adminfunctions admin) {
        if (actualPasword(input, user) == false) {
            return;
        }
        if (user.getType().equals(Type.STANDARD)) {
            user.setNewPasword(input);
            log.addAcctionToLog(user, null, "Cambio su contraseña");
        } else if (admin != null) {
            admin.setActualPasword(input);
            log.addAcctionToLog(null, admin, "Cambio su contraseña");
        }
        System.out.println("Contraseña cambiada con exito");
    }

    /**
     * Permite modificar el nombre de un usuario o administrador.
     *
     * @param input objeto {@link Scanner}
     * @param user  usuario
     * @param admin Adminfunctions (puede ser {@code null})
     */
    public void modifyName(Scanner input, User user, Adminfunctions admin) {
        if (actualPasword(input, user) == false) {
            return;
        }
        if (user.getType().equals(Type.STANDARD)) {
            user.setNewName(input);
            log.addAcctionToLog(user, null, "Cambio su nombre");
        } else if (admin != null) {
            admin.setActualName(input);
            log.addAcctionToLog(null, admin, "Cambio su nombre");
        }
        System.out.println("Nombre cambiado con exito");
    }

    /**
     * Permite modificar el nombre de usuario.
     *
     * @param input objeto {@link Scanner}
     * @param user  usuario estándar
     * @param admin Adminfunctions (puede ser {@code null})
     * @param account Createaccount para validar si ya existe el nombre de usuario
     */
    public void modifyUserName(Scanner input, User user, Adminfunctions admin, Createaccount account) {
        if (actualPasword(input, user) == false) {
            return;
        }
        if (user.getType().equals(Type.STANDARD)) {
            String username= validateusername(account, input);
            user.setNewUserName(username);
            log.addAcctionToLog(user, null, "Cambio su nombre de usuario");
        } else if (admin != null) {
            String username=validateusername(account, input);
            admin.setActualUserName(username);
            log.addAcctionToLog(null, admin, "Cambio su nombre de usuario");
        }
        System.out.println("Nombre de usuario cambiado con exito");
    }

    /**
     * Muestra un submenú para elegir cómo buscar a un usuario (por nombre, username o ID).
     *
     * @param input objeto {@link Scanner}
     * @return número de opción seleccionada (1-3)
     */
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

    
    /**
     * Busca un usuario por nombre, nombre de usuario o ID.
     *
     * @param admin Adminfunctions
     * @param input objeto {@link Scanner}
     * @return índice del usuario encontrado o {@code null} si no existe
     */
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
        if (i != null) {
            log.addAcctionToLog(null, admin, "Busco un usuario");
        }
        return i;
    }

    /**
     * Permite a un administrador modificar la información de un usuario (nombre, usuario, contraseña, rol).
     *
     * @param admin Adminfunctions
     * @param input objeto {@link Scanner}
     * @see #setNewType(Scanner, Adminfunctions, Integer)
     */
    public Integer modifyInformations(Adminfunctions admin, Scanner input, Createaccount account) {
        if (admin == null) {
            System.out.println("No eres admin ");
            return null;
        }
        if (actualPasword(input, admin.getUser()) == false) {
            return null;
        }
        Integer i = searchUser(admin, input);
        if (i == null) {
            return null;
        }
        System.out.println("Digite que desea modificar del usuario: ");
        Integer opcion;
        do {
            System.out.println("""
                    1. Nombre
                    2. Nombre de usuario
                    3. Contraseña
                    4. Rol""");
            System.out.print("Digite su opcion: ");
            String validate = input.nextLine();
            if (validate.matches("[1-4]")) {
                opcion = Integer.parseInt(validate);
                break;
            } else {
                System.out.println("Opcion no valida (Numero del 1 a el 3)");
            }
        } while (true);
        if (opcion == 1) {
            admin.setNewNamedByIndex(input, i);
            log.addAcctionToLog(null, admin, "Cambio el nombre del usuario " + admin.getUserByIndex(i).getName());
            log.addAcctionToLog(admin.getUserByIndex(i), null, "Su nombre fue cambiado");
        } else if (opcion == 2) {
            String username=validateusername(account, input);
            admin.setNewUserNameByIndex(username, i);
            log.addAcctionToLog(null, admin, "Cambio el nombre de usuario de " + admin.getUserByIndex(i).getName());
            log.addAcctionToLog(admin.getUserByIndex(i), null, "Su nombre  de usuario fue cambiado");
        } else if (opcion == 3) {
            admin.setNewPaswordByIndex(input, i);
            log.addAcctionToLog(null, admin, "Cambio la contraseña del usuario " + admin.getUserByIndex(i).getName());
            log.addAcctionToLog(admin.getUserByIndex(i), null, "Su contraseña fue cambiada");
        } else {
            setNewType(input, admin, i);
        }
        return i;
    }

     /**
     * Valida un nombre de usuario asegurando que no exista previamente.
     *
     * @param account instancia de {@link Createaccount} que contiene los usuarios existentes.
     * @param input   objeto {@link Scanner} para leer la entrada del usuario.
     * @return el nombre de usuario único y validado.
     */
    public String validateusername(Createaccount account, Scanner input){
        String username;
            do {
                username= Createuser.validateUserName(input);
                if (account.checkUsername(username)) {
                    break;
                }
                System.out.println("Nombre de usuario ya existente");
            } while (true);
        return username;
    }

    /**
     * Cambia el rol de un usuario (estándar ↔ administrador).
     *
     * @param input objeto {@link Scanner}
     * @param admin Adminfunctions
     * @param i índice del usuario a modificar
     */
    public void setNewType(Scanner input, Adminfunctions admin, Integer i) {
        do {
            System.out.println("¿Desea cambiar el rol a administrador o estandar? ");
            System.out.println("""
                    1. Admin
                    2. Estandar
                    """);
            System.out.print("Digite su opcion: ");
            String validate = input.nextLine();
            if (validate.equals("1")) {
                if (admin.getUserByIndex(i).getType().equals(Type.ADMIN)) {
                    System.out.println("El usuario ya tiene actualmente el rol de admin");
                    return;
                } else {
                    System.out.println("Usuario ascendido a admin ");
                    admin.setTypeByIndex(i, "Admin");
                    log.addAcctionToLog(null, admin, "Cambio el rol a admin del usuario " + admin.getUserByIndex(i).getName());
                    log.addAcctionToLog(admin.getUserByIndex(i), null, "Su rol fue ascendido");
                    return;
                }
            } else if (validate.equals("2")) {
                if (admin.getUserByIndex(i).getType().equals(Type.STANDARD)) {
                    System.out.println("El usuario ya tiene actualmente el rol de estandar");
                    return;
                } else {
                    System.out.println("Usuario ahora tiene el rol de estandar ");
                    admin.setTypeByIndex(i, "Estandar");
                    log.addAcctionToLog(null, admin, "Cambio el rol a estandar del usuario " + admin.getUserByIndex(i).getName());
                    log.addAcctionToLog(admin.getUserByIndex(i), null, "Su rol fue cambiado a estandar");
                    return;
                }
            } else {
                System.out.println("Opcion no valida");
            }
        } while (true);
    }
}
