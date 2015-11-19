/**
 * 
 */
package drawing;

/**
 * @author echchouik
 * 
 */
public interface Observer {

	// Observer avec méthode PUSH : c'est le sujet qui envoie l'état (paramètre value)
	public void update(int value);

}
