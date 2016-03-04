import java.util.HashMap;
import java.util.List;
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

  post("/addBand", (request, response) -> {
      String bandName = request.queryParams("bandName");
      Band newBand = new Band(bandName);
      newBand.save();
      response.redirect("/");
      return null;
    });

  post("/delete/band/:id", (request, response) -> {
      int id = Integer.parseInt(request.params(":id"));
      Band.deleteBand(id);
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
      Venue.delete(id);
      response.redirect("/");
      return null;
    });
  }
}
