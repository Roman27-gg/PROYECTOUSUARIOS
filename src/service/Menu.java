package service;

import java.time.LocalDateTime;
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
     * Verifica si un par de ID y nombre de usuario ya existe en los arreglos.
     *
     * @param id identificador del usuario a verificar
     * @param username nombre de usuario a verificar
     * @return {@code true} si no se repite, {@code false} si ya existe
     */
    public Boolean isRepeat(String id, String username) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == null) {
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

    /**
     * Muestra el menú de opciones según el tipo de usuario (estándar o administrador).
     *
     * @param input objeto {@link Scanner} para leer la entrada
     * @param user usuario que está logueado
     * @return número de la opción seleccionada
     */
    public Integer menu(Scanner input, User user) {
        System.out.println("=====MENU=====");
        if (user.getType() == Type.STANDARD) {
            do {
                System.out.println("""
                        1. Modificar contraseña
                        2. Modificar nombre
                        3. Modificar nombre de usuario
                        4. Ver informacion
                        5. Mostrar historial de acciones
                        12. Cerrar sesion
                        0. Salir del programa""");
                System.out.print("Digite su opcion: ");
                String validate = input.nextLine();
                if (validate.matches("[0-5]|12")) {
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
                        5. Ver historial de acciones propio
                        6. Buscar un usuario
                        7. Modificar la informacion de un usuario especifico
                        8. Borrar un usuario
                        9. Añadir un usario
                        10. Ver la informacion de todos los usuarios
                        11. Buscar historial de un usuario en especifico
                        12. Cerrar sesion
                        0. Salir del programa""");
                System.out.print("Digite su opcion: ");
                String validate = input.nextLine();
                if (validate.matches("[0-9]|10|11|12")) {
                    return Integer.parseInt(validate);
                } else {
                    System.out.println("Opcion no valida");
                }
            } while (true);
        }

    }

    /**
     * Verifica si la contraseña actual ingresada por el usuario coincide con la almacenada.
     *
     * @param input objeto {@link Scanner} para leer la entrada
     * @param user usuario al que se le valida la contraseña
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
     * @param user usuario estándar
     * @param admin Adminfunctions (puede ser {@code null})
     * @see #actualPasword(Scanner, User)
     */
    public void modifyPasword(Scanner input, User user, Adminfunctions admin) {
        if (actualPasword(input, user) == false) {
            return;
        }
        if (user.getType().equals(Type.STANDARD)) {
            user.setNewPasword(input);
            addAcctionToLog(user, null, "Cambio su contraseña");
        } else if (admin != null) {
            admin.setActualPasword(input);
            addAcctionToLog(null, admin, "Cambio su contraseña");
        }
        System.out.println("Contraseña cambiada con exito");
    }

     /**
     * Permite modificar el nombre de un usuario o administrador.
     *
     * @param input objeto {@link Scanner}
     * @param user usuario 
     * @param admin Adminfunctions (puede ser {@code null})
     */
    public void modifyName(Scanner input, User user, Adminfunctions admin) {
        if (actualPasword(input, user) == false) {
            return;
        }
        if (user.getType().equals(Type.STANDARD)) {
            user.setNewName(input);
            addAcctionToLog(user, null, "Cambio su nombre");
        } else if (admin != null) {
            admin.setActualName(input);
            addAcctionToLog(null, admin, "Cambio su nombre");
        }
        System.out.println("Nombre cambiado con exito");
    }

    /**
     * Permite modificar el nombre de usuario.
     *
     * @param input objeto {@link Scanner}
     * @param user usuario estándar
     * @param admin Adminfunctions (puede ser {@code null})
     */
    public void modifyUserName(Scanner input, User user, Adminfunctions admin) {
        if (actualPasword(input, user) == false) {
            return;
        }
        if (user.getType().equals(Type.STANDARD)) {
            user.setNewUserName(input);
            addAcctionToLog(user, null, "Cambio su nombre de usuario");
        } else if (admin != null) {
            admin.setActualUserName(input);
            addAcctionToLog(null, admin, "Cambio su nombre de usuario");
        }
        System.out.println("Nombre de usuario cambiado con exito");
    }

    /**
     * Muestra información de un usuario (propia o de otros en caso de ser admin).
     *
     * @param user usuario estándar
     * @param i índice del usuario buscado (si aplica)
     * @param admin Adminfunctions
     */
    public void information(User user, Integer i, Adminfunctions admin) {
        System.out.println();
        if (user.getType().equals(Type.STANDARD)) {
            System.out.println(user.toString());
            addAcctionToLog(user, null, "Busco su informacion");
        } else if (i != null) {
            admin.showInformation(i);
            addAcctionToLog(null, admin, "Busco la informacion del usuario " + admin.getUserByIndex(i).getName());
        } else if (admin != null) {
            admin.showActualInformation();
            addAcctionToLog(null, admin, "Busco su informacion");
        }
    }

    /**
     * Muestra el historial de acciones de un usuario o de todos en caso de administrador.
     *
     * @param user usuario estándar
     * @param admin Adminfunctions
     * @param input objeto {@link Scanner}
     * @param byIndex indica si se busca un historial específico por índice
     */
    public void showHistory(User user, Adminfunctions admin, Scanner input, Boolean byIndex) {
        if (admin == null) {
            user.showHistory();
            addAcctionToLog(user, null, "Mostro su log");
        } else if (byIndex == false) {
            admin.showActualHistory();
            addAcctionToLog(null, admin, "Mostro su log");
        } else {
            System.out.println("Digite como desea buscar el usuario para mostrar su historial ");
            String message;
            Integer opcion = menuOpcion(input);
            message = (opcion == 1) ? "Digite el nombre: "
                    : (opcion == 2) ? "Digite el nombre de usuario: " : "Digite la id: ";
            if (admin.showHistoryByIndex(input, message) == true) {
                addAcctionToLog(null, admin, "Busco el log de un usuario");
            }
        }
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
            addAcctionToLog(null, admin, "Busco un usuario");
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
            addAcctionToLog(null, admin, "Cambio el nombre del usuario " + admin.getUserByIndex(i).getName());
            addAcctionToLog(admin.getUserByIndex(i), null, "Su nombre fue cambiado");
        } else if (opcion == 2) {
            admin.setNewUserNameByIndex(input, i);
            addAcctionToLog(null, admin, "Cambio el nombre de usuario de " + admin.getUserByIndex(i).getName());
            addAcctionToLog(admin.getUserByIndex(i), null, "Su nombre  de usuario fue cambiado");
        } else if (opcion == 3) {
            admin.setNewPaswordByIndex(input, i);
            addAcctionToLog(null, admin, "Cambio la contraseña del usuario " + admin.getUserByIndex(i).getName());
            addAcctionToLog(admin.getUserByIndex(i), null, "Su contraseña fue cambiada");
        } else {
            setNewType(input, admin, i);
        }
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
                    addAcctionToLog(null, admin, "Cambio el rol a admin del usuario " + admin.getUserByIndex(i).getName());
                    addAcctionToLog(admin.getUserByIndex(i), null, "Su rol fue ascendido");
                    return;
                }
            } else if (validate.equals("2")) {
                if (admin.getUserByIndex(i).getType().equals(Type.STANDARD)) {
                    System.out.println("El usuario ya tiene actualmente el rol de estandar");
                    return;
                } else {
                    System.out.println("Usuario ahora tiene el rol de estandar ");
                    admin.setTypeByIndex(i, "Estandar");
                    addAcctionToLog(null, admin, "Cambio el rol a estandar del usuario " + admin.getUserByIndex(i).getName());
                    addAcctionToLog(admin.getUserByIndex(i), null, "Su rol fue cambiado a estandar");
                    return;
                }
            } else {
                System.out.println("Opcion no valida");
            }
        } while (true);
    }

    /**
     * Elimina un usuario después de confirmar la acción.
     *
     * @param admin Adminfunctions
     * @param input objeto {@link Scanner}
     * @return {@code true} si se eliminó correctamente, {@code false} en caso contrario
     * @see #removeOneData(Integer)
     */
    public Boolean deleteUser(Adminfunctions admin, Scanner input) {
        if (admin == null) {
            System.out.println("No eres admin ");
            return false;
        }
        if (actualPasword(input, admin.getUser())) {
            return false;
        }
        Integer i = searchUser(admin, input);
        if (i == null) {
            return false;
        }
        do {
            System.out.print("¿Esta seguro que desea borrar los datos del usuario?: ");
            String validate = input.nextLine();
            if (validate.equalsIgnoreCase("no")) {
                System.out.println("No se han borrado los datos");
                return false;
            } else if (validate.equalsIgnoreCase("si")) {
                break;
            } else {
                System.out.println("Opcion no valida solo si o no");
            }
        } while (true);
        removeOneData(i);
        admin.deleteUser(i);
        System.out.println("Datos borrados con exito ");
        addAcctionToLog(null, admin, "Borro un usuario");
        return true;
    }

    /**
     * Permite a un administrador crear un nuevo usuario.
     *
     * @param admin Adminfunctions
     * @param input objeto {@link Scanner}
     * @see #createAccount(Scanner)
     */
    public void createUser(Adminfunctions admin, Scanner input) {
        if (admin == null) {
            System.out.println("No eres admin ");
            return;
        }
        admin.addUser(createAccount(input));
        addAcctionToLog(null, admin, "Creo un usuario");
    }

    /**
     * Muestra toda la información de los usuarios registrados (solo admin).
     *
     * @param admin Adminfunctions
     * @param input objeto {@link Scanner}
     */
    public void allInformation(Adminfunctions admin, Scanner input) {
        if (admin == null) {
            System.out.println("No eres admin ");
            return;
        }
        admin.showAllInformation();
        addAcctionToLog(null, admin, "Mostro la informacion de todos los usuarios");
    }

    /**
     * Pregunta al usuario si desea iniciar sesión o crear una cuenta.
     *
     * @param input objeto {@link Scanner}
     * @return 1 para login, 2 para crear cuenta
     */
    public Integer loginOrCreate(Scanner input) {
        do {
            System.out.println("Que desea hacer: ");
            System.out.println("""
                    1. Iniciar sesion
                    2. Crear cuenta
                        """);
            System.out.print("Digite su opcion: ");
            String validate = input.nextLine();
            if (validate.matches("1|2")) {
                if (validate.matches("1")) {
                    for (int i = 0; i < ids.length; i++) {
                        if (ids[i] != null) {
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

     /**
     * Agrega una acción al historial (log) de un usuario o administrador.
     *
     * @param user usuario estándar (puede ser {@code null})
     * @param admin Adminfunctions (puede ser {@code null})
     * @param acction descripción de la acción realizada
     */
    public void addAcctionToLog(User user, Adminfunctions admin, String acction) {
        if (admin != null) {
            admin.addActtionAdmin(acction, LocalDateTime.now());
        } else {
            user.addAcction(acction, LocalDateTime.now());
        }
    }
}