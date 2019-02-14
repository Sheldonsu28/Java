package fishtank;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.ranges.RangeException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class BubbleTest {
    private Bubble bubble;
    private Seaweed seaweed1;
    private Seaweed seaweed2;
    private Seaweed seaweed3;
    private Fish fish1;
    private Fish fish2;
    private Fish fish3;
    private FollowingFish followingFish1;
    private FollowingFish followingFish2;
    private FollowingFish followingFish3;
    private HungryFish hungryFish1;
    private HungryFish hungryFish2;
    private HungryFish hungryFish3;

    @Before
    public void setUp(){
        seaweed1 = mock( Seaweed.class);
        seaweed2= mock( Seaweed.class);
        seaweed3 = mock( Seaweed.class);
        fish1 = mock(Fish.class);
        fish2 = mock(Fish.class);
        fish3 = mock(Fish.class);
        followingFish1 = mock(FollowingFish.class);
        followingFish2 = mock(FollowingFish.class);
        followingFish3 = mock(FollowingFish.class);
        hungryFish1 = mock(HungryFish.class);
        hungryFish2 = mock(HungryFish.class);
        hungryFish3 = mock(HungryFish.class);
        bubble = new Bubble();



    }
    @Test
    public void collisionFish(){
        FishTank.addEntity(10,20,fish1);
        FishTank.addEntity(11,19,fish2);
        FishTank.addEntity(12,20,fish3);
        FishTank.addEntity(11,20,bubble);
        for (int i = 0 ;i < 5;i++){
            bubble.update();
        }
        assertEquals(11, bubble.getX());
        assertEquals (20, bubble.getY());
    }
    @Test
    public void collisionSeaweed(){
        FishTank.addEntity(10,20,seaweed1);
        FishTank.addEntity(11,19,seaweed2);
        FishTank.addEntity(12,20,seaweed3);
        FishTank.addEntity(11,20,bubble);
        for (int i = 0 ;i < 5;i++){
            bubble.update();
        }
        assertEquals(11, bubble.getX());
        assertEquals (20, bubble.getY());
    }
    @Test
    public void collisionFollowingFish() {
        FishTank.addEntity(11, 20, followingFish1);
        FishTank.addEntity(12, 19, followingFish2);
        FishTank.addEntity(13, 20, followingFish3);
        FishTank.addEntity(12, 20, bubble);
        for (int i = 0; i < 5; i++) {
            bubble.update();
        }
        assertEquals(12, bubble.getX());
        assertEquals(20, bubble.getY());
    }
    @Test
    public void collisionHungryFish() {
        FishTank.addEntity(11, 20, hungryFish1);
        FishTank.addEntity(12, 19, hungryFish2);
        FishTank.addEntity(13, 20, hungryFish3);
        FishTank.addEntity(12, 20, bubble);
        for (int i = 0; i < 5; i++) {
            bubble.update();
        }
        assertEquals(12, bubble.getX());
        assertEquals(20, bubble.getY());
    }

    @Test
    public void testDelete(){
        for (int i = 0; i < 20;i++ ){
            FishTank.addEntity(i,0,bubble);
            bubble.update();
            assertTrue(!bubble.exists());
        }
    }
    @Test
    public void testRightProbability(){
        int counter = 0;
        for (int i = 0; i < 1000;i++) {
            FishTank.addEntity(10, 20, bubble);
            bubble.update();
            if (bubble.getX() > 10) {
                counter++;
            }

        }
        assertTrue(counter >= 280 && counter <= 380);
    }
    @Test
    public void testLeftProbability(){
        int counter = 0;
        for (int i = 0; i < 1000;i++) {
            FishTank.addEntity(10, 20, bubble);
            bubble.update();
            if (bubble.getX() < 10) {
                counter++;
            }
        }
        assertTrue(counter >= 280 && counter <= 380);
    }
}
