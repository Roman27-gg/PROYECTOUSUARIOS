package service;

import java.util.Scanner;

import model.Type;
import model.User;

public class Createaccount {
    private String ids[];
    private String usernames[];

    public Createaccount() {
        ids = new String[50];
        usernames = new String[50];
    }

     /**
     * Cambia el nombre de usuario en la posición indicada del arreglo de usernames.
     *
     * @param i índice en el arreglo donde se quiere modificar el nombre de usuario.
     * @param username nuevo nombre de usuario que se asignará en la posición indicada.
     */
    public void changeUserNameData(Integer i, String username){
        usernames[i]=username;
    }

    /**
     * Elimina un registro de usuario en la posición indicada.
     * 
     * @param i índice del usuario a eliminar dentro de los arreglos.
     */
    public void removeOneData(Integer i) {
        ids[i] = null;
        usernames[i] = null;
    }


     /**
     * Verifica si la posición indicada en los arreglos de usuarios está vacía.
     * <p>
     * Una posición se considera vacía cuando tanto el {@code id} como el
     * {@code username} en ese índice son {@code null}.
     * </p>
     *
     * @param i índice que se desea verificar en los arreglos.
     * @return {@code false} si la posición está vacía (id y username son {@code null}),
     *         {@code true} en caso contrario.
     */
    public Boolean checkIsEmpity(Integer i){
        if (ids[i]==null && usernames[i]==null) {
            return false;
        }
        return true;
    }

    /**
     * Verifica si un par de ID y nombre de usuario ya existe en los arreglos.
     *
     * @param id identificador del usuario a verificar
     * @param username nombre de usuario a verificar
     * @return {@code true} si no se repite, {@code false} si ya existe
     */
    public Boolean isRepeat(String id, String username) {
        for (int i = 0; i < ids.length; i++) {
            if (!checkIsEmpity(i)) {
                continue;
            }
            if (ids[i].equalsIgnoreCase(id) && usernames[i].equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Agrega un nuevo registro de usuario (ID y nombre de usuario) en la primera posición disponible.
     *
     * @param id identificador del usuario
     * @param username nombre de usuario
     * @see #isRepeat(String, String)
     */
    public void addOneData(String id, String username) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == null && usernames[i] == null && isRepeat(id, username)) {
                ids[i] = id;
                usernames[i] = username;
            }
        }
    }

     /**
     * Verifica si un nombre de usuario está disponible o ya está en uso.
     *
     * @param username nombre de usuario a verificar
     * @return {@code true} si está disponible, {@code false} si ya existe
     */
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


    /**
     * Verifica si un ID de usuario está disponible o ya está en uso.
     *
     * @param id identificador del usuario a verificar
     * @return {@code true} si está disponible, {@code false} si ya existe
     */
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


    /**
     * Crea una nueva cuenta de usuario pidiendo la información por consola.
     * Se valida nombre, nombre de usuario, contraseña e ID.
     * Si se ingresa el código de seguridad correcto, el usuario se crea con rol de administrador.
     *
     * @param input objeto {@link Scanner} para leer la entrada del usuario
     * @return un nuevo objeto {@link User} creado con la información ingresada
     * @see Createuser
     * @see User
     */
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

     /**
     * Permite a un usuario iniciar sesión con máximo 3 intentos.
     *
     * @param input objeto {@link Scanner} para leer la entrada
     * @param users arreglo de usuarios registrados
     * @return índice del usuario logueado o {@code null} si falla
     */
    public Integer login(Scanner input, User users[]) {
        System.out.println("=====INICIO DE SESION=====");
        for (int i = 3; i > 0; i--) {
            System.out.println("Le quedan " + (i) + " intentos");
            System.out.print("Digite su nombre de usuario: ");
            String username = input.nextLine();
            System.out.print("Digite su contraseña: ");
            String pasword = input.nextLine();
            for (int j = 0; j < users.length; j++) {
                if (users[j] == null) {
                    continue;
                }
                if (users[j].getUsername().equals(username) && users[j].getPasword().equals(pasword)) {
                    System.out.println("Inicio de sesion exitoso");
                    addOneData(users[j].getId(), users[j].getUsername());
                    return j;
                }
            }
            System.out.println("Contraseña o nombre de usuario incorrecto");
        }
        return null;
    }
}