package service;

import java.time.LocalDateTime;
import java.util.Scanner;

import model.User;

public class Log {

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
}
