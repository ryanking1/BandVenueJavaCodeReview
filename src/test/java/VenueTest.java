import org.junit.*;
import static org.junit.Assert.*;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
    public void all_emptyAtFirst() {
      assertEquals(Venue.all().size(), 0);
  }
}
