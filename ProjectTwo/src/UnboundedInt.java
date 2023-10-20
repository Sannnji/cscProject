/**
 Authors: Samuel Acquaviva, James Ji

 Description: This class allows for the creation, addition, and multiplication of values
 that are too large to be stored with typical java data types
 **/
public class UnboundedInt implements Cloneable {
    /**
     Invariants
     1. manyNodes - represents the amount of nodes in the UnboundedInt

     2. front - refers to the front of the list (note - this would mean the
     least significant value--ex: 18,965 is stored as [965]->[018] )

     3. cursor - is used as an iterator/pointer, and represents the current node

     4. back - refers to the tail of the list (note - this would mean the
     most significant value)

     5. each IntNode contains 1 to 3 digits, the max value being 999, "NODE_MAX"
     is set to one more than this to make loops easier to read
     **/
    private int manyNodes;
    private IntNode front;
    private IntNode back;
    private IntNode cursor;

    /**
     * Splits the String "input" into nodes of 3 digits, each representing
     * a portion of the number (ex: "789456123" translates to 123,456,789 )
     * @param String input
     *     the number to be represented as the new UnboundedInt
     * @postcondition
     *     manyNodes in UnboundedInt will now represent the number
     *     of nodes in UnboundedInt
     **/
    public UnboundedInt(String input) {
        String temp;            //Used to temporarily hold a String containing 3 integers
        String formattedInput;    //Used to hold validated input (String length is divisible by 3)
        int count;              //Used to iterate through the checkedInput string

        formattedInput = inputStringVal(input);
        int nodesNeeded = formattedInput.length() / 3;
        manyNodes = 0;

        for (count = 0; count < nodesNeeded; count++) {
            temp = formattedInput.substring((count * 3), ((count * 3) + 3));
            manyNodes++;
            if (count == 0) {
                front = new IntNode(Integer.parseInt(temp), null);
                back = front;
            } else {
                front = new IntNode(Integer.parseInt(temp), front);
            }
        }
        start();
    }

    /**
     * Validates a portion of the UnboundedInt in the form of a string to correctly populate it with zeros
     *
     * @param input
     *     A string value that represents a portion of the UnboundedInt
     * @return
     *     A string that's been populated with zeros to accurately represent the numbers in UnboundedInt
     **/
    private String inputStringVal(String input) {
        int remainder = (input.length() % 3);

        if (remainder == 1) {
            return "00" + input;
        } else if (remainder == 2) {
            return "0" + input;
        } else {
            return input;
        }
    }

    /**
     * Advances the cursor to point to the next node within the list
     *
     * @param - none
     * @throws IllegalStateException
     *     Thrown if there is no linked node
     * @postcondition
     *     Cursor is now pointing to the next node
     **/
    public void advance() {
        if (cursor.getLink() == null) {
//            throw new IllegalStateException();
        } else {
            cursor = cursor.getLink();
        }
    }

    /**
     * Prints the UnboundedInt object as a string with commas and the correct
     * number of zero's
     *
     * @param - none
     * @throws IllegalStateException
     *     Thrown if the UnboundedInt is empty
     * @return
     *     A string representing the UnboundedInt
     **/
    public String toString() {
        String output = "";
        if (front == null) {
            throw new IllegalStateException("Cannot print when the sequence is empty");
        } else {
            start();
            for (int x = 0; x < manyNodes; x++) {
                if (x == 0) {
                    output = inputStringVal(new Integer(cursor.getData()).toString()) + output;
                    advance();
                } else {
                    output = x == manyNodes - 1
                            ? cursor.getData() + "," + output
                            : inputStringVal(new Integer(cursor.getData()).toString()) + "," + output;
                    advance();
                }
            }
        }
        return output;
    }

    /**
     * Places the cursor at the first node in the UnboundedInt
     * @param - none
     * @postcondition
     *     the cursor is located at the first node
     **/
    public void start() {
        this.cursor = front;
    }

