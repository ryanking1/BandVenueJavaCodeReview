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
  public void delete_deletesAvenue() {
    Band myBand = new Band("Band 1");
    myBand.save();

    Venue myVenue = new Venue("Venue 1");
    myVenue.save();

    myBand.addVenue(myVenue);
    myVenue.delete();
    assertEquals(myBand.getVenues().size(), 0);
  }

  @Test
  public void updatesVenueName() {
    Venue myVenue = new Venue("Venue 1");
    myVenue.save();
    myVenue.update("Venue 2");
    Venue savedVenue = Venue.find(myVenue.getId());
    assertTrue(savedVenue.getName().equals("Venue 2"));
  }

  @Test
  public void delete_deletesAllVenuess() {
    Band myBand = new Band("Band 1");
    myBand.save();

    Venue myVenue = new Venue("Venue 1");
    myVenue.save();

    Venue myVenue2 = new Venue("Venue 2");
    myVenue2.save();

    myBand.addVenue(myVenue);
    myBand.addVenue(myVenue2);

    Venue.deleteAllVenues();
    assertEquals(myBand.getVenues().size(), 0);
  }
}
