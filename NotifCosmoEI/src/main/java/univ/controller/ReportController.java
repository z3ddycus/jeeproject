package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import univ.domain.Region;
import univ.service.RegionService;
import univ.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @Autowired
    private RegionService regionService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getAll(Model model, @RequestParam(value="region", defaultValue = "-1") String id) {
        model.addAttribute("regions", regionService.getAll());
        long longId = Long.parseLong(id);
        Region r = regionService.get(longId);
        if (r == null) {
            model.addAttribute("titre", "Liste des alertes");
            model.addAttribute("reports", reportService.getReportsWithComponentNames());
        } else {
            model.addAttribute("titre", "Liste des alertes pour la région " + r.getName());
            model.addAttribute("reports", reportService.getReportsByRegion(r));
        }
        return "allReport";
    }
}
