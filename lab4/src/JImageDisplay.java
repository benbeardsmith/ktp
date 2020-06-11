import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

public class JImageDisplay extends JComponent{
    private BufferedImage image;

    public JImageDisplay(int w, int h) {
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);//задаем размер
        Dimension imageDimension = new Dimension(w, h); // создаем
        super.setPreferredSize(imageDimension); //отображение
    }

    //отрисовка
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    //очистка
    public void clearImage(){
        for (int x=0;x<image.getWidth();x++)
            for (int y=0;y<image.getHeight();y++)
                image.setRGB(x,y,0);
    }

    //закрашивание 1 пикселя
    public void drawPixel(int x, int y, int rgbColor){
        image.setRGB(x,y,rgbColor);
    }
}