    /**
     * Adds two UnboundedInts and returns the sum as a new UnboundedInt
     *
     * @param num2
     *     the UnboundedInt to be added to the object that called the method
     * @postcondition
     *     The total number of nodes in the UnboundedInt now represents the number
     *     of nodes in the UnboundedInt
     * @return
     *     An UnboudedInt representing the sum of the two numbers
     **/
    public UnboundedInt add(UnboundedInt num2) {
        int i = 0;
        int remainder = 0;
        String bigIntSum = "";

        start();
        num2.start();

        while (i < Math.max(manyNodes, num2.manyNodes)) {
            int sum = 0;
            String temp = "";
            Boolean num1HasNodeAtIndex = i < manyNodes;
            Boolean num2HasNodeAtIndex = i < num2.manyNodes;

            if (num1HasNodeAtIndex && num2HasNodeAtIndex) {
                sum = cursor.getData() + num2.cursor.getData() + remainder;
            } else if (num1HasNodeAtIndex && !num2HasNodeAtIndex) {
                sum = cursor.getData() + remainder;
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

            if (remainderPresent) {
                temp = sumStr.substring(1, 4);
                remainder = Integer.parseInt(sumStr.substring(0, 1));
            } else {
                temp = sumStr.substring(0, 3);
            }

            bigIntSum = temp + bigIntSum;

            advance();
            num2.advance();
            i++;
        }

        return new UnboundedInt(bigIntSum);
    }

    /**
     * Multiplies to UnboundedInt objects and stores the result in a new UnboundedInt
     *
     * @param input
     *     the UnboundedInt to be multiplied to the object that called the method
     * @postcondition
     *     The UnboundedInt objects that are multiplied together are unchanged
     * @return
     *     The product of the two UnboundedInt objects
     **/
    public void multiply(UnboundedInt input) {
        String temp = "";
        String bottomStr = input.toString().replace(",", "");
        String topStr = this.toString().replace(",", "");

        String bigSum = "";
        // bottom iterator
        for(int bottomIdx = bottomStr.length() - 1; bottomIdx >= 0; bottomIdx--) {
//            System.out.println("bottom: " + bottomStr.charAt((bottomIdx)));
            String smallSum = "";
            int remainder = 0;

            //for length of top
            for(int topIdx = topStr.length() - 1; topIdx >= 0; topIdx--) {
                //multiplying a node in the "bottom" to every node in the top,
                //and then iterating along the bottom by one and repeating
//                System.out.println("top: " + topStr.charAt(topIdx));
                int topNum = topStr.charAt(topIdx);
                int bottomNum = bottomStr.charAt(bottomIdx);

                int sum = (topNum + remainder) * bottomNum;

                remainder = 0;

                Integer sumObj = sum;
                String sumStr = sumObj.toString();
                Boolean remainderPresent = sumStr.length() > 1;

                if (remainderPresent) {
                    temp = sumStr.substring(1, 2);
                    remainder = Integer.parseInt(sumStr.substring(0, 1));
                } else {
                    temp = sumStr.substring(0, 1);
                }

                smallSum = temp + smallSum;
            }

            for (int i = bottomIdx; i > 0; i--) {
                smallSum += "0";
            }

            System.out.println(smallSum);
        }

//        return unbounded_Sum;

    }

    //A method to add a new element at the end of the sequence , used for building up each higher term in a single sequence.
    //(i.e. adding a new IntNode to the linked list)
    void addEnd(int item) {
        back.addNodeAfter(item);
        back = IntNode.listPosition(front, IntNode.listLength(front));
        manyNodes++;
    }

    /**
     * Equals method determines if two UnboundedInt objects are equal to one another
     *
     * @param obj
     *     a generic Object type object
     * @return
     *     a boolean representing whether if the two UnboundedInts are equal
     **/
    public boolean equals(Object obj) {
        if (obj instanceof UnboundedInt) {
            UnboundedInt input = (UnboundedInt) obj;
            if (manyNodes != input.manyNodes)
                return false;
            IntNode inputNode = input.front;
            for (IntNode current = front; current != null; current = current.getLink()) {
                if (current.getData() != inputNode.getData())
                    return false;
                inputNode = inputNode.getLink();
            }
        }
        return true;
    }

    /**
     * Clone method to clone an UnboundedInt object. Overrides Object clone() method
     *
     * @param - none
     * @throws CloneNotSupportedException
     *     Thrown if class does not implement Cloneable
     * @return
     *     A copy of this sequence
     **/
    public UnboundedInt clone() {
        UnboundedInt clonedNum;
        try {
            clonedNum = (UnboundedInt) super.clone();
        } catch (CloneNotSupportedException e) {
            // This exception should not occur. But if it does, it would probably
            // indicate a programming error that made super.clone unavailable.
            // The most common error would be forgetting the "Implements Cloneable"
            // clause at the start of this class.
            throw new RuntimeException("This class does not implement Cloneable");
        }
        clonedNum.front = IntNode.listCopy(front);
        return clonedNum;
    }

}