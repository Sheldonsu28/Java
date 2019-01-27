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
}
