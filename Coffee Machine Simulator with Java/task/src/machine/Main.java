package machine;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        final Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            coffeeMachine.inputHandler(scanner.next());
        }
    }
}