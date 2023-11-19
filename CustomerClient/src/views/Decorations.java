package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public abstract class Decorations {
	
	Font os = new Font("Open Sans", Font.PLAIN, 14);
	Font rale = new Font("Raleway", Font.BOLD, 17);
	Font verdana = new Font("Verdana", Font.BOLD, 20);
	
	// Creates a border with a raised effect
    Border bevel = BorderFactory.createRaisedBevelBorder();

	// Creates a light blue color
	Color transCyan = new Color(173, 216, 230, 150);
	Color cyan = new Color(173, 216, 230);
	Color brown = new Color(120, 90, 40);

}
