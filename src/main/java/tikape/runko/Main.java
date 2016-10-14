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
import tikape.runko.domain.Aihe;
import tikape.runko.domain.Keskustelu;

public class Main {

    public static void main(String[] args) throws Exception {
        port(getHerokuAssignedPort());

        AiheDao aiheDao = new AiheDao("jdbc:sqlite:keskustelupalsta.db");
        KeskusteluDao keskusteluDao = new KeskusteluDao("jdbc:sqlite:keskustelupalsta.db");
        
        Spark.get("/", (req, res) -> {
            res.redirect("/aiheet");
            return "ok";
        });
        
        Spark.get("/aiheet", (req, res) -> {
            HashMap data = new HashMap<>();
            data.put("aiheet", aiheDao.findAll());

            return new ModelAndView(data, "index");
        }, new ThymeleafTemplateEngine());
        
        
        Spark.post("/aiheet", (req, res) -> {
            aiheDao.luoAihe(req.queryParams("nimi"));
            res.redirect("/aiheet");
            return "ok";
        });
        
//          KESKEN
//        Spark.get("/aiheet/:aihe", (req, res) -> {
//            HashMap data = new HashMap<>();
//            String aiheNimi = aiheDao.findOne(req.params(":aihe"));
//            data.put("keskustelut", keskusteluDao.haeAiheenKt(aiheNimi);
//            data.put("aihe", aiheDao.findOne(Integer.parseInt(req.params(":aihe_id"))));
//
//            return new ModelAndView(data, "aiheenKeskustelut");
//        }, new ThymeleafTemplateEngine());
    
        
        Spark.get("/keskustelut", (req, res) -> {
            HashMap data = new HashMap<>();
            data.put("keskustelu", keskusteluDao.findAll());

            return new ModelAndView(data, "keskustelu");
        }, new ThymeleafTemplateEngine());

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
//           get("/", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("aiheet", AiheDao.findAll());
//
//            return new ModelAndView(map, "index");
//        }, new ThymeleafTemplateEngine());
//        
//        get("/", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("viesti", "tervehdys");
//
//            return new ModelAndView(map, "index");
//        }, new ThymeleafTemplateEngine());
//
//        get("/opiskelijat", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("opiskelijat", opiskelijaDao.findAll());
//
//            return new ModelAndView(map, "opiskelijat");
//        }, new ThymeleafTemplateEngine());
//
//        get("/opiskelijat/:id", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("opiskelija", opiskelijaDao.findOne(Integer.parseInt(req.params("id"))));
//
//            return new ModelAndView(map, "opiskelija");
//        }, new ThymeleafTemplateEngine());
//    }
//}
