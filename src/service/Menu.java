package service;

import java.util.Scanner;

import model.Type;
import model.User;

public class Menu {
    private Createaccount account;
    private Modifyinformation modify;
    private Log log;
    private User users[];

    public Menu() {
        users = new User[50];
        log = new Log();
        account = new Createaccount();
        modify = new Modifyinformation(log);
    }

    public Integer addUser(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                return i;
            }
        }
        return null;
    }

    public void deleteUser(Integer i) {
        users[i] = null;
    }

    /**
     * Muestra el menú de opciones según el tipo de usuario (estándar o
     * administrador).
     *
     * @param input objeto {@link Scanner} para leer la entrada
     * @param user  usuario que está logueado
     * @return número de la opción seleccionada
     */
    public Integer menuOption(Scanner input, User user) {
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
     * Muestra y gestiona el menú principal del sistema para un usuario autenticado.
     * <p>
     * Este método controla el flujo de interacción del usuario con el sistema,
     * permitiéndole realizar distintas acciones según su rol ({@link Type#ADMIN} o usuario normal).
     * En caso de que el usuario sea administrador, se inicializa una instancia
     * de {@link Adminfunctions} para habilitar opciones avanzadas de gestión.
     * </p>
     *
     * <p>
     * Opciones principales del menú:
     * <ul>
     *     <li>1 - Modificar contraseña</li>
     *     <li>2 - Modificar nombre</li>
     *     <li>3 - Modificar nombre de usuario</li>
     *     <li>4 - Ver información personal</li>
     *     <li>5 - Ver historial personal</li>
     *     <li>6 - Buscar usuario (admin)</li>
     *     <li>7 - Modificar información de otros usuarios (admin)</li>
     *     <li>8 - Eliminar usuario (admin)</li>
     *     <li>9 - Crear usuario (admin)</li>
     *     <li>10 - Ver información de todos los usuarios (admin)</li>
     *     <li>11 - Ver historial completo (admin)</li>
     *     <li>12 - Cerrar sesión</li>
     * </ul>
     * </p>
     *
     * <p>
     * Cada acción ejecutada es registrada en el log mediante {@code log.addAcctionToLog}.
     * El menú se repite indefinidamente hasta que el usuario decida salir del sistema.
     * </p>
     *
     * @param input objeto {@link Scanner} para capturar las entradas del usuario en consola.
     */
    public void menu(Scanner input) {
        Adminfunctions admin = null;
        Integer l=null;
        Boolean x= true;
        do {
            admin=null;
            while (x) {
                l = actualUser(input);
                if (l == null) {
                    continue;
                }
                x=false;
            }
            if (users[l].getType().equals(Type.ADMIN)) {
                admin = new Adminfunctions(users[l]);
                admin.deleteUsers();
                admin.addArrayUsers(users);
            }
            Integer opcion = menuOption(input, users[l]);
            switch (opcion) {
                case 1:
                    modify.modifyPasword(input, users[l], admin);
                    System.out.print("Para volver a el menu presione enter ");
                    input.nextLine();
                    break;
                case 2:
                    modify.modifyName(input, users[l], admin);
                    System.out.print("Para volver a el menu presione enter ");
                    input.nextLine();
                    break;
                case 3:
                    modify.modifyUserName(input, users[l], admin, account);
                    account.changeUserNameData(l, users[l].getUsername());
                    System.out.print("Para volver a el menu presione enter ");
                    input.nextLine();
                    break;
                case 4:
                    information(users[l], null, admin);
                    System.out.print("Para volver a el menu presione enter ");
                    input.nextLine();
                    break;
                case 5:
                    log.showHistory(users[l], admin, input, false);
                    System.out.print("Para volver a el menu presione enter ");
                    input.nextLine();
                    break;
                case 6:
                    modify.searchUser(admin, input);
                    System.out.print("Para volver a el menu presione enter ");
                    input.nextLine();
                    break;
                case 7:
                    Integer j =modify.modifyInformations(admin, input, account);
                    if (j != null) {
                        account.changeUserNameData(j, users[j].getUsername());
                    }
                    System.out.print("Para volver a el menu presione enter ");
                    input.nextLine();
                    break;
                case 8:
                    deleteUser(admin, input);
                    System.out.print("Para volver a el menu presione enter ");
                    input.nextLine();
                    break;
                case 9:
                    createUser(admin, input);
                    System.out.print("Para volver a el menu presione enter ");
                    input.nextLine();
                    break;
                case 10:
                    allInformation(admin, input);
                    System.out.print("Para volver a el menu presione enter ");
                    input.nextLine();
                    break;
                case 11:
                    log.showHistory(users[l], admin, input, true);
                    System.out.print("Para volver a el menu presione enter ");
                    input.nextLine();
                    break;
                case 12:
                    x=true;
                    System.out.println("Cerrando sesion.....");
                    log.addAcctionToLog(users[l], admin, "Cerro sesion");
                    break;
                default:
                    return;
            }
        } while (true);
    }

    /**
     * Maneja el proceso de autenticación o creación de un nuevo usuario.
     * <p>
     * Dependiendo de la elección del usuario, este método permite:
     * <ul>
     *   <li>Iniciar sesión con un usuario existente.</li>
     *   <li>Crear una nueva cuenta e iniciar sesión con ella.</li>
     * </ul>
     * Además, registra las acciones realizadas (inicio de sesión o creación de cuenta) en el log.
     * Si el usuario que inicia sesión es de tipo {@link Type#ADMIN}, se inicializa
     * una instancia de {@link Adminfunctions}.
     * </p>
     *
     * @param input objeto {@link Scanner} utilizado para capturar los datos ingresados por el usuario.
     * @return el índice del usuario autenticado en el arreglo {@code users}, o {@code null}
     *         si no se pudo iniciar sesión correctamente.
     */
    public Integer actualUser(Scanner input) {
        Adminfunctions admin = null;
        if (loginOrCreate(input) == 1) {
            Integer l = account.login(input, users);
            if (l == null) {
                System.out.println("No se pudo iniciar sesion");
                return null;
            } else {
                if (users[l].getType().equals(Type.ADMIN)) {
                    admin = new Adminfunctions(users[l]);
                }
                log.addAcctionToLog(users[l], admin, "Inicio sesion");
                return l;
            }
        } else {
            Integer l = addUser(account.createAccount(input));
            if (users[l].getType().equals(Type.ADMIN)) {
                admin = new Adminfunctions(users[l]);
            }
            log.addAcctionToLog(users[l], admin, "Creo su cuenta");
            l = account.login(input, users);
            if (l == null) {
                System.out.println("No se ha podido iniciar sesion ");
                return null;
            } else {
                log.addAcctionToLog(users[l], admin, "Inicio sesion");
                return l;
            }
        }
    }

    /**
     * Muestra información de un usuario (propia o de otros en caso de ser admin).
     *
     * @param user  usuario estándar
     * @param i     índice del usuario buscado (si aplica)
     * @param admin Adminfunctions
     */
    public void information(User user, Integer i, Adminfunctions admin) {
        System.out.println();
        if (user.getType().equals(Type.STANDARD)) {
            System.out.println(user.toString());
            log.addAcctionToLog(user, null, "Busco su informacion");
        } else if (i != null) {
            admin.showInformation(i);
            log.addAcctionToLog(null, admin, "Busco la informacion del usuario " + admin.getUserByIndex(i).getName());
        } else if (admin != null) {
            admin.showActualInformation();
            log.addAcctionToLog(null, admin, "Busco su informacion");
        }
    }

    /**
     * Elimina un usuario después de confirmar la acción.
     *
     * @param admin Adminfunctions
     * @param input objeto {@link Scanner}
     * @return {@code true} si se eliminó correctamente, {@code false} en caso
     *         contrario
     * @see #removeOneData(Integer)
     */
    public Boolean deleteUser(Adminfunctions admin, Scanner input) {
        if (admin == null) {
            System.out.println("No eres admin ");
            return false;
        }
        if (modify.actualPasword(input, admin.getUser())==false) {
            return false;
        }
        Integer i = modify.searchUser(admin, input);
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
        account.removeOneData(i);
        admin.deleteUser(i);
        deleteUser(i);
        System.out.println("Datos borrados con exito ");
        log.addAcctionToLog(null, admin, "Borro un usuario");
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
        User user = account.createAccount(input);
        log.addAcctionToLog(user, null, "Se creo su cuenta");
        addUser(user);
        log.addAcctionToLog(null, admin, "Creo un usuario");
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
        log.addAcctionToLog(null, admin, "Mostro la informacion de todos los usuarios");
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
                    for (int i = 0; i < 50; i++) {
                        if (account.checkIsEmpity(i)) {
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