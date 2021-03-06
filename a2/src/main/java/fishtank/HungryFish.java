package fishtank;

import java.awt.*;

/**
 * A fish.
 */
public class HungryFish extends FishTankEntity {

    /**
     * How this fish appears on the screen.
     */
    private String appearance;

    /**
     * Indicates whether this fish is moving right.
     */
    boolean goingRight;

    /**
     * This fish's first coordinate.
     */
    private int x;
    /**
     * This fish's second coordinate.
     */
    private int y;
    /**
     * The colour of this fish.
     */
    private Color colour;


    /**
     * Constructs a new hungry fish.
     */
    public HungryFish() {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><MEHUNGRY>";
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
        System.out.println("Turnign around" + this.appearance);
        String reverse = "";
        if (this.appearance.equals("><MEHUNGRY>")){
            reverse = "<YRGNUHEM><";
        }else if (this.appearance.equals("<YRGNUHEM><")){
            reverse = "><MEHUNGRY>";
        }
        System.out.println("Turned around" + this.appearance);
        appearance = reverse;
        return reverse;
    }


    /**
     * Turns this fish around, causing it to reverse direction.
     */
    protected void turnAround() {
        goingRight = !goingRight;
        if (goingRight) {
            appearance = reverseAppearance();
        } else {
            appearance = reverseAppearance();
        }
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    /**
     * The font used to draw instances of this class.
     */
    private static Font FONT = new Font("Monospaced", Font.PLAIN, 10);


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
    void draw(Graphics g) {
        drawString(g, appearance, x, y);
    }


    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {
        boolean turnedAround = false;
        // Move one spot to the right or left.
        if (goingRight) {
            if (x < FishTank.getWidth() - 1) {
                if (FishTank.getEntity(x + 1, y) != null) {
                    this.turnAround();
                    turnedAround = true;
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
                    turnedAround = true;
                } else {
                    x -= 1;
                }
            } else {
                x -= 0;
            }
        }

        // Figure out whether I blow a bubble.
        double d = Math.random();
        // If it's less than 10%, blow a bubble.
        if (d < 0.1) {
            blowBubble();
        }

        // Figure out whether I turn around.
        d = Math.random();
        // If it's less than 10%, turn around
        if (d < 0.1 && !turnedAround) {
            turnAround();
        }

        // Figure out whether to move up or down, or neither.
        d = Math.random();
        // If it's less than 10%, move up or down.
        if (d < 0.05) {
            // Increment
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
            // Decrement
            if (y > 0) {
                if (FishTank.getEntity(x, y - 1) != null) {
                    y -= 0;
                } else {
                    y -= 1;
                }
            } else {
                y -= 0;
            }
        }
        for (int i = 0; i < FishTank.getHeight(); i++) {
            FishTankEntity e = FishTank.getEntity(x, i);
            if (e instanceof Seaweed) {
                ((Seaweed) e).eatCheck(this.y);
            }
        }
    }

}

