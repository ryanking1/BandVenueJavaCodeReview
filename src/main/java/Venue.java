import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Venue {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Venue(String name) {
    this.name = name;
  }

  public static List<Venue> all() {
    String sql = "SELECT * FROM venues";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Venue.class);
    }
  }

  @Override
  public boolean equals(Object otherVenue){
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) otherVenue;
      return this.getName().equals(newVenue.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO venues (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String name) {
    this.name = name;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE venues SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addBand(Band band) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues (bandid, venueid) VALUES (:bandid, :venueid)";
      con.createQuery(sql)
        .addParameter("venueid", this.getId())
        .addParameter("bandid", band.getId())
        .executeUpdate();
    }
  }

  public ArrayList<Band> getBands() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT DISTINCT bandid FROM bands_venues WHERE venueid = :venueid";
      List<Integer> bandids = con.createQuery(sql)
        .addParameter("venueid", this.getId())
        .executeAndFetch(Integer.class);

      ArrayList<Band> bands = new ArrayList<Band>();

      for (Integer bandid : bandids) {
          String venueQuery = "Select * From bands WHERE id = :bandid";
          Band band = con.createQuery(venueQuery)
            .addParameter("bandid", bandid)
            .executeAndFetchFirst(Band.class);
            bands.add(band);
      }
      return bands;
    }
  }

  public static Venue find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM venues where id=:id";
      Venue venue = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Venue.class);
      return venue;
    }
  }

  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM venues WHERE id = :id;";
      con.createQuery(deleteQuery)
        .addParameter("id", id)
        .executeUpdate();

      String joinDeleteQuery = "DELETE FROM bands_venues WHERE venueid = :venueid";
      con.createQuery(joinDeleteQuery)
        .addParameter("venueid", this.getId())
        .executeUpdate();
    }
  }

  public static void deleteAllVenues() {
      String sql = "DELETE FROM venues";
      try(Connection con = DB.sql2o.open()) {
        con.createQuery(sql)
        .executeUpdate();

      String joinDeleteQuery = "DELETE FROM bands_venues WHERE venueid >= 0";
      con.createQuery(joinDeleteQuery)
        .executeUpdate();
    }
  }
}
