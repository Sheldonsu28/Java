package fishtank;

import javafx.beans.binding.When;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FishTest {

    /* Note: FishTest is in the package fishtank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be autograding your tests, so make sure
       to follow this naming convention!
     */
    private Fish fish;
    private Seaweed seaweed;
    private HungryFish hungryFish;
    private Fish fish2;
    private FollowingFish followingFish;
    private Bubble bubble;

    @Before
    public void setUp() {
        fish = new Fish();
        seaweed = new Seaweed(7);
        fish2 = mock(Fish.class);
        followingFish = mock(FollowingFish.class);
        bubble = mock(Bubble.class);
        hungryFish = mock(HungryFish.class);

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
    public void collisionWithSelf(){
        for(int x = 0; x < FishTank.width - 1; x++){
            for(int y  = 0; y < FishTank.height -1 ;y++){
                FishTank.entities[x][y] = null;
            }
        }
        FishTank.addEntity(32,24, fish);
        FishTank.addEntity(33,24,fish2);
        for (int i = 0; i <10; i++){
            FishTank.addEntity(32,24, fish);
            fish.goingRight = true;
            fish.update();
            assertEquals(32,fish.getX());
        }
    }
    @Test
    public void collisionWithHungryFish(){
        for(int x = 0; x < FishTank.width - 1; x++){
            for(int y  = 0; y < FishTank.height -1 ;y++){
                FishTank.entities[x][y] = null;
            }
        }
        FishTank.addEntity(32,24,fish);
        FishTank.addEntity(33,24,hungryFish);
        fish.goingRight = true;
        fish.update();
        assertEquals(32,fish.getX());
        assertTrue("fish did not turned around",!fish.goingRight);
    }
    @Test
    public void collisionWithBubble(){
        for(int x = 0; x < FishTank.width - 1; x++){
            for(int y  = 0; y < FishTank.height -1 ;y++){
                FishTank.entities[x][y] = null;
            }
        }
        FishTank.addEntity(32,24,fish);
        FishTank.addEntity(33,24,bubble);
        fish.goingRight = true;
        fish.update();
        assertEquals(32,fish.getX());
        assertTrue("fish did not turned around",!fish.goingRight);
    }
    @Test
    public void collisionWithSeaweed(){
        for(int x = 0; x < FishTank.width - 1; x++){
            for(int y  = 0; y < FishTank.height -1 ;y++){
                FishTank.entities[x][y] = null;
            }
        }
        FishTank.addEntity(32,24,fish);
        FishTank.addEntity(33,24,seaweed);
        fish.goingRight = true;
        fish.update();
        assertEquals("fish should not move on X axis",32,fish.getX());
        assertTrue("fish did not turned around",!fish.goingRight);
    }
    @Test
    public void collisionWithFollowingFish(){
        for(int x = 0; x < FishTank.width - 1; x++){
            for(int y  = 0; y < FishTank.height -1 ;y++){
                FishTank.entities[x][y] = null;
            }
        }
        FishTank.addEntity(32,24,fish);
        FishTank.addEntity(33,24,followingFish);
        fish.goingRight = true;
        fish.update();
        assertEquals("fish should not move on X axis",32,fish.getX());
        assertTrue("fish did not turned around",!fish.goingRight);
    }
    @Test
    public void alwaysGoingRight(){
        for(int x = 0; x < FishTank.width - 1; x++){
            for(int y  = 0; y < FishTank.height -1 ;y++){
                FishTank.entities[x][y] = null;
            }
        }
        fish.setLocation(0,10);
        for (int i = 0 ; i < 10; i++ ){
            fish.setGoingRight(true);
            fish.update();
            System.out.println(FishTank.getEntity(fish.getX() + 1,fish.getY()));
        }
        assertEquals(10, fish.getX());
    }
    @Test
    public void alwaysGoingLeft(){
        for(int x = 0; x < FishTank.width - 1; x++){
            for(int y  = 0; y < FishTank.height -1 ;y++){
                FishTank.entities[x][y] = null;
            }
        }
        fish.setLocation(20,10);
        for (int i = 0 ; i < 10; i++ ){
            fish.setGoingRight(false);
            fish.update();
        }
        assertEquals(10, fish.getX());
    }

    @Test
    public void blowBubbleProbability(){
        for(int x = 0; x < FishTank.width - 1; x++){
            for(int y  = 0; y < FishTank.height -1 ;y++){
                FishTank.entities[x][y] = null;
            }
        }
        int counter = 0;
        for (int i = 0; i < 1000; i++){
            fish.setLocation(10,11);
            fish.update();
            FishTankEntity e = FishTank.getEntity(fish.getX(),11);
            if (e instanceof  Bubble){
                counter ++;
                FishTank.entities[fish.getX()][fish.getY()] = null;
            }
        }
        assertTrue(counter > 50 && counter < 150);
    }

    @Test
    public void moveVerticalProbability(){
        for(int x = 0; x < FishTank.width - 1; x++){
            for(int y  = 0; y < FishTank.height -1 ;y++){
                FishTank.entities[x][y] = null;
            }
        }
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
        for(int x = 0; x < FishTank.width - 1; x++){
            for(int y  = 0; y < FishTank.height -1 ;y++){
                FishTank.entities[x][y] = null;
            }
        }
        int counter = 0;
        fish.setLocation(32,24);
        for (int i = 0; i< 1000; i++){
            fish.setGoingRight(true);
            fish.update();
            if (!fish.goingRight){
                counter += 1;
            }
        }
        System.out.println(counter);
        assertTrue(counter > 50 && counter < 150);
    }
    @Test
    public void testEatenSeaweedFish(){
        FishTank.addEntity(23,5,fish);
        FishTank.addEntity(22,10,seaweed);
        fish.goingRight = false;
        fish.update();
        assertEquals("The length of the seaweed is not correct ",Math.abs(fish.getY() - seaweed.getY()) ,seaweed.l);
    }

    @Test
    public void testBoundary(){
        fish.setLocation(0,11);
        for(int i = 0;i < 5; i++){
            fish.goingRight = false;
            fish.update();
            assertTrue("Out of bound On left!", fish.getX() >= 0);
        }
        fish.setLocation(FishTank.width - 1,11);
        for(int i = 0;i < 5; i++){
            fish.goingRight = true;
            fish.update();
            assertTrue("Out of bound On right!", fish.getX() <= FishTank.width - 1);
        }
        for(int i = 0;i < 1000; i++){
            fish.setLocation(11,0);
            fish.update();
            assertTrue("Out of bound On top!", fish.getY() >= 0);
        }
        for(int i = 0;i < 1000; i++){
            fish.setLocation(11,FishTank.height - 1);
            fish.update();
            assertTrue("Out of bound On bottom!", fish.getY() <= FishTank.height - 1 );
        }

    }
}
