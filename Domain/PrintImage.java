
package Domain;

import Frames.LoadImage;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class PrintImage {
    
   
    
    
    
    public void chargedImage(Graphics g,String route){
        ImageIcon print= new ImageIcon(route);
        LoadImage image= new LoadImage();
        
        Icon icon = new ImageIcon(print.getImage().getScaledInstance(image.panel.getWidth(), image.panel.getHeight(), Image.SCALE_DEFAULT));
        g.drawImage(print.getImage(), 0, 0, 300,300,null);
    
    }

}
