// Contributors: James Ji, Samuel Acquaviva
// File: DoubleArraySeq.java

// This is an assignment for students to complete after reading Chapter 3 of
// "Data Structures and Other Objects Using Java" by Michael Main.


/******************************************************************************
 * This class is a homework assignment;
 * A DoubleArraySeq is a collection of double numbers.
 * The sequence can have a special "current element," which is specified and
 * accessed through four methods that are not available in the bag class
 * (start, getCurrent, advance and isCurrent).
 *
 * @note
 *   (1) The capacity of one a sequence can change after it's created, but
 *   the maximum capacity is limited by the amount of free memory on the
 *   machine. The constructor, addAfter,
 *   addBefore, clone,
 *   and concatenation will result in an
 *   OutOfMemoryError when free memory is exhausted.
 *   <p>
 *   (2) A sequence's capacity cannot exceed the maximum integer 2,147,483,647
 *   (Integer.MAX_VALUE). Any attempt to create a larger capacity
 *   results in a failure due to an arithmetic overflow.
 *
 * @note
 *   This file contains only blank implementations ("stubs")
 *   because this is a Programming Project for my students.
 *
 * @see
 *   <A HREF="../../../../edu/colorado/collections/DoubleArraySeq.java">
 *   Java Source Code for this class
 *   (www.cs.colorado.edu/~main/edu/colorado/collections/DoubleArraySeq.java)
 *   </A>
 *
 * @version
 *   March 5, 2002
 ******************************************************************************/
public class DoubleArraySeq implements Cloneable {
    // Invariant of the DoubleArraySeq class:
    //   1. The number of elements in the sequences is in the instance variable
    //      manyItems.
    //   2. For an empty sequence (with no elements), we do not care what is
    //      stored in any of data; for a non-empty sequence, the elements of the
    //      sequence are stored in data[0] through data[manyItems-1], and we
    //      don�t care what�s in the rest of data.
    //   3. If there is a current element, then it lies in data[currentIndex];
    //      if there is no current element, then currentIndex equals manyItems.
    private double[] data;
    private int manyItems;
    private int currentIndex;

    /**
     * Initialize an empty sequence with an initial capacity of 10.  Note that
     * the addAfter and addBefore methods work
     * efficiently (without needing more memory) until this capacity is reached.
     *
     * @param - none
     * @throws OutOfMemoryError Indicates insufficient memory for:
     *                          new double[10].
     * @postcondition This sequence is empty and has an initial capacity of 10.
     **/
    public DoubleArraySeq() {
        // Implemented by student.
        data = new double[10];
        manyItems = 0;
        currentIndex = 0;
    }


    /**
     * Initialize an empty sequence with a specified initial capacity. Note that
     * the addAfter and addBefore methods work
     * efficiently (without needing more memory) until this capacity is reached.
     *
     * @param initialCapacity the initial capacity of this sequence
     * @throws IllegalArgumentException Indicates that initialCapacity is negative.
     * @throws OutOfMemoryError         Indicates insufficient memory for:
     *                                  new double[initialCapacity].
     * @precondition initialCapacity is non-negative.
     * @postcondition This sequence is empty and has the given initial capacity.
     **/
    public DoubleArraySeq(int initialCapacity) {
        // Implemented by student.
        data = new double[initialCapacity];
        manyItems = 0;
        currentIndex = 0;
    }

