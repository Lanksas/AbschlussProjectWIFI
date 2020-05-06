package viewing.hintergrund;

import javax.swing.*;
import java.awt.*;

public class Hintergrund extends JPanel {
    private Image image;
    private boolean fitImage;

    public Hintergrund() {
        super();
    }

    public Hintergrund(LayoutManager layout){
        super(layout);
    }

    public void setImage(Image image){
        this.setImage(image, false);
    }

    public void setImage(Image image, boolean fitImage){
        this.image = image;
        this.fitImage = fitImage;
        validate();
        repaint();
    }

    @Override
    public Dimension getPreferredSize(){
        if(image != null){
            return new Dimension(image.getWidth(this), image.getHeight(this));
        }
        return super.getPreferredSize();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(image != null) {
            if(fitImage) {
                Dimension size = this.getSize();
                g.drawImage(image,0,0, size.width, size.height,this);
            } else {
                g.drawImage(image, 0,0,this);
            }
        }
    }
}
