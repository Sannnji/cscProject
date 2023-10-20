/**
 Authors: Samuel Acquaviva, James Ji

 Description: This file tests the UnboundedInt class
 **/
import java.util.Scanner;

public class LargeNumberTest{
    public static String getUsrInput() {
        Scanner kb = new Scanner(System.in);
        String input;

        System.out.print("Please input the first number\n>");
        input = kb.nextLine();

        return input;
    }
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        int choice;             //Holds user input for menu choice
        boolean run = true;     //Flag var for main loop

        UnboundedInt num1;
        UnboundedInt num2;
        UnboundedInt sum;
        UnboundedInt product;
        UnboundedInt clone;

        num1 = new UnboundedInt(getUsrInput());
        num2 = new UnboundedInt(getUsrInput());

        //Main loop runs, each time asking the user to make a menu choice. Choices are executed via a switch statement.
        while(run){
            System.out.print("[MENU]\n" +
                    "1.\tDisplay both numbers (with commas)\n" +
                    "2.\tInput two new numbers (without commas)\n" +
                    "3.\tReport the sum of the two numbers\n"+
                    "4.\tReport the multiplication of the two numbers\n"+
                    "5.\tCheck if numbers are equal\n"+
                    "6.\tCreate and output the clone of the first number\n" +
                    "7.\tQuit\n>");

            choice = kb.nextInt();
            switch(choice) {
                case 1:
                    System.out.println(num1.toString());
                    System.out.println(num2.toString());
                    break;
                case 2:
                    num1 = new UnboundedInt(getUsrInput());
                    num2 = new UnboundedInt(getUsrInput());
                    break;
                case 3:
                    sum = num1.add(num2);
                    System.out.println(sum.toString());
                    break;
                case 4:
                    num1.multiply(num2);
//                    System.out.println(product.toString());
                    break;
                case 5:
                    if (num1.equals(num2)) {
                        System.out.println("The two numbers are equal");
                    } else {
                        System.out.println("The two numbers aren't equal");
                    }
                    break;
                case 6:
                    clone = num1.clone();
                    System.out.println(clone.toString());
                    break;
                case 7:
                    run = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("ERROR: Not a valid response");
                    break;
            }
            System.out.println();
        }
    }
}