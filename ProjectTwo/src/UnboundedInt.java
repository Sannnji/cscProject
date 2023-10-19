public class UnboundedInt {
    //An integer called manyNodes that equals the number of Nodes
    private int manyNodes;

    //Link to front of list, called front
    private IntNode front;

    //Link to back of list, called back
    private IntNode back;

    //A cursor that points to an IntNode within the list
    private IntNode cursor;

    //This constructor will take a string of digits(no commas) and turn it into an UnboundedInt object (MUST BE STRING INPUT)
    public UnboundedInt(String input) {
        String temp;            //Used to temporarily hold a String containing 3 integers
        String formattedInput;    //Used to hold validated input (String length is divisible by 3)
        int count;              //Used to iterate through the checkedInput string

        formattedInput = inputStringVal(input);
        int nodesNeeded = formattedInput.length()/3;

        System.out.println(formattedInput);

        temp = formattedInput.substring(formattedInput.length() - 3);
        front = new IntNode(Integer.parseInt(temp), null);
        manyNodes++;
        back = front;
        cursor = front;

        temp = formattedInput.substring(6, 9);
        front = new IntNode(Integer.parseInt(temp), front);
        manyNodes++;
        System.out.println(toString());

//        if(nodesNeeded > 1){
//            for(count = nodesNeeded - 1; count > 0; count--){
//                temp = formattedInput.substring((count * 3) - 3, ((count * 3)));
//                System.out.println("temp: " + temp);
//                front = new IntNode(Integer.parseInt(temp), front);
//                System.out.println(toString());
//                System.out.println(count);
//                System.out.println();
//                manyNodes++;
//            }
//        }

        start();
    }

    //This ensures that the length of the inputted string is divisible by 3
    private String inputStringVal(String input){
        int remainder = (input.length() % 3);

        if(remainder == 1) {
            return "00" + input;
        }else if(remainder == 2){
            return "0" + input;
        }else{
            return input;
        }
    }

    //move the cursor along the list
    //Throws an IllegalStateException if the cursor is null
    public void advance(){
        if(cursor.getLink() == null){
            //throw new IllegalStateException();
        }else {
            cursor = cursor.getLink();
        }
    }

    //a method that returns the integer value of the Node that is pointed to by the cursor.
    //Throws an IllegalStateException if the cursor is not pointing to a Node
    public int getNodeValue(){
        if(cursor == null){
            //throw new IllegalStateException();
        }else{
            return cursor.getData();
        }
        return 0;
    }

    //Creates a string of all elements in order separated by commas,
    //making sure leading zeros are added when needed.  (i.e.  12,005,016 or  34,000 )
    //Throw an IllegalStateException if the sequence is empty
    public String toString(){
        String output = "";
        if(front == null){
            throw new IllegalStateException();
        }else{
            if(manyNodes > 1) {
                start();
                for (int x = 0; x <= manyNodes; x++) {
                    if (x == 0) {
                        output += cursor.getData();
                    }
                    output = cursor.getData() + "," + output;
                    advance();
                }
            }else{
                output = front.getData() + output;
            }
        }

        return output;
    }

    //set the cursor to the front of the list
    public void start(){
        this.cursor = front;
    }

    public static void add (UnboundedInt num1, UnboundedInt num2) {
        int i = 0;
        int remainder = 0;
        String bigIntSum = "";

        while (i <= Math.max(num1.manyNodes, num2.manyNodes)) {
            int sum = 0;
            String temp = "";
            Boolean num1HasNodeAtIndex = i <= num1.manyNodes;
            Boolean num2HasNodeAtIndex = i <= num2.manyNodes;

            System.out.println("data: " + num1.cursor.getData());
            System.out.println("data2: " + num2.cursor.getData());
            System.out.println(num1HasNodeAtIndex && num2HasNodeAtIndex);
            if ( num1HasNodeAtIndex && num2HasNodeAtIndex ) {
                sum = num1.cursor.getData() + num2.cursor.getData() + remainder;
                System.out.println("sum: " + sum);
            } else if (num1HasNodeAtIndex && !num2HasNodeAtIndex) {
                sum = num1.cursor.getData() + remainder;
            } else if (!num1HasNodeAtIndex && num2HasNodeAtIndex) {
                sum = num2.cursor.getData() + remainder;
            } else {
                sum = remainder;
            }
            remainder = 0;

            Integer sumObj = sum;
            String sumStr = sumObj.toString();
            Boolean remainderPresent = sumStr.length() > 3;

            if (sumStr.length() < 3) {
                while (sumStr.length() != 3) {
                    sumStr = "0" + sumStr;
                }
            }

            if ( remainderPresent ) {
                temp = sumStr.substring(1,4);
                remainder = Integer.parseInt(sumStr.substring(0,1));
            } else {
                temp = sumStr.substring(0,3);
            }


            bigIntSum = temp + bigIntSum;

            System.out.println(remainder);
            System.out.println(temp);
            System.out.println();

            num1.advance();
            num2.advance();
            i++;
        }

        System.out.println(bigIntSum);
    }

    /**
    UnboundedInt add (UnboundedInt )
    A method that adds the current UnboundedInt with a passed in one.  The return is a new UnboundedInt.

    UnboundedInt multiply (UnboundedInt )  - do this one last!
    A method that multiplies the current UnboundedInt with a passed in one.  The return is a new UnboundedInt.

    void addEnd  ( int )  -optional method (helpful)
    A method to add a new element at the end of the sequence , used for building up each higher term in a single sequence.  (i.e. adding a new IntNode to the linked list)

    UnboundedInt clone(  )
    a method that returns a copy of the original structure

    boolean equals ( Object )
    a method that returns true if linked list represents the same numerical number as the input parameter.  False otherwise.  Overrides method in Object class.
    **/
}