import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

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

  @Override
  public boolean equals(Object otherBand){
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return this.getName().equals(newBand.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public ArrayList<Venue> getVenues() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT DISTINCT venueid FROM bands_venues WHERE bandid = :bandid";
      List<Integer> venueids = con.createQuery(sql)
        .addParameter("bandid", this.getId())
        .executeAndFetch(Integer.class);

      ArrayList<Venue> venues = new ArrayList<Venue>();

      for (Integer venueid : venueids) {
        String venueQuery = "SELECT * FROM venues WHERE id = :venueid";
        Venue venue = con.createQuery(venueQuery)
          .addParameter("venueid", venueid)
          .executeAndFetchFirst(Venue.class);
          venues.add(venue);
      }
    return venues;
    }
  }

  public static Band find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands where id=:id";
      Band Band = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Band.class);
      return Band;
    }
  }

  public void addVenue(Venue venue) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues (bandid, venueid) VALUES (:bandid, :venueid)";
      con.createQuery(sql)
        .addParameter("bandid", this.getId())
        .addParameter("venueid", venue.getId())
        .executeUpdate();
    }
  }


  public void update(String name) {
    this.name = name;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE bands SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM bands WHERE id = :id;";
      con.createQuery(deleteQuery)
        .addParameter("id", id)
        .executeUpdate();

      String joinDeleteQuery = "DELETE FROM bands_venues WHERE bandid = :bandid";
      con.createQuery(joinDeleteQuery)
        .addParameter("bandid", this.getId())
        .executeUpdate();
    }
  }

  public static void deleteAllBands() {
      String sql = "DELETE FROM bands";
      try(Connection con = DB.sql2o.open()) {
        con.createQuery(sql)
        .executeUpdate();
    }
  }
}
