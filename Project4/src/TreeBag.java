// Stubs edited by: James Ji, Samuel Acquaviva

// The implementation of most methods in this file is left as a student
// exercise from Section 9.5 of "Data Structures and Other Objects Using Java"

/******************************************************************************
* This class is a homework assignment;
* An <CODE>TreeBag</CODE> is a collection of int numbers.
*
* <dl><dt><b>Limitations:</b> <dd>
*   Beyond <CODE>Integer.MAX_VALUE</CODE> elements, <CODE>countOccurrences</CODE>,
*   and <CODE>size</CODE> are wrong. 
*
* <dt><b>Note:</b><dd>
*   This file contains only blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* @version
*   Jan 24, 2016
******************************************************************************/
public class TreeBag<E extends Comparable> implements Cloneable
{
   // The Term E extends Comparable is letting the compiler know that any type
   // used to instantiate E must implement Comparable. i.e. that means that whatever
   // type E is must have a compareTo method so that elements can be compared against one another
   // This is required because we are doing comparisons in our methods

   // Invariant of the TreeBag class:
   //   1. The elements in the bag are stored in a binary search tree.
   //   2. The instance variable root is a reference to the root of the
   //      binary search tree (or null for an empty tree).
   private BTNode<E> root;

   /**
   * Insert a new element into this bag.
   * @param element
   *   the new element that is being inserted
   * <dt><b>Postcondition:</b><dd>
   *   A new copy of the element has been added to this bag.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory a new BTNode.
   **/
   public void add(E element) {
      BTNode<E> cursor = root;
      boolean nodeAdded = false;
      BTNode<E> newNode = new BTNode<E>(element, null, null);

      // Handle null root (empty tree) case
      if (root == null){
         root = newNode;
      } else {
         while (!nodeAdded){
            if (element.compareTo(cursor.getData()) < 0){
               if (cursor.getLeft() == null) {
                  cursor.setLeft(newNode);
                  nodeAdded = true;
               }
               else {
                  cursor = cursor.getLeft();
               }
            }
            else {
               if (cursor.getRight() == null) {
                  cursor.setRight(newNode);
                  nodeAdded = true;
               }
               else {
                  cursor = cursor.getRight();
               }
            }
         }
      }

      cursor = root;
   }

   /**
   * Retrieve location of a specified element from this bag.
   * @param target
   *   the element to locate in the bag
   * @return 
   *  the return value is a reference to the found element in the tree
   * <dt><b>Postcondition:</b><dd>
   *   If <CODE>target</CODE> was found in the bag, then method returns
   *   a reference to a comparable element. If the target was not found then
   *   the method returns null.
   *   The bag remains unchanged.
   **/
   public E retrieve(E target) {
      boolean targetFound = false;
      BTNode<E> cursor = root;

      // query for target cursor and parent cursor of target cursor
      while (!targetFound) {
         // move to the appropriate node if cursor does not equal target num
         if (target.compareTo(cursor.getData()) != 0) {
            // check if we are going left or right
            if (target.compareTo(cursor.getData()) < 0) {
               // if left node is not null then move cursors and continue query
               // otherwise break because we've reached the end and the target num DNE
               if (cursor.getLeft() != null) {
                  cursor = cursor.getLeft();
               } else {
                  break;
               }
            } else {
               if (cursor.getRight() != null) {
                  cursor = cursor.getRight();
               } else {
                  break;
               }
            }
         } else {
            targetFound = true;
         }
      }

      return targetFound ? cursor.getData() : null;
   }

   
   /**
   * Remove one copy of a specified element from this bag.
   * @param target
   *   the element to remove from the bag
   * <dt><b>Postcondition:</b><dd>
   *   If <CODE>target</CODE> was found in the bag, then one copy of
   *   <CODE>target</CODE> has been removed and the method returns true. 
   *   Otherwise, the bag remains unchanged and the method returns false.
   **/
   public boolean remove(E target) {
      Object[] cursorArray = retrieveForRemoval(target);
      BTNode<E> cursor = (BTNode<E>) cursorArray[1];
      BTNode<E> parentOfCursor = (BTNode<E>) cursorArray[0];

      // if target was not found
      if (cursor == null){
         return false;
      }
      // if cursor is root and has no children
      else if (cursor == root && cursor.getLeft() == null && cursor.getRight() == null){
         root = null;
      }
      // if cursor is root and has a null left node
      else if (cursor == root && cursor.getLeft() == null){
         root = root.getRight();
      }
      // if cursor is root and has a null right node
      else if (cursor == root && cursor.getRight()== null){
         root = root.getLeft();
      }
      // if cursor has a left node but no right node
      else if (cursor.getRight() == null){
         if (cursor == parentOfCursor.getLeft()){
            parentOfCursor.setLeft(cursor.getLeft());
         }
         else if (cursor == parentOfCursor.getRight()){
            parentOfCursor.setRight(cursor.getLeft());
         }
      }
      // if cursor has a right node but no left node
      else if (cursor.getLeft() == null){
         if (cursor == parentOfCursor.getLeft()){
            parentOfCursor.setLeft(cursor.getRight());
         }
         else if (cursor == parentOfCursor.getRight()){
            parentOfCursor.setRight(cursor.getRight());
         }
      }
      // if the cursor has left and right children
      else {
         cursor.setData(cursor.getLeft().getRightmostData());
         cursor.setLeft(cursor.getLeft().removeRightmost());
      }

      return false;
   }

   /**
    * Gets a specified element and returns it so that
    * it can be removed from the bag later
    * @param target
    *    the element to be found in the bag
    * @return
    *    the element and its parent node
    **/
   private Object[] retrieveForRemoval(E target) {
      BTNode<E> cursor = root;
      BTNode<E> parentOfCursor = null;
      boolean done = false;
      Object[] btArray = new Object[2];

      while (cursor != null && !done){
         if (target.compareTo(cursor.getData()) == 0){
            done = true;
         }
         else if (target.compareTo(cursor.getData()) < 0){
            parentOfCursor = cursor;
            cursor = cursor.getLeft();
         }
         else if (target.compareTo(cursor.getData()) > 0){
            parentOfCursor = cursor;
            cursor = cursor.getRight();
         }
      }

      btArray[1] = cursor;
      btArray[0] = parentOfCursor;
      return btArray;
   }

   /**
   * Displays the entire tree of Node elements in a specified order
   * by the elements compareTo method
   * <dt><b>Postcondition:</b><dd>
   *   Outputs all elements in the tree to Screen.
   *   Does not change the structure
   **/
   public void display() {
      if (root == null)
         System.out.println("The database is empty. Nothing to print.");
      root.inorderPrint();
   }
       
   /**
   * Determine the number of elements in this bag.
   * @return
   *   the number of elements in this bag
   **/                           
   public int size() {
      return BTNode.treeSize(root);
   }

   public int countOccurrences(E target)
   {
      int count = 0;

      if (root != null){
         BTNode<E> cursor = root;

         while (cursor != null){
            if (target.equals(cursor.getData())){
               count++;
            }
            if (target.compareTo(cursor.getData()) <= 0){
               cursor = cursor.getLeft();
            }
            else {
               cursor = cursor.getRight();
            }
         }
      }
      return count;
   }
}
           
