public class Main {
    public static void main(String[] args) {
        testItemAt();
    }

    public static void testItemAt() {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();

        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        linkedStack.push(4);
        linkedStack.push(5);

        Node<Integer> node = linkedStack.itemAt(4);

        System.out.println(node.getData());
    }
}