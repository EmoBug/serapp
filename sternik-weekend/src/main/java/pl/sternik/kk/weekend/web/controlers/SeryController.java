package pl.sternik.kk.weekend.web.controlers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.kk.weekend.entities.Ser;
import pl.sternik.kk.weekend.entities.Status;
import pl.sternik.kk.weekend.services.KlaserService;
import pl.sternik.kk.weekend.services.NotificationService;


@Controller
public class SeryController {

    @Autowired
    // @Qualifier("spring")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @GetMapping(value = "/sery/{id}")
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Ser> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Ser ser = result.get();
            model.addAttribute("ser", ser);
            return "ser";
        } else {
            notifyService.addErrorMessage("Cannot find ser #" + id);
            model.clear();
            return "redirect:/sery";
        }
    }

    @RequestMapping(value = "/sery/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Ser> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Ser> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Ser ser = result.get();
            return new ResponseEntity<Ser>(ser, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find ser #" + id);
            model.clear();
            return new ResponseEntity<Ser>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sery", params = { "save" }, method = RequestMethod.POST)
    public String saveSer(Ser ser, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "ser";
        }
        Optional<Ser> result = klaserService.edit(ser);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis udany");
        else
            notifyService.addErrorMessage("Zapis NIE udany");
        model.clear();
        return "redirect:/sery";
    }

    @RequestMapping(value = "/sery", params = { "create" }, method = RequestMethod.POST)
    public String createSer(Ser ser, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "ser";
        }
        klaserService.create(ser);
        model.clear();
        notifyService.addInfoMessage("Zapis nowego udany");
        return "redirect:/sery";
    }

    @RequestMapping(value = "/sery", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final Ser ser, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = klaserService.deleteById(rowId.longValue());
        return "redirect:/sery";
    }

    @RequestMapping(value = "/sery/create", method = RequestMethod.GET)
    public String showMainPages(final Ser ser) {
        // Ustawiamy date nowej monety, na dole strony do dodania
        ser.setDataNabycia(Calendar.getInstance().getTime());
        return "ser";
    }
}