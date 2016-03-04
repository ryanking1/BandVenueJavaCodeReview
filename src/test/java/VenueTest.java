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

  @Test
  public void find_findsVenueInDatabase_true() {
    Venue myVenue = new Venue("Venue 11");
    myVenue.save();
    Venue savedVenue = Venue.find(myVenue.getId());
    assertTrue(myVenue.equals(savedVenue));
  }
//Start here
  @Test
  public void addBand_addsBandToVenue() {
    Band myBand = new Band("Band 1");
    myBand.save();

    Venue myVenue = new Venue("Venue 1");
    myVenue.save();

    myVenue.addBand(myBand);
    Band savedBand = myVenue.getBands().get(0);
    assertTrue(myBand.equals(savedBand));
  }


  @Test
  public void delete_deletesAVenue() {
    Band myBand = new Band("Band 1");
    myBand.save();

    Venue myVenue = new Venue("Venue 1");
    myVenue.save();

    myVenue.addBand(myBand);
    myVenue.delete();
    assertEquals(myBand.getVenues().size(), 0);
  }
}
