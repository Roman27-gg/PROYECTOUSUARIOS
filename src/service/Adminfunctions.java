package service;

import java.time.LocalDateTime;
import java.util.Scanner;

import model.User;

public class Adminfunctions {
    private User user;
    private User users[];

    public Adminfunctions(User user) {
        users = new User[50];
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public User[] getUsers() {
        return users;
    }

    public User getUserByIndex(Integer i) {
        return users[i];
    }

    /**
     * Muestra la información del usuario actual en consola.
     */
    public void showActualInformation() {
        System.out.println(user.toString());
    }

    /**
     * Muestra la información del usuario en una posición específica del arreglo.
     *
     * @param i índice del usuario en el arreglo.
     */
    public void showInformation(int i) {
        System.out.println(users[i].toString());
    }

    /**
     * Muestra la información de todos los usuarios registrados.
     * Ignora las posiciones nulas en el arreglo.
     */
    public void showAllInformation() {
        for (int i = 0; i < 50; i++) {
            if (users[i] == null) {
                continue;
            }
            showInformation(i);
        }
    }

    /**
     * Permite al usuario actual cambiar su contraseña.
     *
     * @param input objeto Scanner utilizado para leer la nueva contraseña.
     */
    public void setActualPasword(Scanner input) {
        user.setNewPasword(input);
    }

    /**
     * Cambia la contraseña de un usuario en una posición específica del arreglo.
     *
     * @param input objeto Scanner utilizado para leer la nueva contraseña.
     * @param i     índice del usuario en el arreglo.
     */
    public void setNewPaswordByIndex(Scanner input, int i) {
        users[i].setNewPasword(input);
    }

    /**
     * Cambia el nombre de un usuario en una posición específica del arreglo.
     *
     * @param input objeto Scanner utilizado para leer el nuevo nombre.
     * @param i     índice del usuario en el arreglo.
     */
    public void setNewNamedByIndex(Scanner input, int i) {
        users[i].setNewName(input);
    }

    /**
     * Cambia el nombre del usuario actual.
     *
     * @param input objeto Scanner utilizado para leer el nuevo nombre.
     */
    public void setActualName(Scanner input) {
        user.setNewName(input);
    }

    /**
     * Cambia el nombre de usuario de un usuario en una posición específica del
     * arreglo.
     *
     * @param input objeto Scanner utilizado para leer el nuevo nombre de usuario.
     * @param i     índice del usuario en el arreglo.
     */
    public void setNewUserNameByIndex(Scanner input, int i) {
        users[i].setNewUserName(input);
    }

    /**
     * Cambia el nombre de usuario del usuario actual.
     *
     * @param input objeto Scanner utilizado para leer el nuevo nombre de usuario.
     */
    public void setActualUserName(Scanner input) {
        user.setNewUserName(input);
    }

    /**
     * Asigna el tipo de usuario (ADMIN o STANDARD) a un usuario en una posición
     * específica del arreglo.
     *
     * @param i          índice del usuario en el arreglo.
     * @param opciontype tipo de usuario ("Admin" para administrador, otro valor
     *                   para estándar).
     */
    public void setTypeByIndex(int i, String opciontype) {
        users[i].setType((opciontype.equals("Admin")) ? model.Type.ADMIN : model.Type.STANDARD);
    }

    /**
     * Elimina un usuario del arreglo en una posición específica.
     *
     * @param i índice del usuario en el arreglo.
     */
    public void deleteUser(int i) {
        users[i] = null;
    }

    /**
     * Agrega un nuevo usuario al arreglo en la primera posición disponible.
     *
     * @param user objeto User a agregar.
     */
    public void addUser(User user) {
        for (int i = 0; i < 50; i++) {
            if (users[i] == null) {
                users[i] = user;
                return;
            }
        }
    }

    /**
     * Reemplaza el arreglo de usuarios por uno nuevo.
     *
     * @param users arreglo de usuarios a asignar.
     */
    public void addArrayUsers(User users[]) {
        this.users = users;
    }

    /**
     * Busca un usuario en el arreglo según un criterio (nombre, nombre de usuario o
     * id).
     *
     * @param input   objeto Scanner utilizado para leer el valor de búsqueda.
     * @param message mensaje que indica el tipo de búsqueda.
     *                Puede ser "Digite el nombre: ", "Digite el nombre de usuario:
     *                " o "Digite la id: ".
     * @return índice del usuario encontrado, o null si no existe.
     */
    public Integer searchBy(Scanner input, String message) {
        System.out.print(message);
        String validate = input.nextLine();
        System.out.println("Buscando.....");
        for (int i = 0; i < users.length; i++) {
            if (message.equals("Digite el nombre: ")) {
                if (users[i] == null) {
                    continue;
                }
                if (users[i].getName().equals(validate)) {
                    System.out.println("Persona encontrada ");
                    System.out.println(users[i].toString());
                    return i;
                }
            } else if (message.equals("Digite el nombre de usuario: ")) {
                if (users[i] == null) {
                    continue;
                }
                if (users[i].getUsername().equals(validate)) {
                    System.out.println("Persona encontrada ");
                    System.out.println(users[i].toString());
                    return i;
                }
            } else if (message.equals("Digite la id: ")) {
                if (users[i] == null) {
                    continue;
                }
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

    /**
     * Registra una acción en el historial del usuario actual.
     *
     * @param acttion acción realizada.
     * @param date    fecha y hora de la acción.
     */
    public void addActtionAdmin(String acttion, LocalDateTime date) {
        user.addAcction(acttion, date);
    }

    /**
     * Muestra el historial de acciones del usuario actual.
     */
    public void showActualHistory() {
        user.showHistory();
    }

    /**
     * Registra una acción en el historial de un usuario específico.
     *
     * @param acction acción realizada.
     * @param date    fecha y hora de la acción.
     * @param i       índice del usuario en el arreglo.
     */
    public void addActionByIndex(String acction, LocalDateTime date, Integer i) {
        users[i].addAcction(acction, date);
    }

    /**
     * Busca un usuario según un criterio y muestra su historial de acciones.
     *
     * @param input   objeto Scanner utilizado para leer el valor de búsqueda.
     * @param message mensaje que indica el tipo de búsqueda.
     * @return true si se encontró y mostró el historial, false si no se encontró.
     */
    public Boolean showHistoryByIndex(Scanner input, String message) {
        Integer i = searchBy(input, message);
        if (i == null) {
            return false;
        }
        users[i].showHistory();
        return true;
    }

}
