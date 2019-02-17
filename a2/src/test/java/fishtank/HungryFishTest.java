package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HungryFishTest {
    private HungryFish hungryFish;
    private HungryFish hungryFish2;
    private Seaweed seaweed2;
    private Fish fish;
    private Seaweed seaweed;
    private FollowingFish followingFish;
    private Bubble bubble;

    @Before
    public void setUp(){
        hungryFish = new HungryFish();
        hungryFish2 = mock(HungryFish.class);
        fish = mock(Fish.class);
        seaweed = mock(Seaweed.class);
        followingFish = mock(FollowingFish.class);
        bubble = mock(Bubble.class);
        seaweed2 = new Seaweed(7);
    }
    @Test
    public void collisionWithSelf(){
        FishTank.addEntity(32,24, hungryFish);
        FishTank.addEntity(33,24,hungryFish2);
        for (int i = 0; i <10; i++){
            FishTank.addEntity(32,24, hungryFish);
            hungryFish.goingRight = true;
            hungryFish.update();
            assertEquals(32,hungryFish.getX());
        }
    }
    @Test
    public void collisionWithFish(){
        FishTank.addEntity(32,24,hungryFish);
        FishTank.addEntity(33,24,fish);
        hungryFish.goingRight = true;
        hungryFish.update();
        assertEquals(32,hungryFish.getX());
        assertTrue("Hungry did not turned around",!hungryFish.goingRight);
    }
    @Test
    public void collisionWithBubble(){
        FishTank.addEntity(32,24,hungryFish);
        FishTank.addEntity(33,24,bubble);
        hungryFish.goingRight = true;
        hungryFish.update();
        assertEquals(32,hungryFish.getX());
        assertTrue("Hungry did not turned around",!hungryFish.goingRight);
    }
    @Test
    public void collisionWithSeaweed(){
        FishTank.addEntity(32,24,hungryFish);
        FishTank.addEntity(33,24,seaweed);
        hungryFish.goingRight = true;
        hungryFish.update();
        assertEquals("HungryFish should not move on X axis",32,hungryFish.getX());
        assertTrue("Hungry did not turned around",!hungryFish.goingRight);
    }
    @Test
    public void collisionWithFollowingFish(){
        FishTank.addEntity(32,24,hungryFish);
        FishTank.addEntity(33,24,followingFish);
        hungryFish.goingRight = true;
        hungryFish.update();
        assertEquals("HungryFish should not move on X axis",32,hungryFish.getX());
        assertTrue("Hungry did not turned around",!hungryFish.goingRight);
    }

    @Test
    public void moveVerticalProbability(){
        int counter = 0;

        for (int i = 0; i< 1000; i++){
            hungryFish.setLocation(10,20);
            hungryFish.update();
            if (hungryFish.getY() != 20 ){
                counter++;
            }
        }
        System.out.println(counter);
        assertTrue(counter > 50 && counter < 150);
    }
    @Test
    public void turnAroundProbability(){
        int counter = 0;
        hungryFish.setLocation(32,24);
        for (int i = 0; i< 1000; i++){
            hungryFish.goingRight = true;
            hungryFish.update();
            if (!hungryFish.goingRight){
                counter += 1;
            }
        }
        System.out.println(counter);
        assertTrue(counter > 50 && counter < 150);
    }

    @Test
    public void blowBubbleProbability(){
        int counter = 0;
        for (int i = 0; i < 1000; i++){
            hungryFish.setLocation(10,11);
            hungryFish.update();
            FishTankEntity e = FishTank.getEntity(hungryFish.getX(),11);
            if (e instanceof  Bubble){
                counter ++;
                FishTank.entities[hungryFish.getX()][hungryFish.getY()] = null;
            }
        }
        assertTrue(counter > 50 && counter < 150);
    }
    @Test
    public void testBoundary(){
        hungryFish.setLocation(0,11);
        for(int i = 0;i < 5; i++){
            hungryFish.goingRight = false;
            hungryFish.update();
            assertTrue("Out of bound On left!", hungryFish.getX() >= 0);
        }
        hungryFish.setLocation(FishTank.width - 1,11);
        for(int i = 0;i < 5; i++){
            hungryFish.goingRight = true;
            hungryFish.update();
            assertTrue("Out of bound On right!", hungryFish.getX() <= FishTank.width - 1);
        }
        for(int i = 0;i < 1000; i++){
            hungryFish.setLocation(11,0);
            hungryFish.update();
            assertTrue("Out of bound On top!", hungryFish.getY() >= 0);
        }
        for(int i = 0;i < 1000; i++){
            hungryFish.setLocation(11,FishTank.height - 1);
            hungryFish.update();
            assertTrue("Out of bound On bottom!", hungryFish.getY() <= FishTank.getHeight() - 1 );
        }

    }

    @Test
    public void testEatSeaweedHungryFish(){
        FishTank.addEntity(23,5,hungryFish);
        FishTank.addEntity(22,10,seaweed2);
        hungryFish.goingRight = false;
        hungryFish.update();
        assertEquals("The length of the seaweed is not correct",Math.abs(hungryFish.getY() - seaweed2.getY()) ,seaweed2.l);
    }

    @Test
    public void alwaysGoingRight(){
        for(int x = 0; x < FishTank.width - 1; x++){
            for(int y  = 0; y < FishTank.height -1 ;y++){
                FishTank.entities[x][y] = null;
            }
        }
        hungryFish.setLocation(0,10);
        for (int i = 0 ; i < 10; i++ ){
            hungryFish.setGoingRight(true);
            hungryFish.update();
            System.out.println(FishTank.getEntity(hungryFish.getX() + 1,hungryFish.getY()));
        }
        assertEquals(10, hungryFish.getX());
    }
    @Test
    public void alwaysGoingLeft(){
        for(int x = 0; x < FishTank.width - 1; x++){
            for(int y  = 0; y < FishTank.height -1 ;y++){
                FishTank.entities[x][y] = null;
            }
        }
        hungryFish.setLocation(20,10);
        for (int i = 0 ; i < 10; i++ ){
            hungryFish.setGoingRight(false);
            hungryFish.update();
        }
        assertEquals(10, hungryFish.getX());
    }

}
