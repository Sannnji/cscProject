//Samuel Acquaviva, James Ji
import java.util.Scanner;

public class LargeNumberTest{
    public static void main(String[] args){
        //Initializing Scanner and main-wide vars
        Scanner kb = new Scanner(System.in);
        String input;           //Holds user input as string to convert to UnboundedInt
        int choice;             //Holds user input for menu choice
        boolean run = true;     //Flag var for main loop

        //These UnboundedInts are defined by the user via the main loop
        UnboundedInt sum;
        UnboundedInt product;
        UnboundedInt num1;
        UnboundedInt num2;
        UnboundedInt clone;

        //Gets the first 2 UnboundedInts
        System.out.print("Please input the first number\n>");
        input = kb.nextLine();
        num1 = new UnboundedInt(input);
        System.out.print("Please input the second number\n>");
        input = kb.nextLine();
        num2 = new UnboundedInt(input);

        //Main loop runs, each time asking the user to make a menu choice. Choices are executed via a switch statement.
        while(run){
            System.out.print("[MENU]\n" +
                    "1.\tDisplay both numbers (with commas)\n" +
                    "2.\tInput two new numbers (without commas)\n" +
                    "3.\tCheck if numbers are equal\n" +
                    "4.\tReport the sum of the two numbers\n" +
                    "5.\tReport the multiplication of the two numbers\n" +
                    "6.\tCreate and output the clone of the first number\n" +
                    "7.\tQuit\n>");
            choice = kb.nextInt();

            switch(choice) {
                case 1:
                    System.out.println(num1.toString());
                    System.out.println(num2.toString());
                    break;
                case 2:
                    kb.nextLine();
                    System.out.print("Please input the first number\n>");
                    input = kb.nextLine();
                    num1 = new UnboundedInt(input);
                    System.out.print("Please input the second number\n>");
                    input = kb.nextLine();
                    num2 = new UnboundedInt(input);
                    break;
                case 3:
                    if (num1.equals(num2)) {
                        System.out.println("The two numbers are equal");
                    } else {
                        System.out.println("The two numbers aren't equal");
                    }
                    break;
                case 4:
                    //sum = num1.add(num2);
                    //System.out.println(sum.toString());
                    break;
                case 5:
                    //product = num1.multiply(num2);
                    //System.out.println(product.toString());
                    break;
                case 6:
                    //clone = num1.clone();
                    //System.out.println(clone.toString());
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