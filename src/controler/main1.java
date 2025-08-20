package controler;

import java.util.Scanner;

import service.Menu;

public class main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();
        menu.menu(input);
        input.close();
    }
}