package fishtank;

import java.awt.*;

/**
 * A fish.
 */
public class FollowingFish extends FishTankEntity {

    /**
     * How this fish appears on the screen.
     */
    public String appearance;

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
    int y;
    /**
     * The colour of this fish.
     */
    private Color colour;

    /**
     * The entity that our fish is following
     */
    Fish de;


    /**
     * Constructs a new hungry fish.
     */
    public FollowingFish(Fish f) {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><FOLLOW>";
        goingRight = true;
        de = f;
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
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    private String reverseAppearance() {
        System.out.println("Turnign around" + this.appearance);
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
        System.out.println("Turned around" + this.appearance);
        appearance = reverse;
        return reverse;
    }


    /**
     * Turns this fish to fc
     */
    protected void turnToFace() {
        if (de.getX() < this.getX() && goingRight) {
            goingRight = false;
            reverseAppearance();
        } else if (de.getX() > this.getX() && !goingRight) {
            goingRight = true;
            reverseAppearance();
        } else if (de.getX() == this.getX()) {
            if (this.goingRight != de.goingRight) {
                this.goingRight = de.goingRight;
                this.reverseAppearance();
                System.out.println("Aligned");
            }
        }
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
    void draw(Graphics g) {
        drawString(g, appearance, x, y);
    }


    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {
        turnToFace();
    }

    public void followCheck() {
        if (Math.abs(de.getY() - y) > 2) {
            if (de.getY() < y && y - 1 > 0) {
                if (FishTank.getEntity(x, y - 1) == null) {
                    y -= 1;
                } else {
                    y -= 0;
                }
            } else {
                System.out.println("nope");
            }
            if (de.getY() > y && y + 1 < FishTank.getHeight() - 1) {
                if (FishTank.getEntity(x, y + 1) == null) {
                    y++;
                } else {
                    y += 0;
                }
            }
        }
        if (Math.abs(de.getY() - y) < 2) {
            if (de.getY() < y && y + 1 > 0) {
                if (FishTank.getEntity(x, y + 1) == null) {
                    y += 1;
                } else {
                    y += 0;
                }
            } else {
                System.out.println("nope");
            }
            if (de.getY() > y && y - 1 < FishTank.getHeight() - 1) {
                if (FishTank.getEntity(x, y - 1) == null) {
                    y--;
                } else {
                    y -= 0;
                }
            }
        }
        // Move one spot to the right or left.
        if (this.getX() != de.getX()) {
            if (goingRight) {
                if (x < FishTank.getWidth() - 1) {
                    if (FishTank.getEntity(x + 1, y) != null) {
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
                        x -= 0;
                    } else {
                        x -= 1;
                    }
                } else {
                    x -= 0;
                }
            }
            System.out.println(this.getX() - de.getX());
        }
    }


}
