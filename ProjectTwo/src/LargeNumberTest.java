import java.util.Scanner;

public class LargeNumberTest{
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        String input;

        System.out.print("Please input the first number\n>");
        input = kb.nextLine();
        UnboundedInt num1 = new UnboundedInt(input);
        System.out.print("Please input the second number\n>");
        input = kb.nextLine();
        UnboundedInt num2 = new UnboundedInt(input);

        System.out.println(num1.toString());

    }
}