package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FollowingFishTest {
  private Fish followee;
  private FollowingFish follower;

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

}