    /**
     * Add a new element to this sequence, after the current element.
     * If the new element would take this sequence beyond its current capacity,
     * then the capacity is increased before adding the new element.
     *
     * @param element the new element that is being added
     * @throws OutOfMemoryError Indicates insufficient memory for increasing the sequence's capacity.
     * @postcondition A new copy of the element has been added to this sequence. If there was
     * a current element, then the new element is placed after the current
     * element. If there was no current element, then the new element is placed
     * at the end of the sequence. In all cases, the new element becomes the
     * new current element of this sequence.
     * @note An attempt to increase the capacity beyond
     * Integer.MAX_VALUE will cause the sequence to fail with an
     * arithmetic overflow.
     **/
    public void addAfter(double element) {
        // if sequence is at capacity increase size
        if (manyItems == data.length || manyItems == data.length - 1) {
            ensureCapacity((data.length * 2) + 1);
        }

        if (currentIndex < manyItems - 1) {
            System.arraycopy(data, currentIndex + 1, data, currentIndex + 2, manyItems - currentIndex);
        }
        // add to first index if there are no elements otherwise add to the next index from the current
        int targetIndex = size() == 0 ? 0 : currentIndex + 1;
        data[targetIndex] = element;

        manyItems++;
        // don't increment after adding first element because we are adding to the current index
        if (size() != 1) {
            currentIndex++;
        }
        System.out.println("index " + currentIndex);
    }


    /**
     * Add a new element to this sequence, before the current element.
     * If the new element would take this sequence beyond its current capacity,
     * then the capacity is increased before adding the new element.
     *
     * @param element the new element that is being added
     * @throws OutOfMemoryError Indicates insufficient memory for increasing the sequence's capacity.
     * @postcondition A new copy of the element has been added to this sequence. If there was
     * a current element, then the new element is placed before the current
     * element. If there was no current element, then the new element is placed
     * at the start of the sequence. In all cases, the new element becomes the
     * new current element of this sequence.
     * @note An attempt to increase the capacity beyond
     * Integer.MAX_VALUE will cause the sequence to fail with an
     * arithmetic overflow.
     **/
    public void addBefore(double element) {
        // if sequence is at capacity increase size
        if (manyItems == data.length || manyItems == data.length - 1) {
            ensureCapacity((data.length * 2) + 1);
        }

        // always shift array if there are elements in the sequence since we are adding before an element
        if (manyItems != 0)
            System.arraycopy(data, currentIndex, data, currentIndex + 1, manyItems - currentIndex);

        data[currentIndex] = element;

        manyItems++;
        if (currentIndex != 0) {
            currentIndex--;
        }
    }


