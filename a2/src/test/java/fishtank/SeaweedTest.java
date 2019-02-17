package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
    public void growBack(){
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
    public void eatonTestFish(){
        FishTank.addEntity(23,5,fish);
        FishTank.addEntity(22,10,seaweed);
        fish.goingRight = false;
        fish.update();
        assertEquals("The length of the seaweed should be 5 ",5 ,seaweed.l);
    }
    @Test
    public void eatonTestHungryFish(){
        FishTank.addEntity(23,5,hungryFish);
        FishTank.addEntity(22,10,seaweed);
        hungryFish.goingRight = false;
        hungryFish.update();
        assertEquals("The length of the seaweed should be 5 ",5 ,seaweed.l);
    }

}
