package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FishTest {

    /* Note: FishTest is in the package fishtank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be autograding your tests, so make sure
       to follow this naming convention!
     */
    private Fish fish;

    @Before
    public void setUp() {
        fish = new Fish();
    }

    @Test
    public void testFishBubbles() {
        //Note: This test currently fails, but should pass once you've
      // refactored &
        //fixed the starter code.
        boolean bubbleMade = false;
        for (int i = 0; i < 200; i++) {
            fish.setLocation(5, 10);
            fish.goingRight =
                false; //notice: I can edit package private attributes!
            fish.update();
            //fish should move one tile left and eventually blow a bubble.
            FishTankEntity e = FishTank.getEntity(5 - 1, 10);
            if (e instanceof Bubble) {
                bubbleMade = true;
                break;
            }
        }
        //You could also write "assert bubbleMade", but using the JUnit version
        //makes the message much nicer if it fails.
        assertTrue(bubbleMade);
    }
    @Test
    public void alwaysGoingRight(){
        fish.setLocation(20,10);
        for (int i = 0 ; i < 10; i++ ){
            fish.setGoingRight(true);
            fish.update();
        }
        assertEquals(30, fish.getX());
    }
    @Test
    public void alwaysGoingLeft(){
        fish.setLocation(20,10);
        for (int i = 0 ; i < 10; i++ ){
            fish.setGoingRight(false);
            fish.update();
        }
        assertEquals(10, fish.getX());
    }

    @Test
    public void collisionWithBorder(){
        fish.setLocation(0,1);
        for (int i = 0 ; i < 10; i++ ){
            fish.setGoingRight(false);
            fish.update();
        }
        assertEquals(0, fish.getX());

    }

    @Test
    public void blowBubbleProbability(){
        int counter = 0;

        for (int i = 0; i < 1000; i++){
            fish.setLocation(10,11);
            fish.update();
            for (int x = 0;x < FishTank.getWidth() -1 ; x++){
                for (int y = 0; y < FishTank.getHeight() - 1; y++){
                    FishTankEntity e = FishTank.getEntity(x,y);
                    if (e instanceof Bubble){
                        counter++;
                        FishTank.addEntity( x,y, null);
                    }
                }
            }

        }
        System.out.println(counter);
        //assertTrue(counter > 50 && counter < 150);
    }

    @Test
    public void moveVerticalProbability(){
        int counter = 0;

        for (int i = 0; i< 1000; i++){
            fish.setLocation(10,20);
            fish.update();
            if (fish.getY() != 20 ){
                counter++;
            }
        }
        System.out.println(counter);
        assertTrue(counter > 50 && counter < 150);
    }
    @Test
    public void turnAroundProbability(){
        int counter = 0;
        fish.setLocation(32,24);
        for (int i = 0; i< 1000; i++){
            fish.goingRight = true;
            fish.update();
            if (!fish.goingRight){
                counter += 1;
            }
        }
        System.out.println(counter);
        assertTrue(counter > 50 && counter < 150);
    }
}
