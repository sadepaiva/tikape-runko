package tikape.runko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AiheDao;
import tikape.runko.database.Database;
import tikape.runko.database.KeskusteluDao;
import tikape.runko.database.ViestiDao;
import tikape.runko.domain.Aihe;
import tikape.runko.domain.Keskustelu;
import tikape.runko.domain.Viesti;

public class Main {

    public static void main(String[] args) throws Exception {
        port(getHerokuAssignedPort());

        AiheDao aiheDao = new AiheDao("jdbc:sqlite:keskustelupalsta.db");
        KeskusteluDao keskusteluDao = new KeskusteluDao("jdbc:sqlite:keskustelupalsta.db");
        ViestiDao viestiDao = new ViestiDao("jdbc:sqlite:keskustelupalsta.db");
        
        Spark.get("/", (req, res) -> {
            res.redirect("/aiheet");
            return "ok";
        });
        
        Spark.get("/aiheet", (req, res) -> {
            HashMap data = new HashMap<>();
            data.put("aiheet", aiheDao.aiheenViestit());

            return new ModelAndView(data, "index");
        }, new ThymeleafTemplateEngine());
        
        
        Spark.post("/aiheet", (req, res) -> {
            aiheDao.luoAihe(req.queryParams("nimi"));
            res.redirect("/aiheet");
            return "ok";
        });
        
        
        Spark.get("/aiheet/:aihe", (req, res) -> {
            HashMap data = new HashMap<>();
            int aiheId = aiheDao.findOne(req.params(":aihe"));
            data.put("keskustelut", keskusteluDao.keskustelunViestit(aiheId));
            data.put("aihe", req.params(":aihe"));

            return new ModelAndView(data, "aiheenKeskustelut");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/aiheet/:aihe", (req, res) -> {
            int aiheId = aiheDao.findOne(req.params(":aihe"));
            keskusteluDao.luoKeskustelu(req.queryParams("keskustelu"), (aiheId));

            res.redirect("/aiheet/" + req.params(":aihe"));
            return "ok";
        });
        
        Spark.get("/keskustelut/:keskustelu", (req, res) -> {
            HashMap data = new HashMap<>();
            int keskusteluId = keskusteluDao.findOne(req.params(":keskustelu"));
            data.put("viestit", viestiDao.haeKeskustelunVt(keskusteluId));
            data.put("keskustelu", req.params(":keskustelu"));

            return new ModelAndView(data, "keskustelunViestit");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/keskustelut/:keskustelu", (req, res) -> {
            int keskusteluId = keskusteluDao.findOne(req.params(":keskustelu"));
            viestiDao.luoViesti(req.queryParams("viesti"), (keskusteluId), req.queryParams("nimimerkki"));

            res.redirect("/keskustelut/" + req.params(":keskustelu"));
            return "ok";
        });
        
        
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
