package tikape.runko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AiheDao;
import tikape.runko.database.Database;
import tikape.runko.database.KeskusteluDao;
import tikape.runko.domain.Aihe;
import tikape.runko.domain.Keskustelu;

public class Main {

    public static void main(String[] args) throws Exception {

        Database database = new Database("jdbc:sqlite:keskustelupalsta.db");
        database.init();

        AiheDao aiheDao = new AiheDao(database);
        

        List<Aihe> aiheet = new ArrayList<>();
        aiheet = aiheDao.findAll();

        for (Aihe aihe : aiheet) {
            System.out.println(aihe.getAihe());
            System.out.println();
        }

        get("/keskustelupalsta", (Request req, Response res) -> {
            HashMap map = new HashMap<>();
            map.put("teksti", "Aihealueet");
            map.put("aiheet", aiheDao.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        
        
        KeskusteluDao keskusteluDao = new KeskusteluDao(database, aiheDao);
        
        List<Keskustelu> keskustelut = new ArrayList<>();
        keskustelut = keskusteluDao.findAll();

        for (Keskustelu keskustelu : keskustelut) {
            System.out.println(keskustelu.getAlue());
            System.out.println(keskustelu.getKeskustelu());
            System.out.println(keskustelu.getKeskustelutunnus());
            System.out.println();
        }

        get("/keskustelut", (Request req, Response res) -> {
            HashMap map = new HashMap<>();
            map.put("teksti", "Keskustelut");
            map.put("keskustelut", keskusteluDao.findAll());

            return new ModelAndView(map, "keskustelu");
        }, new ThymeleafTemplateEngine());

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
