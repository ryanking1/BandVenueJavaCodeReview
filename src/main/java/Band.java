import java.util.List;
import org.sql2o.*;

public class Band {
  private String name;
  private int id;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Band(String name) {
    this.name = name;
  }

  public static List<Band> all() {
    String sql = "SELECT * FROM bands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Band.class);
    }
  }
}
