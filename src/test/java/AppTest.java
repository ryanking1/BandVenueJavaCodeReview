import org.junit.*;
import static org.junit.Assert.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
    public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Band List");
    }

  @Test
  public void SeeBandsInVenue() {
    Band band1 = new Band("Band 1");
    band1.save();
    Band band2 = new Band("Band 2");
    band2.save();
    Venue venue1 = new Venue("Venue 1");
    venue1.save();
    Venue venue2 = new Venue("Venue 2");
    venue2.save();
    Venue venue3 = new Venue("Venue 3");
    venue3.save();
    Venue venue4 = new Venue("Venue 4");
    venue4.save();
    venue1.addBand(band1);
    venue1.addBand(band2);
    venue2.addBand(band1);
    venue3.addBand(band2);
    venue4.addBand(band1);
    venue4.addBand(band2);
    goTo("http://localhost:4567/");
    click("a", withText("Venue 1"));
    assertThat(pageSource()).contains("Band 1");
    assertThat(pageSource()).contains("Band 2");
    }

  @Test
  public void SeeVenuesInBand() {
    Band band1 = new Band("Band 1");
    band1.save();
    Band band2 = new Band("Band 2");
    band2.save();
    Venue venue1 = new Venue("Venue 1");
    venue1.save();
    Venue venue2 = new Venue("Venue 2");
    venue2.save();
    Venue venue3 = new Venue("Venue 3");
    venue3.save();
    Venue venue4 = new Venue("Venue 4");
    venue4.save();
    venue1.addBand(band1);
    venue1.addBand(band2);
    venue2.addBand(band1);
    venue3.addBand(band2);
    venue4.addBand(band1);
    venue4.addBand(band2);
    goTo("http://localhost:4567/");
    click("a", withText("Band 1"));
    assertThat(pageSource()).contains("Venue 1");
    assertThat(pageSource()).contains("Venue 2");
    assertThat(pageSource()).contains("Venue 4");
  }

  public void updateBand() {
    Band band1 = new Band("Band 1");
    band1.save();
    goTo("http://localhost:4567/");
    click("a", withText("Band 1"));
    fill("#bandName").with("Band 2");
    assertThat(pageSource()).contains("Band 2");
  }

  public void updateVenue() {
    Venue venue1 = new Venue("Venue 1");
    venue1.save();
    goTo("http://localhost:4567/");
    click("a", withText("Venue 1"));
    fill("#venueName").with("Venue 2");
    assertThat(pageSource()).contains("Venue 2");
  }

  @Test
  public void deleteBand() {
    Band band1 = new Band("Band 1");
    band1.save();
    goTo("http://localhost:4567/");
    click(".deleteButton", withText("Delete"));
    assertThat(((pageSource()).contains("Band 1")) == false);
  }

  @Test
  public void deleteVenue() {
    Venue venue1 = new Venue("Venue 1");
    venue1.save();
    goTo("http://localhost:4567/");
    click(".deleteButton", withText("Delete"));
    assertThat(((pageSource()).contains("Venue 1")) == false);
  }
}