    /**
     * Place the contents of another sequence at the end of this sequence.
     *
     * @param addend a sequence whose contents will be placed at the end of this sequence
     * @throws NullPointerException Indicates that addend is null.
     * @throws OutOfMemoryError     Indicates insufficient memory to increase the size of this sequence.
     * @precondition The parameter, addend, is not null.
     * @postcondition The elements from addend have been placed at the end of
     * this sequence. The current element of this sequence remains where it
     * was, and the addend is also unchanged.
     * @note An attempt to increase the capacity beyond
     * Integer.MAX_VALUE will cause an arithmetic overflow
     * that will cause the sequence to fail.
     **/
    public void addAll(DoubleArraySeq addend) {
        if (manyItems + addend.manyItems > data.length) {
            ensureCapacity(manyItems + addend.manyItems);
        }
        System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems);
        manyItems += addend.manyItems;
    }


    /**
     * Move forward, so that the current element is now the next element in
     * this sequence.
     *
     * @param - none
     * @throws IllegalStateException Indicates that there is no current element, so
     *                               advance may not be called.
     * @precondition isCurrent() returns true.
     * @postcondition If the current element was already the end element of this sequence
     * (with nothing after it), then there is no longer any current element.
     * Otherwise, the new element is the element immediately after the
     * original current element.
     **/
    public void advance() {
        try {
            if (currentIndex == manyItems) {
                throw new IllegalStateException("There is currently no element, no point in advancing any further");
            }
            currentIndex++;
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

    }


    /**
     * Generate a copy of this sequence.
     *
     * @param - none
     * @return The return value is a copy of this sequence. Subsequent changes to the
     * copy will not affect the original, nor vice versa.
     * @throws OutOfMemoryError Indicates insufficient memory for creating the clone.
     **/
    public DoubleArraySeq clone() {
        // Clone a DoubleArraySeq object.
        DoubleArraySeq answer;
        try {
            answer = (DoubleArraySeq) super.clone();
        } catch (CloneNotSupportedException e) {
            // This exception should not occur. But if it does, it would probably
            // indicate a programming error that made super.clone unavailable.
            // The most common error would be forgetting the "Implements Cloneable"
            // clause at the start of this class.
            throw new RuntimeException("This class does not implement Cloneable");
        }
        answer.data = data.clone();
        return answer;
    }


    /**
     * Create a new sequence that contains all the elements from one sequence
     * followed by another.
     *
     * @param s1 the first of two sequences
     * @param s2 the second of two sequences
     * @return a new sequence that has the elements of s1 followed by the
     * elements of s2 (with no current element)
     * //     * @exception NullPointerException.
     * Indicates that one of the arguments is null.
     * //     * @exception OutOfMemoryError
     * Indicates insufficient memory for the new sequence.
     * @precondition Neither s1 nor s2 is null.
     * @note An attempt to create a sequence with a capacity beyond
     * Integer.MAX_VALUE will cause an arithmetic overflow
     * that will cause the sequence to fail.
     **/
    public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2) {
        DoubleArraySeq newSeq = new DoubleArraySeq(0);
        newSeq.addAll(s1);
        newSeq.addAll(s2);
        return newSeq;
    }


    /**
     * Change the current capacity of this sequence.
     *
     * @param minimumCapacity the new capacity for this sequence
     * @throws OutOfMemoryError Indicates insufficient memory for: new int[minimumCapacity].
     * @postcondition This sequence's capacity has been changed to at least minimumCapacity.
     * If the capacity was already at or greater than minimumCapacity,
     * then the capacity is left unchanged.
     **/
    public void ensureCapacity(int minimumCapacity) {
        double[] biggerSequence;
        if (data.length < minimumCapacity) {
            biggerSequence = new double[minimumCapacity];
            System.arraycopy(data, 0, biggerSequence, 0, data.length);
            data = biggerSequence;
        }
    }


    /**
     * Accessor method to get the current capacity of this sequence.
     * The add method works efficiently (without needing
     * more memory) until this capacity is reached.
     *
     * @param - none
     * @return the current capacity of this sequence
     **/
    public int getCapacity() {
        return data.length;
    }


    /**
     * Accessor method to get the current element of this sequence.
     *
     * @param - none
     * @return the current element of this sequence
     * @throws IllegalStateException Indicates that there is no current element, so
     *                               getCurrent may not be called.
     * @precondition isCurrent() returns true.
     **/
    public double getCurrent() {
        double answer = 0;
        try {
            if (currentIndex == manyItems) {
                throw new IllegalStateException("There is no current element");
            }
            answer = data[currentIndex];
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        return answer;
    }


    /**
     * Accessor method to determine whether this sequence has a specified
     * current element that can be retrieved with the
     * getCurrent method.
     *
     * @param - none
     * @return true (there is a current element) or false (there is no current element at the moment)
     **/
    public boolean isCurrent() {
        try {
            getCurrent();
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Remove the current element from this sequence.
     *
     * @param - none
     * @throws IllegalStateException Indicates that there is no current element, so
     *                               removeCurrent may not be called.
     * @precondition isCurrent() returns true.
     * @postcondition The current element has been removed from this sequence, and the
     * following element (if there is one) is now the new current element.
     * If there was no following element, then there is now no current
     * element.
     **/
    public void removeCurrent() {
        try {
            if (!isCurrent()) {
                throw new IllegalStateException("There no current element, nothing to remove");
            }

            if (data.length != 0) {
                data[currentIndex] = 0;
                currentIndex = currentIndex + 1;
            } else {
                data[currentIndex] = 0;
            }
            manyItems--;
        } catch (Exception e) {
            e.getMessage();
        }

    }


    /**
     * Determine the number of elements in this sequence.
     *
     * @param - none
     * @return the number of elements in this sequence
     **/
    public int size() {
        return manyItems;
    }


    /**
     * Set the current element at the front of this sequence.
     *
     * @param - none
     * @postcondition The front element of this sequence is now the current element (but
     * if this sequence has no elements at all, then there is no current
     * element).
     **/
    public void start() {
        currentIndex = 0;
    }

    /**
     * Adds the passed in element to the front of the array and sets
     * the current element to index 0 of the array
     *
     * @param element The element to be added to the front of the array.
     * @postcondition There will be a new element in the front of the sequence and will also now
     * be the current element
     **/
    public void addFront(double element) {
        // Create new temp array, 1 larger than the original ( I NEED TO CHECK THE SIZE BEFORE I DO THIS)
        double[] newData = new double[this.manyItems + 1];

        // Set index 0 of the temp array to the value of the passed in element, then copy the original array to the temp array
        newData[0] = element;
        System.arraycopy(data, 0, newData, 1, this.manyItems);

        //Set the original array equal to the value of the updated temp array
        data = newData;
        manyItems++;

        start();
    }

    public void removeFront() {
        if (manyItems == 0) {
            throw new IllegalStateException("Nothing to remove, sequence is empty...");
        } else if (manyItems == 1) {
            manyItems--;
            data[0] = manyItems;
        } else {
            data[0] = data[manyItems - 1];
            manyItems--;
        }
    }

    public void setCurrent(int n) {
        if (data.length == 0) {
            throw new IllegalArgumentException("There is nothing to print. The sequence is empty...");
        } else if (n - 1 > manyItems) {
            throw new IllegalStateException("Cannot setCurrent() to a invalid position in sequence...");
        } else {
            currentIndex = n - 1;
        }
    }

    public double getElement(int n) {
        double answer = 0;
        try {
            if (data.length == 0) {
                throw new IllegalArgumentException("There is nothing to print. The sequence is empty...");
            }
            if (n - 1 > manyItems || n - 1 < 0) {
                throw new IllegalStateException("Cannot setCurrent() to a invalid position in sequence...");
            }
            setCurrent(n);
            answer = getCurrent();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        return answer;
    }

    /**
     * A method to compare two DoubleArraySeq objects and determine if they are equivalent.
     * @param obj
     *   The sequence that is being compared to the current sequence.
     * @postcondition
     *   If the sequences being compared are equivalent, then equals will return true. Otherwise, equals will return false.
     **/
    @Override
    public boolean equals(Object obj) {
        boolean result = true;
        if (obj instanceof DoubleArraySeq) {
            DoubleArraySeq input = (DoubleArraySeq) obj;
            if ( manyItems == input.manyItems ) {
                for (int i = 0; i < manyItems; i++) {
                    if (data[i] != input.data[i]) {
                        result = false;
                    }
                }
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * A method to print all elements of the sequence in order, separated by a space.
     *
     * @param - none
     * @throws IllegalStateException Indicates that the sequence is empty.
     * @precondition The sequence is not empty.
     * @postcondition The elements of this sequence have been printed in order, separated by a space.
     **/
    public String toString() {
        String output = "";
        int savedIndexState = currentIndex;

        try {
            if (size() == 0) {
                throw new IllegalStateException("Cannot toString, sequence is empty");
            }
            this.start();
            for (int i = 0; i < manyItems; i++) {
                output += this.getCurrent() + " ";
                this.advance();
            }

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        currentIndex = savedIndexState;
        return output;
    }


    /**
     * Reduce the current capacity of this sequence to its actual size (i.e., the
     * number of elements it contains).
     *
     * @param - none
     * @throws OutOfMemoryError Indicates insufficient memory for altering the capacity.
     * @postcondition This sequence's capacity has been changed to its current size.
     **/
    public void trimToSize() {
        double[] trimmedArray;

        if (data.length != manyItems) {
            trimmedArray = new double[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }
}


