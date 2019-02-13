package fishtank;

import java.awt.*;

/**
 * A fish.
 */
public class Fish extends FishTankEntity {

    /**
     * How this fish appears on the screen.
     */
    String appearance;

    /**
     * Indicates whether this fish is moving right.
     */
    boolean goingRight;

    /**
     * This fish's first coordinate.
     */
    int x;
    /**
     * This fish's second coordinate.
     */
    private int y;
    /**
     * The colour of this fish.
     */
    Color colour;


    /**
     * Constructs a new fish.
     */
    public Fish() {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><>";
        goingRight = true;
    }


    /**
     * Set this item's location.
     *
     * @param a the first coordinate.
     * @param b the second coordinate.
     */
    public void setLocation(int a, int b) {
        x = a;
        y = b;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }


    /**
     * Causes this fish to blow a bubble.
     */
    protected void blowBubble() {
        Bubble b = new Bubble();
        b.setLocation(x, y);
        System.out.println(x + " " + y);

        FishTank.addEntity(x, y, b);
    }


    /**
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    private String reverseAppearance() {
        String reverse = "";
        for (int i = appearance.length() - 1; i >= 0; i--) {
            switch (appearance.charAt(i)) {
                case ')':
                    reverse += '(';
                    break;
                case '(':
                    reverse += ')';
                    break;
                case '>':
                    reverse += '<';
                    break;
                case '<':
                    reverse += '>';
                    break;
                case '}':
                    reverse += '{';
                    break;
                case '{':
                    reverse += '}';
                    break;
                case '[':
                    reverse += ']';
                    break;
                case ']':
                    reverse += '[';
                    break;
                default:
                    reverse += appearance.charAt(i);
                    break;
            }
        }

        return reverse;
    }


    /**
     * Turns this fish around, causing it to reverse direction.
     */
    protected void turnAround() {
        this.goingRight = !this.goingRight;
        appearance = reverseAppearance();
    }


    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    /**
     * The font used to draw instances of this class.
     */
    static Font FONT = new Font("Monospaced", Font.PLAIN, 10);


    /**
     * Draws the given string in the given graphics context at
     * at the given cursor location.
     *
     * @param g the graphics context in which to draw the string.
     * @param s the string to draw.
     * @param x the x-coordinate of the string's cursor location.
     * @param y the y-coordinate of the string's cursor location.
     */
    void drawString(Graphics g, String s, int x, int y) {
        g.setColor(colour);
        g.setFont(FONT);
        FontMetrics fm = g.getFontMetrics(FONT);
        g.drawString(s, x * fm.charWidth('W'), y * fm.getAscent());
    }


    /**
     * Draws this fish tank item.
     *
     * @param g the graphics context in which to draw this item.
     */
    public void draw(Graphics g) {
        drawString(g, appearance, x, y);
    }


    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {

        // Move one spot to the right or left.
        if (goingRight) {
            if (x < FishTank.getWidth() - 1) {
                if (FishTank.getEntity(x + 1, y) != null) {
                    this.turnAround();
                    x += 0;
                } else {
                    x += 1;
                }
            } else {
                x += 0;
            }
        } else {
            if (x > 0) {
                if (FishTank.getEntity(x - 1, y) != null) {
                    this.turnAround();
                    x -= 0;

                } else {
                    x -= 1;
                }
            } else {
                x -= 0;
            }
        }

        // Figure out whether I blow a bubble.
        double d = Math.random();
        if (d < 0.1) {
            blowBubble();
        }

        // Figure out whether I turn around.
        d = Math.random();
        if (d < 0.1) {
            turnAround();
        }

        // Figure out whether to move up or down, or neither.

        d = Math.random();
        if (d < 0.05) {
            if (y < FishTank.getHeight() - 1) {
                if (FishTank.getEntity(x, y + 1) != null) {
                    y += 0;
                } else {
                    y += 1;
                }
            } else {
                y += 0;
            }
        }
        if (d > 0.95) {
            if (y > 1) {
                if (FishTank.getEntity(x, y - 1) != null) {
                    y -= 0;
                } else {
                    y -= 1;
                }
            } else {
                y -= 0;
            }
        }

        for (int i = 0;i < FishTank.getHeight(); i++){
            FishTankEntity e = FishTank.getEntity(x,i);
            if(e instanceof Seaweed){
                 ((Seaweed) e).eatCheck(this.y);
            }
        }

    }
}
