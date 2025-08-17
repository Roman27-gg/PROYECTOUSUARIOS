package service;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Createuser {

    /**
     * Valida el nombre ingresado por el usuario a través de consola.
     * <p>
     * Solo se permiten caracteres alfabéticos y espacios,
     * no se aceptan cadenas vacías ni compuestas únicamente de espacios.
     * </p>
     *
     * @param input objeto {@link Scanner} utilizado para leer la entrada del
     *              usuario
     * @return el nombre validado como {@link String}
     */
    public static String validateName(Scanner input) {
        do {
            System.out.print("Digite el nombre del usuario: ");
            String name = input.nextLine();
            if (name.matches("[a-zA-Z\\s]*") && !name.matches("\\s*") && name.length() > 0) {
                return name;
            } else {
                System.out.println("Nombre no valido ");
            }
        } while (true);
    }

    /**
     * Valida el nombre de usuario ingresado por el usuario.
     * <p>
     * El nombre de usuario debe contener letras y puede incluir números,
     * pero no puede estar compuesto solo por números o espacios.
     * Además, no se aceptan cadenas vacías.
     * </p>
     *
     * @param input objeto {@link Scanner} utilizado para leer la entrada del
     *              usuario
     * @return el nombre de usuario validado como {@link String}
     */
    public static String validateUserName(Scanner input) {
        do {
            System.out.print("Digite el nombre de usuario (debe ser unico): ");
            String name = input.nextLine();
            Matcher match = Pattern.compile("[a-zA-Z\\s]*([0-9\\s])*?").matcher(name);
            if (match.find() && !name.matches("\\s*") && name.length() > 0 && !name.matches("[0-9\\s]*")) {
                return name;
            } else {
                System.out.println("Username no valido ");
            }
        } while (true);
    }

    /**
     * Valida la contraseña ingresada por el usuario.
     * <p>
     * La contraseña debe cumplir con las siguientes condiciones:
     * <ul>
     * <li>Mínimo 8 caracteres</li>
     * <li>Al menos una letra mayúscula</li>
     * <li>Al menos un número</li>
     * </ul>
     * No se aceptan cadenas vacías ni compuestas únicamente de espacios.
     * </p>
     *
     * @param input objeto {@link Scanner} utilizado para leer la entrada del
     *              usuario
     * @return la contraseña validada como {@link String}
     */
    public static String validatePasword(Scanner input) {
        do {
            System.out.print("Digite la contraseña (minimo: 8 caracteres, 1 mayuscula, y 1 numero): ");
            String name = input.nextLine();
            Matcher match1 = Pattern.compile("[A-Z]{1,}\\s*").matcher(name);
            Matcher match2 = Pattern.compile("[0-9]{1,}\\s*").matcher(name);
            Matcher match = Pattern.compile("[a-z]*").matcher(name);
            if (match.find() && !name.matches("\\s*") && name.length() > 7 && match1.find() && match2.find()) {
                return name;
            } else {
                System.out.println("contraseña no valido ");
            }
        } while (true);
    }

    /**
     * Genera un identificador único de usuario.
     * <p>
     * El identificador es un número entero aleatorio de 6 dígitos,
     * convertido a {@link String}.
     * </p>
     *
     * @return el identificador generado como {@link String}
     */
    public static String createId() {
        Random randomnum = new Random();
        Integer num = randomnum.nextInt(100000, 1000000);
        return String.valueOf(num);
    }

}
