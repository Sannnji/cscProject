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
    public UnboundedInt(String input){
        manyNodes = input.length();
        String temp;
        int tempValue, count;

        temp = input.substring(0, 2);
        tempValue = Integer.parseInt(temp);

        front(tempValue);

        cursor = front;

        for(int x = 3; x < manyNodes; x += 3){
            temp = input.substring(x, (x + 3));
            tempValue = Integer.parseInt(temp);
            cursor.addNodeAfter(tempValue);
            cursor = cursor.listPosition(front, count);
        }

        back = cursor;
        start();
    }

    //move the cursor along the list
    //Throws an IllegalStateException if the cursor is null
    public void advance(){
        try{
            if(cursor.getLink() == null){
                throw new IllegalStateException();
            }else{
                cursor = cursor.getLink();
            }
        }catch IllegalStateException();
    }

    //a method that returns the integer value of the Node that is pointed to by the cursor.
    //Throws an IllegalStateException if the cursor is not pointing to a Node
    public int getNodeValue(){
        try{
            if(cursor.getData() == null){
                throw new IllegalStateException();
            }else{
                return cursor.getData();
            }
        }catch IllegalStateException();
    }

    //Same as toString but no commas in string.
    public String toStringNoCommas(){

    }

    //Creates a string of all elements in order separated by commas,
    //making sure leading zeros are added when needed.  (i.e.  12,005,016 or  34,000 )
    //Throw an IllegalStateException if the sequence is empty
    public String toString(){
        String output = "";

        try{
            if(front.getData() == null){
                throw new IllegalStateException();
            }else{
                start();
                output += cursor.getData() + ",";
                advance();
            }
        }catch IllegalStateException();

        return output;
    }

    //set the cursor to the front of the list
    public void start(){
        this.cursor = front;
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