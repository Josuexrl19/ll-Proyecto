package Domain;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Image {
    
    //attributes
    public static final int approvedWidth=1024;//maximum width; static final so that it is not modified
    public static final int approvedHeight=720;//maximum height; static final so that it is not modified
    private Color matrix [][];// array of colors RGB, pixels
    private int width;
    private int height;
    
    //builder
    public Image(String file) {
        
        //bring the colors and insert it into the matrix
        matrix = new Color[height][width];
        uploadImage(file);
        
    }// end of builder Image 
    
    public void uploadImage (String file) {
        
        BufferedImage bfImage = null;
        
        try {
            bfImage = ImageIO.read(new File(file));
        } catch (IOException ioe) {
            System.err.println("Error" + ioe);
        }// end of catch
        
        // size validation
        if (bfImage.getWidth()<approvedWidth) {
            width=bfImage.getWidth();
        }else{
            width=approvedWidth;
            System.err.println("Error, The image exceeds the width limits");
        }    
        if (bfImage.getHeight()<approvedHeight) {
            height=bfImage.getHeight();
        }else{
            height=approvedHeight;
            System.err.println("Error, The image exceeds the height limits");
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                
                //Assign the color
                matrix[i][j]= new Color(bfImage.getRGB(j, i));
                
            }// end of for
            
        }// end of for
        
    }// end of method uploadImage
    
    public BufferedImage resize (String file, double percent ) {
        
        BufferedImage bfImage = null;
        
        try {
            bfImage = ImageIO.read(new File(file));
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }// end of catch// end of catch// end of catch// end of catch
        
        int width = bfImage.getWidth();
        int height = bfImage.getHeight();
        int rankWidth = (int)(percent* width);
        int rankHeight = (int)(percent*height);
        
        BufferedImage bfI = new BufferedImage(rankWidth, rankHeight, bfImage.getType());
        Graphics2D g2D = bfI.createGraphics();
        
        g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2D.drawImage(bfImage, 0,0, rankWidth,rankHeight, 0,0,width,height, null);
        g2D.dispose();
        
        return bfI;
        
    }// end of method resize

    //print image
    public BufferedImage printImage(){
        
        BufferedImage bfImage_exit = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        for (int i = 0; i < height; i++) {
            
            for (int j = 0; j < width; j++) {
                //assign the colors
                bfImage_exit.setRGB(j, i, matrix[i][j].getRGB());
            }// end of for
            
        }// end of for
        
        return bfImage_exit;
        
    }// end of method printImage
    
}// end of class
