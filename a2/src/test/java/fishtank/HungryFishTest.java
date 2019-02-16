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
    private HungryFish hungryFish3;
    private Fish fish;
    private Seaweed seaweed;
    private FollowingFish followingFish;
    private Bubble bubble;

    @Before
    public void setUp(){
        hungryFish = new HungryFish();
        hungryFish2 = mock(HungryFish.class);
        hungryFish3 = mock(HungryFish.class);
        fish = mock(Fish.class);
        seaweed = mock(Seaweed.class);
        followingFish = mock(FollowingFish.class);
        bubble = mock(Bubble.class);
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

}
