/**
 * Class: EmptyStackException
 * Description: throws the given string message when there is an empty stack
 * @author jessica yang
 *
 */
public class EmptyStackException extends RuntimeException{

	/**
	 * Constructor sends message to parent class RuntimeException
	 */
	public EmptyStackException(String message) {
		super(message);
	}
}
