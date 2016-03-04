import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
   public void all_emptyAtFirst() {
     assertEquals(Band.all().size(), 0);
   }
}
