package pl.sternik.kk.weekend.web.controlers;

import java.util.Date;

import javax.ws.rs.HeaderParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.kk.weekend.entities.Ser;
import pl.sternik.kk.weekend.entities.Status;
import pl.sternik.kk.weekend.repositories.SerAlreadyExistsException;
import pl.sternik.kk.weekend.repositories.SeryRepository;
import pl.sternik.kk.weekend.repositories.NoSuchSerException;



@Controller
public class WprawkiController {

    @Autowired
    @Qualifier("tablica")
    SeryRepository baza;
    
    @RequestMapping(path = "/wprawki", method = RequestMethod.GET)
    public String wprawki(ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "wprawki";
    }

    @GetMapping("/wprawki/{cos}")
    public String wprawki(@PathVariable String cos, ModelMap model) {
        model.addAttribute("cos", cos);
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "wprawki";
    }

    @GetMapping("/wprawki2")
    @ResponseBody
    public String wprawkiParam(@RequestParam("cos") String cosParam, ModelMap model) {
        return "Wprawki z param cos=" + cosParam;
    }
    
    @GetMapping("/wprawki3")
    @ResponseBody
    public String wprawkiHeader(@RequestHeader("User-Agent") String cosParam, ModelMap model) {
        return "Uzywasz przegladarki:=" + cosParam;
    }
    
    @GetMapping(value = "/wprawki/monety/{id}/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Ser> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Ser s;
        try {
            s = baza.readById(id);
            return new ResponseEntity<Ser>(s, HttpStatus.OK);
            
        } catch (NoSuchSerException e) {
            System.out.println(e.getClass().getName());
            s = new Ser();
            s.setNumerKatalogowy(id);
            s.setKrajPochodzenia("Polska");
            s.setStatus(Status.NOWY);
            try {
                baza.create(s);
            } catch (SerAlreadyExistsException e1) {
                System.out.println(e1.getClass().getName());
            }
            return new ResponseEntity<Ser>(s, HttpStatus.CREATED);
        }
    }

}
