package fishtank;
import java.awt.*;

/**
 * A bubble.
 */
public class Bubble extends FishTankEntity {

    /** How this bubble appears on the screen. */
    private String appearance;

    /** The font used to draw instances of this class. */
    static Font FONT = new Font("Monospaced", Font.PLAIN, 10);

    /** My colour. Ah, the vagaries of British vs. US spelling. */
    Color colour;

    /** Use for random movement left and right.  */
    public double d;

    /** This bubble's first coordinate. */
    int x;
    /** This bubble's second coordinate. */
    protected int y;

    /**
     * Constructs a new bubble at the specified cursor location (x, y).
     */
    public Bubble() {
        // Get a nice-looking grey for the bubble
         colour = Color.gray.darker().darker().darker();
         // start off with . as the appearance
        appearance = ".";
    }

    /**
     * Set this item's location.
     * @param a the first coordinate.
     * @param b  the second coordinate.
     */
    public void setLocation(int a, int b) {
        // set x to a
      x = a;
        // set y to b
      y = b;
    }

    @Override
    int getX() {
        return x;
    }

    @Override
    int getY() {
        return y;
    }

    /**
     * Draws the given string in the given graphics context at
     * at the given cursor location.
     *
     * @param  g  the graphics context in which to draw the string.
     * @param  s  the string to draw.
     * @param  x  the x-coordinate of the string's cursor location.
     * @param  y  the y-coordinate of the string's cursor location.
     */
    void drawString(Graphics g, String s, int x, int y) {
        g.setColor(colour);
        g.setFont(FONT);
        FontMetrics fm = g.getFontMetrics(FONT);
        g.drawString(s, x*fm.charWidth('W'), y*fm.getAscent());
    }


    /**
     * Draws this fish tank item.
     *
     * @param  g  the graphics context in which to draw this item.
     */
    void draw(Graphics g) {
        drawString(g, appearance, y, x);
    }



    /**
     * Causes this item to take its turn in the fish-tank simulation, moving straight up.
     */
    public void floatStraightUp() {

        // Move upwards.
        if (y > 0) {
            if (FishTank.getEntity(x, y - 1) != null) {
                y -= 0;
            } else {
                y -= 1;
            }
        }
        x += 0; // no change left or right

        // Figure out whether to grow, if at all.
        d = Math.random();
          // Oocasinally change a . to a o or a o to a O
        if (d < 0.05) {
            // If the appearance is a ., change it to an o
            if (appearance.equals("."))appearance="o";
            // If the appearance is an o, change it to a O
            else if (appearance.equals("o"))appearance="O";
        }
    }    /**
     * Causes this item to take its turn in the fish-tank simulation, moving up and left.
     */
    public void floatLeftUp() {

        // Move upwards.
        if (y>0){
            if (FishTank.getEntity(x, y - 1) != null){
                y -= 0;
            }else{
                y-=1;
            }
        }else{
            y-= 0;
        }
        if (x > 0){
            if (FishTank.getEntity(x - 1, y) != null){
                x -= 0;
            }else{
                x-=1;
            }
        }else{
            x -= 0;
        }

        // Figure out whether to grow, if at all.
          d = Math.random();
          // Oocasinally change a . to a o or a o to a O
        if (d < 0.05) {
            // If the appearance is a ., change it to an o
            if (appearance.equals(".")) appearance = "o";
            // If the appearance is an o, change it to a O
            else if (appearance.equals("o")) appearance = "O";
        }
    }
    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void floatRightUp() {

        // Move upwards.
        if (y > 0){
            if (FishTank.getEntity(x, y - 1) != null){
                y -= 0;
            }else{
                y-=1;
            }
        }else{
            y-=0;
        }
        if (x < FishTank.getWidth() - 1){
            if (FishTank.getEntity(x+1, y) != null){
                x += 0;
            }else{
                x += 1;
            }// right
        }else{
            x += 0;
        }
        // Figure out whether to grow, if at all.
        d = Math.random();
          // Occasionally change a . to a o or a o to a O
        if (d < 0.05) {
            // If the appearance is a ., change it to an o
            if (appearance.equals("."))appearance="o";
            // If the appearance is an o, change it to a O
            else if (appearance.equals("o"))appearance="O";
        }
    }

    public void update() {
        if (y == 0){
            this.delete();
        }else{
            d = Math.random();
            if (d < 0.33) {
                floatStraightUp();
            } else if (d < 0.66) {
                floatRightUp();
            } else /* heybub.d >= 0.66 */ {
                floatLeftUp();
            }
        }
    }
}
