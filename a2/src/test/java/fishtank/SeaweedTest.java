package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class SeaweedTest {
    private Fish fish;
    private HungryFish hungryFish;
    private Seaweed seaweed;


    @Before
    public void setUp(){
        fish = new Fish();
        hungryFish = new HungryFish();
        seaweed = new Seaweed(7);
    }

    @Test
    public void testGrowBack(){
        FishTank.addEntity(22,32,seaweed);
        FishTank.addEntity(21, 30,fish);
        fish.setGoingRight(true);
        fish.update();
        FishTank.getEntity(22,32).update();
        int before = seaweed.getLenght();
        for (int i = 0; i < 200;i++){
            FishTank.getEntity(22,32).update();
        }
        assertEquals("The seaweed did not grow.", before + 1, seaweed.getLenght());
    }

    @Test
    public void testBoundary(){
        seaweed.setLocation(0,11);
        for(int i = 0;i < 5; i++){
            hungryFish.update();
            assertTrue("Out of bound On left!", hungryFish.getX() >= 0);
            assertTrue("Seaweed moved!", seaweed.getX() == 0 && seaweed.getY() == 11);
        }
        seaweed.setLocation(FishTank.width - 1,11);
        for(int i = 0;i < 5; i++){

            seaweed.update();
            assertTrue("Out of bound On right!", hungryFish.getX() <= FishTank.width - 1);
        }
        for(int i = 0;i < 1000; i++){
            seaweed.setLocation(11,0);
            seaweed.update();
            assertTrue("Out of bound On top!", hungryFish.getY() >= 0);
        }
        for(int i = 0;i < 1000; i++){
            seaweed.setLocation(11,FishTank.height - 1);
            seaweed.update();
            assertTrue("Out of bound On bottom!", hungryFish.getY() <= FishTank.getHeight() - 1 );
        }

    }
}
