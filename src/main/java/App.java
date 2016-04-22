import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/results", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/results.vtl");

      String inputWords = request.queryParams("userInput");

      CodeReviewWeek1 newCodeReview = new CodeReviewWeek1();
      String results = newCodeReview.runCodeReview(inputWords);

      model.put("results", results);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/guess", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/guess.vtl");

      String userGuess = request.queryParams("userGuess");
      String inputWords = request.queryParams("userInput");

      model.put("userGuess", userGuess);
      model.put("inputWords", inputWords);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
