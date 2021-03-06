import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("venues", Venue.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  get("/band/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params(":id")));
      model.put("band", band);
      model.put("venues", Venue.all());
      model.put("template", "templates/bandPage.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  get("/venue/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Venue venue = Venue.find(Integer.parseInt(request.params(":id")));
      model.put("venue", venue);
      model.put("bands", Band.all());
      model.put("template", "templates/venuePage.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  post("/addBand", (request, response) -> {
      String bandName = request.queryParams("bandName");
      Band newBand = new Band(bandName);
      newBand.save();
      response.redirect("/");
      return null;
    });

  post("/updateBand", (request, response) -> {
      String bandName = request.queryParams("bandName");
      int bandid = Integer.parseInt(request.queryParams("bandid"));
      Band newBand = Band.find(bandid);
      newBand.update(bandName);
      response.redirect("/band/" + bandid);
      return null;
    });

  post("/updateVenue", (request, response) -> {
      String venueName = request.queryParams("venueName");
      int venueid = Integer.parseInt(request.queryParams("venueid"));
      Venue newVenue = Venue.find(venueid);
      newVenue.update(venueName);
      response.redirect("/venue/" + venueid);
      return null;
    });

  post("/delete/band/:id", (request, response) -> {
      int id = Integer.parseInt(request.params(":id"));
      Band newBand = Band.find(id);
      newBand.delete();
      response.redirect("/");
      return null;
    });

  post("/addVenue", (request, response) -> {
      String venueName = request.queryParams("venueName");
      Venue newVenue = new Venue(venueName);
      newVenue.save();
      response.redirect("/");
      return null;
    });

  post("/delete/venue/:id", (request, response) -> {
      int id = Integer.parseInt(request.params(":id"));
      Venue newVenue = Venue.find(id);
      newVenue.delete();
      response.redirect("/");
      return null;
    });

  post("/addVenues", (request, response) -> {
      int venueid = Integer.parseInt(request.queryParams("venueid"));
      int bandid = Integer.parseInt(request.queryParams("bandid"));
      Band band = Band.find(bandid);
      Venue venue = Venue.find(venueid);
      band.addVenue(venue);
      response.redirect("/band/" + bandid);
      return null;
    });

  post("/addBands", (request, response) -> {
      int venueid = Integer.parseInt(request.queryParams("venueid"));
      int bandid = Integer.parseInt(request.queryParams("bandid"));
      Band band = Band.find(bandid);
      Venue venue = Venue.find(venueid);
      venue.addBand(band);
      response.redirect("/venue/" + venueid);
      return null;
    });

  post("/index/deleteBands", (request, response) -> {
      Band.deleteAllBands();
      response.redirect("/");
      return null;
    });

  post("/index/deleteVenues", (request, response) -> {
      Venue.deleteAllVenues();
      response.redirect("/");
      return null;
    });
  }
}
