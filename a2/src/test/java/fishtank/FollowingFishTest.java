package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FollowingFishTest {
  private Fish followee;
  private FollowingFish follower;
  private HungryFish hungryFish;
  private HungryFish hungryFish1;
  private HungryFish hungryFish2;
  private HungryFish hungryFish3;
  private Fish fish;
  private Fish fish1;
  private Fish fish2;
  private Fish fish3;
  private Seaweed seaweed;
  private Seaweed seaweed1;
  private Seaweed seaweed2;
  private Seaweed seaweed3;
  private Bubble bubble;
  private Bubble bubble1;
  private Bubble bubble2;
  private Bubble bubble3;


  @Before
  public void setUp() {
    followee = mock(Fish.class);
    //note: this is also why we use getters and setters so much in Java,
    //we wouldn't be able to mock the field itself if it were used instead
    //of the getter.
    when(followee.getX()).thenReturn(5);
    //This syntax is introduced by a library called mockito.
    //You can use it however you want, and it will be installed when we
    //run the grader.
    //See: http://www.vogella.com/tutorials/Mockito/article.html from 4 onwards
    when(followee.getY()).thenReturn(10);

    follower = new FollowingFish(followee);
    follower.setLocation(20, 20);
    fish = mock(Fish.class);
    fish1 = mock(Fish.class);
    fish2 = mock(Fish.class);
    fish3 = mock(Fish.class);
    hungryFish = mock(HungryFish.class);
    hungryFish1 = mock(HungryFish.class);
    hungryFish2 = mock(HungryFish.class);
    hungryFish3 = mock(HungryFish.class);
    seaweed = mock(Seaweed.class);
    seaweed1 = mock(Seaweed.class);
    seaweed2 = mock(Seaweed.class);
    seaweed3 = mock(Seaweed.class);
    bubble = mock(Bubble.class);
    bubble1 = mock(Bubble.class);
    bubble2 = mock(Bubble.class);
    bubble3 = mock(Bubble.class);
  }

  @Test
  public void testApproachesFromBottomRight() {
    //it should take exactly 15 updates to get from
    //(20, 20) to (5, 10)
    for(int i = 0; i < 15; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }
  @Test
  public void testApproachesFromRight(){
    follower.setLocation(10,10);
    for (int i = 0;i < 5; i++){
      follower.update();
    }
    int x_dist = Math.abs(follower.getX() - followee.getX());
    int y_dist = Math.abs(follower.getY() - followee.getY());
    assertEquals("X coordinate does not match",0, x_dist);
    assertEquals("Y coordinate does not match",2, y_dist);
  }

  @Test
  public void testApproachesFromAbove(){
    follower.setLocation(5,0);
    for (int i = 0;i < 8; i++){
      follower.update();
    }
    int x_dist = Math.abs(follower.getX() - followee.getX());
    int y_dist = Math.abs(follower.getY() - followee.getY());
    assertEquals("X coordinate does not match",0, x_dist);
    assertEquals("Y coordinate does not match",2, y_dist);
  }

  @Test
  public void testApproachesFromLeft(){
    follower.setLocation( 0 ,10);
    for (int i = 0; i < 5; i++){
      follower.update();
    }
    int x_dist = Math.abs(follower.getX() - followee.getX());
    int y_dist = Math.abs(follower.getY() - followee.getY());
    assertEquals("X coordinate does not match",0, x_dist);
    assertEquals("Y coordinate does not match",2, y_dist);
  }

  @Test
  public void testOnUpperBorder(){
    when(followee.getX()).thenReturn(10);
    when(followee.getY()).thenReturn(1);
    follower.setLocation(3,0);
    for (int i = 0; i < 8; i++){
      follower.update();
    }
    int x_dist = Math.abs(follower.getX() - followee.getX());
    int y_dist = Math.abs(follower.getY() - followee.getY());
    assertEquals("X coordinate does not match",1, x_dist);
    assertEquals("Y coordinate does not match",1, y_dist);

  }
  @Test
  public void testOnLowerBorder(){
    when(followee.getX()).thenReturn(10);
    when(followee.getY()).thenReturn(FishTank.getHeight() - 2);
    follower.setLocation(3,FishTank.getHeight() - 1);
    for (int i = 0; i < 8; i++){
      follower.update();
    }
    int x_dist = Math.abs(follower.getX() - followee.getX());
    int y_dist = Math.abs(follower.getY() - followee.getY());
    assertEquals("X coordinate does not match",1, x_dist);
    assertEquals("Y coordinate does not match",1, y_dist);

  }

  @Test
  public void testCornerOnUpperBorder(){
    when(followee.getX()).thenReturn(10);
    when(followee.getY()).thenReturn(1);
    follower.setLocation(10,0);
    for (int i = 0; i < 2; i++){
      follower.update();
    }
    int x_dist = Math.abs(follower.getX() - followee.getX());
    int y_dist = Math.abs(follower.getY() - followee.getY());
    assertEquals("X coordinate does not match",1, x_dist);
    assertEquals("Y coordinate does not match",1, y_dist);
  }
  @Test
  public void testCornerOnLowerBorder(){
    when(followee.getX()).thenReturn(10);
    when(followee.getY()).thenReturn(FishTank.height - 2);
    follower.setLocation(10,FishTank.height - 1);
    for (int i = 0; i < 2; i++){
      follower.update();
    }
    int x_dist = Math.abs(follower.getX() - followee.getX());
    int y_dist = Math.abs(follower.getY() - followee.getY());
    assertEquals("X coordinate does not match",1, x_dist);
    assertEquals("Y coordinate does not match",1, y_dist);
  }
  @Test
  public void collisionWithFish(){
    FishTank.addEntity(22,22,follower);
    FishTank.addEntity(21,22,fish);
    FishTank.addEntity(23,22,fish1);
    FishTank.addEntity(22,23,fish2);
    FishTank.addEntity(22,21,fish3);
    follower.goingRight = false;
    follower.update();
    assertTrue("Following fish moved!",follower.getX() == 22 && follower.getY() == 22);
  }
  @Test
  public void collisionWithBubble(){
    FishTank.addEntity(22,22,follower);
    FishTank.addEntity(21,22,bubble);
    FishTank.addEntity(23,22,bubble1);
    FishTank.addEntity(22,23,bubble2);
    FishTank.addEntity(22,21,bubble3);
    hungryFish.goingRight = true;
    hungryFish.update();
    assertTrue("Following fish moved!",follower.getX() == 22 && follower.getY() == 22);
  }
  @Test
  public void collisionWithSeaweed(){
    FishTank.addEntity(22,22,follower);
    FishTank.addEntity(21,22,seaweed);
    FishTank.addEntity(23,22,seaweed1);
    FishTank.addEntity(22,23,seaweed2);
    FishTank.addEntity(22,21,seaweed3);
    follower.goingRight = true;
    follower.update();
    assertTrue("Following fish moved!",follower.getX() == 22 && follower.getY() == 22);
  }
  @Test
  public void collisionWithHungryFish(){
    FishTank.addEntity(22,22,follower);
    FishTank.addEntity(21,22,hungryFish);
    FishTank.addEntity(23,22,hungryFish1);
    FishTank.addEntity(22,23,hungryFish2);
    FishTank.addEntity(22,21,hungryFish3);
    follower.goingRight = true;
    follower.update();
    assertTrue("Following fish moved!",follower.getX() == 22 && follower.getY() == 22);
  }

}
