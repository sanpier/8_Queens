import java.awt.*;

public interface DrawableObject 
{
	public void draw(Component c, Graphics g); 
	/*All classes that implement this interface
    must have draw method in this structure, 
	but body can be different.*/
}
