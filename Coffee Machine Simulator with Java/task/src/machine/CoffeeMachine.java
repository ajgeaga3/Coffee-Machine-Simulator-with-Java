package machine;

import java.util.Locale;

public class CoffeeMachine {

    private MachineState current;
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
        menu();
    }

    public void menu() {
        current = MachineState.MENU;
        System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
    }

    public void inputHandler(String input) {
        switch (current) {
            case MENU:
                action(input);
                break;
            case FILLINGWATER:
                fillingWater(input);
                break;
            case BUYINGCOFFEE:
                coffeeChoosing(input);
                break;
            case FILLINGCUPS:
                fillingCups(input);
                break;
            case FILLINGMILK:
                fillingMilk(input);
                break;
            case FILLINGBEANS:
                fillingBeans(input);
                break;
        }
    }

    public void howMuch() {
        System.out.println("The coffee machine has: ");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
        menu();
    }

    public void action(String input) {

        switch (input.toLowerCase(Locale.ROOT)) {
            case "take":
                take();
                break;
            case "buy":
                buy();
                current = MachineState.BUYINGCOFFEE;
                break;
            case "fill":
                System.out.println("Write how many ml of water you want to add: ");
                current = MachineState.FILLINGWATER;
                break;
            case "remaining":
                howMuch();
                break;
            case "exit":
                System.exit(0);
                break;
        }
    }

    public void take() {
        System.out.println("I gave you $" + money);
        money = 0;
        menu();
    }
    public void fillingWater(String input) {
        water += Integer.parseInt(input);
        current = MachineState.FILLINGMILK;
        System.out.println("Write how many ml of milk you want to add: ");

    }
    public void fillingMilk(String input) {
        milk += Integer.parseInt(input);
        current = MachineState.FILLINGBEANS;
        System.out.println("Write how many grams of coffee beans you want to add: ");
    }
    public void fillingBeans(String input) {
        beans += Integer.parseInt(input);
        current = MachineState.FILLINGCUPS;
        System.out.println("Write how many disposable cups of coffee you want to add: ");
    }
    public void fillingCups(String input) {
        cups += Integer.parseInt(input);
        menu();
    }

    public void buy() {
        System.out.println();
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
    }

    public void coffeeChoosing(String input) {
        switch (input) {
            case "1":
                coffeeMaking(CoffeeStandards.ESSPRESSO);
                break;
            case "2":
                coffeeMaking(CoffeeStandards.LATTE);
                break;
            case "3":
                coffeeMaking(CoffeeStandards.CAPPUCCINO);
                break;
            case "back":
                menu();
                break;

        }
    }

    public void coffeeMaking (CoffeeStandards coffeeStandards) {
        boolean isWater = water >= coffeeStandards.getWater();
        boolean isMilk = milk >= coffeeStandards.getMilk();
        boolean isBeans = beans >= coffeeStandards.getBeans();
        boolean isCups = cups > 0;
        if (isWater && isMilk && isBeans && isCups) {
            water -= coffeeStandards.getWater();
            milk -= coffeeStandards.getMilk();
            beans -= coffeeStandards.getBeans();
            money += coffeeStandards.getPrice();
            cups -= 1;
            System.out.println("I have enough resources, making you a coffee!");
            menu();

        } else {
            if (!isBeans) {
                System.out.println("Sorry, not enough beans!");
                menu();
            } else if (!isWater) {
                System.out.println("Sorry, not enough water!");
                menu();
            } else if (!isMilk) {
                System.out.println("Sorry, not enough milk!");
                menu();
            } else if (!isCups) {
                System.out.println("Sorry, not enough cups!");
                menu();
            }
        }
    }


}