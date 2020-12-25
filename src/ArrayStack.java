/**
 * Class: ArrayStack
 * Description: creates a stack that stores information of type T
 * @author jessica yang
 *
 * @param <T>
 */
public class ArrayStack<T> implements ArrayStackADT<T>{

	// variables needed 
	private T[] stack;
	private int top;
	private int initialCapacity;
	private int sizeIncrease;
	private int sizeDecrease;


	/**
	 * Constructor initializes needed variables and creates a stack array
	 * @param initialCap This will be set to the initial capacity of the array
	 * @param sizeInc This variable will be set to the amount of size increase
	 * @param sizeDec This variable will be set to the amount of size decrease
	 */
	public ArrayStack (int initialCap, int sizeInc, int sizeDec){
		top = -1;
		initialCapacity = initialCap;
		sizeIncrease = sizeInc;
		sizeDecrease = sizeDec;
		stack = (T[ ]) (new Object[initialCapacity]);

	}

	/**
	 * Push Method adds data items into the stack
	 */
	public void push(T dataItem) {

		// if the stack is full then creates a new stack of bigger size 
		if (size() == length()) {
			T[ ] stack2 = (T[ ]) (new Object[length() + sizeIncrease]);
			for (int i = 0; i < length(); i++)
				stack2[i] = stack[i];

			stack = stack2;
		}
		
		// adds the data item to the stack and increases the top data item position
		top = top + 1;
		stack[top] = dataItem;
	}

	/**
	 * Pop Method removes the most recently added item from the stack
	 */
	public T pop() throws EmptyStackException {
		// if the stack is empty then throws an empty stack error
		if (top == -1) {
			throw new EmptyStackException("Error: Empty Stack");
		}
		// if the size is a fourth of the length of the array, and the length is bigger than intial length, then decrease the size of the array
		if (size() -1 < length()/4 && length()>initialCapacity) {
			T[ ] stack2 = (T[ ]) (new Object[length() - sizeDecrease]);
			for (int i = 0; i < size(); i++) {
				stack2[i] = stack[i];
			}
			stack = stack2;
		}
		
		// removes the item at the top of the stack and returns it
		T removeditem = stack [top];
		stack[top] = null;
		top --;
		return removeditem;
	}

	/**
	 * Peek method checks what item top of the stack is 
	 */
	public T peek() throws EmptyStackException {
		// if the stack is empty then throws an empty array exception
		if (top == -1) {
			throw new EmptyStackException("Error: Empty Stack");
		}
		return stack[top];
	}

	/**
	 * isEmpty method checks if the stack is empty
	 * @return This returns a boolean if the stack is empty or not
	 */
	public boolean isEmpty() {
		boolean empty = false;
		if (stack[top] == null) {
			empty = true;
		}
		return empty;
	}

	/**
	 * Size method returns size of stack
	 * @return this returns the size of the stack
	 */
	public int size() {
		return top+1;
	}
	
	/**
	 * Length method returns how many cells are in the stack
	 * @return this returns the length of the stack
	 */
	public int length() {
		return stack.length;
	}
	
	/**
	 * toString returns the stack in a neatly organized string format 
	 * @return This will return the string containing the organized string format
	 */
	public String toString() {
		String str = "Stack: ";
		// loops through the array list and prints out items one by one
		for (int i = 0; i < size(); i++) {
			// will print the item out with a comma after if it is not the last item
			if (i != top) {
				str += stack[i] + ", ";
			}
			else str += stack[i];
		}
		
		return str;
	}
}
