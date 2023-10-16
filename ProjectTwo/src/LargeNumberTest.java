import java.util.*;

public class LargeNumberTest{
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        String input = "123456";

        System.out.print("Please input the first number\n>");
        //input = kb.getline();
        UnboundedInt num1 = new UnboundedInt(input);
        System.out.print("Please input the second number\n>");
        //input = kb.getline();
        UnboundedInt num2 = new UnboundedInt(input);

    }
}