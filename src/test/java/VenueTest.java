import org.junit.*;
import static org.junit.Assert.*;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
    public void all_emptyAtFirst() {
      assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Venue firstVenue = new Venue("Venue 1");
    Venue secondVenue = new Venue("Venue 1");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesVenueIntoDatabase() {
    Venue myVenue = new Venue("Venue 1");
    myVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(myVenue.getId(), savedVenue.getId());
  }

  @Test
  public void allMethodReturnsVenuesCorrectly() {
    Venue myVenue = new Venue("Venue 1");
    myVenue.save();
    Venue myVenue2 = new Venue("Venue 2");
    myVenue2.save();
    assertTrue(Venue.all().contains(myVenue2));
  }
}
