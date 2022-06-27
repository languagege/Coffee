/**
 * Three coffee to chose:
 * --------------------------------------------------------
 * espresso:    250 water / 16 coffee / $4                |
 * latte:       350 water / 75 milk / 20 coffee / $7      |
 * cappuccino:  200 water / 100 milk / 12 coffee / $6     |
 * --------------------------------------------------------
 */

import java.util.Scanner;

public class RunCoffeeMachine {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        boolean power = true;
        yourChoice nextAction = yourChoice.SELECT;
        while (power) {
            switch (nextAction) {
                case SELECT:
                    nextAction = coffeeMachine.userInput();
                    break;
                case BUY:
                    coffeeMachine.makeCoffee();
                    nextAction = yourChoice.SELECT;
                    break;
                case FILL:
                    coffeeMachine.fillMachine();
                    nextAction = yourChoice.SELECT;
                    break;
                case TAKE:
                    coffeeMachine.takeMoney();
                    nextAction = yourChoice.SELECT;
                    break;
                case REMAINING:
                    coffeeMachine.showRemain();
                    nextAction = yourChoice.SELECT;
                    break;
                case EXIT:
                    power = false;
            }
        }
        System.out.println("\nGood bye!");
    }
}

class CoffeeMachine {
    Scanner scanner = new Scanner(System.in);
    private int water = 400;
    private int milk = 540;
    private int coffee = 120;
    private int cup = 9;
    private int money = 550;

    //    display customer's choice
    private void greetings() {
        System.out.println("Write action " +
                "(buy, fill, take, remaining, exit):");
    }

    private void whichCoffee() {
        System.out.println("What do you want to buy? " +
                "1 - espresso, " +
                "2 - latte, " +
                "3 - cappuccino, " +
                "back - to main menu:");
    }

    public yourChoice userInput() {
        greetings();
        String inputWhat = scanner.next();
        switch (inputWhat) {
            case "buy":
                return yourChoice.BUY;
            case "fill":
                return yourChoice.FILL;
            case "take":
                return yourChoice.TAKE;
            case "remaining":
                return yourChoice.REMAINING;
            case "exit":
                return yourChoice.EXIT;
            default:
                return yourChoice.SELECT;
        }
    }

    void makeCoffee() {
        whichCoffee();
        String mkCoffee = scanner.next();
        if (cup <= 0) {
            excuse("cups");
            return;
        } else if (water < 200) {
            excuse("water");
            return;
        } else if (coffee < 12) {
            excuse("coffee beans");
            return;
        } else if ((milk < 75) && ((mkCoffee.equals("2")) || (mkCoffee.equals("3")))) {
            excuse("milk");
            return;
        }
        switch (mkCoffee) {
            case "1": //espresso
                mk1();
                yesCoffee();
                break;
            case "2":
                mk2();
                yesCoffee();
                break;
            case "3":
                mk3();
                yesCoffee();
                break;
            default:
                break;
        }
    }


    void fillMachine() {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        coffee += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        cup += scanner.nextInt();
    }

    void takeMoney() {
        System.out.println("I gave you $" + money + "\n");
        money = 0;
    }

    void showRemain() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffee + " g of coffee beans");
        System.out.println(cup + " disposable cups");
        System.out.println("$" + money + " of money");
        System.out.println();
    }

    private void excuse(String whatLess) {
        System.out.println("Sorry, not enough " + whatLess + "!\n");
    }

    private void yesCoffee() {
        System.out.println("I have enough resources, making you a coffee!\n");
    }

    private void mk1() {
        water -= 250;
        coffee -= 16;
        money += 4;
        cup--;
    }

    private void mk2() {
        water -= 350;
        milk -= 75;
        coffee -= 20;
        money += 7;
        cup--;
    }

    private void mk3() {
        water -= 200;
        milk -= 100;
        coffee -= 12;
        money += 6;
        cup--;
    }
}

enum yourChoice {
    SELECT, BUY, FILL, TAKE, REMAINING, EXIT
}