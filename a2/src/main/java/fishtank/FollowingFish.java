package fishtank;

import java.awt.*;

/**
 * A fish.
 */
public class FollowingFish extends FishTankEntity {

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
     * The entity that our fish is following
     */
    private Fish de;
    private boolean moved = false;

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
        System.out.println("Turning around" + this.appearance);
        String reverse = "";
        if (this.appearance.equals("><FOLLOW>")){
            reverse = "<WOLLOF><";
        }else if (this.appearance.equals("<WOLLOF><")){
            reverse ="><FOLLOW>";
        }
        System.out.println("Turned around" + this.appearance);
        return reverse;
    }


    /**
     * Turns this fish to fc
     */
    protected void turnToFace() {
        if (de.getX() < this.getX() && goingRight) {
            goingRight = false;
            this.appearance = reverseAppearance();
        } else if (de.getX() > this.getX() && !goingRight) {
            goingRight = true;
            this.appearance = reverseAppearance();
        } else if (de.getX() == this.getX()) {
            if (this.goingRight != de.goingRight) {
                this.goingRight = de.goingRight;
                this.appearance = this.reverseAppearance();
            }
        }
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
        turnToFace();
        if (y == 0 || y == FishTank.getHeight() - 1) {
            this.moveOnBound();
        } else {
            this.unboundMove();
        }
        this.moveX();
        this.moved = false;
    }

    private void unboundMove() {
        if (Math.abs(de.getY() - y) > 2) {
            if (de.getY() <= y && y - 1 >= 0) {
                if (FishTank.getEntity(x, y - 1) == null) {
                    y -= 1;
                } else {
                    if (this.getX() != de.getX()) {
                        if (goingRight) {
                            if (x < FishTank.getWidth() - 1) {
                                if (FishTank.getEntity(x + 1, y) != null) {
                                    x += 0;
                                } else {
                                    x += 1;
                                    this.moved = true;
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
                                    this.moved = true;
                                }
                            } else {
                                x -= 0;
                            }
                        }
                    }
                }
            }
            if (de.getY() > y && y + 1 <= FishTank.getHeight() - 1) {
                if (FishTank.getEntity(x, y + 1) == null) {
                    y++;
                } else {
                    if (this.getX() != de.getX()) {
                        if (goingRight) {
                            if (x < FishTank.getWidth() - 1) {
                                if (FishTank.getEntity(x + 1, y) != null) {
                                    x += 0;
                                } else {
                                    x += 1;
                                    this.moved = true;
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
                                    this.moved = true;
                                }
                            } else {
                                x -= 0;
                            }
                        }
                    }
                }
            }
        }
        if (Math.abs(de.getY() - y) < 2) {
            if (de.getY() <= y && y + 1 <= FishTank.getHeight() - 1) {
                if (FishTank.getEntity(x, y + 1) == null) {
                    y += 1;
                } else {
                    if (FishTank.getEntity(x + 1, y) == null && x + 1 < FishTank.getWidth() - 1) {
                        if (goingRight) {
                            x += 1;
                            this.moved = true;
                        } else {
                            goingRight = true;
                            reverseAppearance();
                            x += 1;
                            this.moved = true;
                        }
                    } else if (FishTank.getEntity(x - 1, y) == null && x - 1 > 0) {
                        if (!goingRight) {
                            x -= 1;
                            this.moved = true;
                        } else {
                            goingRight = false;
                            reverseAppearance();
                            x -= 1;
                            this.moved = true;
                        }
                    } else {
                        x += 0;
                    }
                }
            }


            if (de.getY() > y && y - 1 >= 0) {
                if (FishTank.getEntity(x, y - 1) == null) {
                    y -= 1;
                } else {
                    if (FishTank.getEntity(x + 1, y) == null && x + 1 < FishTank.getWidth() - 1) {
                        if (goingRight) {
                            x += 1;
                            this.moved = true;
                        } else {
                            goingRight = !goingRight;
                            reverseAppearance();
                            x += 1;
                            this.moved = true;
                        }
                    } else if (FishTank.getEntity(x - 1, y) == null && x - 1 > 0) {
                        if (!goingRight) {
                            x -= 1;
                            this.moved = true;
                        } else {
                            goingRight = !goingRight;
                            reverseAppearance();
                            x -= 1;
                            this.moved = true;
                        }
                    } else {
                        x += 0;
                    }
                }
            }

        }
    }

    private void moveOnBound() {
        if(Math.abs(getY() - de.getY())+ Math.abs(getX() - de.getX()) == 2){
            if(getY() == de.getY()&& getY() ==0){
                if (FishTank.getEntity(getX(),getY()+1) == null){
                    y++;
                }else if ((getY() == de.getY()&& getY() ==FishTank.getHeight() - 1)){
                    if (FishTank.getEntity(getX(),getY()-1) == null) {
                        y--;
                    }
                }
            }else{
                moved = true;
            }
        }else {
            if (Math.abs(de.getY() - this.getY()) < 2) {
                if (de.getY() <= this.getY()) {
                    if (goingRight) {
                        if (x < FishTank.getWidth() - 1) {
                            if (FishTank.getEntity(x + 1, y) != null) {
                                x += 0;
                            } else {
                                x += 1;
                                this.moved = true;
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
                                this.moved = true;
                            }
                        } else {
                            x -= 0;
                        }
                    }
                }
                if (de.getY() > this.getY()) {
                    if (goingRight) {
                        if (x < FishTank.getWidth() - 1) {
                            if (FishTank.getEntity(x + 1, y) != null) {
                                x += 0;
                            } else {
                                x += 1;
                                this.moved = true;
                            }
                        } else {
                            x += 0;
                            this.moved = true;
                        }
                    } else {
                        if (x > 0) {
                            if (FishTank.getEntity(x - 1, y) != null) {
                                x -= 0;
                            } else {
                                x -= 1;
                                this.moved = true;
                            }
                        } else {
                            x -= 0;
                            this.moved = true;
                        }
                    }

                }
            }
            if (Math.abs(de.getY() - this.getY()) > 2) {
                if (de.getY() >= this.getY()) {
                    if (FishTank.getEntity(x, y + 1) == null) {
                        y += 1;
                    } else {
                        if (FishTank.getEntity(x + 1, y) == null && x + 1 < FishTank.getWidth() - 1) {
                            if (goingRight) {
                                if (Math.abs(this.getY() - de.getY()) + Math.abs(this.getX() + 1 - de.getX()) < 2) {
                                    x += 0;
                                } else {
                                    x += 1;
                                    this.moved = true;
                                }
                                this.moved = true;
                            } else {
                                goingRight = true;
                                reverseAppearance();
                                x += 1;
                                this.moved = true;
                            }
                        } else if (FishTank.getEntity(x - 1, y) == null && x - 1 > 0) {
                            if (!goingRight) {
                                if (Math.abs(this.getY() - de.getY()) + Math.abs(this.getX() - 1 - de.getX()) >= 2) {
                                    x -= 1;
                                } else {
                                    x -= 0;
                                    this.moved = true;
                                }
                            } else {
                                goingRight = false;
                                reverseAppearance();
                                x -= 1;
                                this.moved = true;
                            }
                        } else {
                            x += 0;
                        }
                    }
                } else {
                    if (FishTank.getEntity(x, y - 1) == null) {
                        y -= 1;
                    } else {
                        if (FishTank.getEntity(x + 1, y) == null && x + 1 < FishTank.getWidth() - 1) {
                            if (goingRight) {
                                x += 1;
                                this.moved = true;
                            } else {
                                goingRight = true;
                                reverseAppearance();
                                x += 1;
                                this.moved = true;
                            }
                        } else if (FishTank.getEntity(x - 1, y) == null && x - 1 > 0) {
                            if (!goingRight) {
                                x -= 1;
                                this.moved = true;
                            } else {
                                goingRight = false;
                                reverseAppearance();
                                x -= 1;
                                this.moved = true;
                            }
                        } else {
                            x -= 0;
                        }
                    }
                }
            }
        }
    }

    private void moveX() {
        if (!this.moved) {
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
                        if (FishTank.getEntity(x - 1, y) == null) {
                            x -= 1;
                        } else {
                            if (this.getY() < de.getY()) {
                                if (this.y - 1 <= 0 && FishTank.getEntity(x, y - 1) == null) {
                                    y -= 1;
                                } else {
                                    y -= 0;
                                }
                            } else {
                                if (this.y + 1 <= FishTank.getHeight() - 1 && FishTank.getEntity(x, y + 1) == null) {
                                    y += 1;
                                } else {
                                    y += 0;
                                }
                            }

                        }
                    }
                }

            }
        }


    }
}
