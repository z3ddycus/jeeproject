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

/**
 * <b>Contrôleur d'un rapport d'effets.</b>
 *
 * Définit les actions à suivre selon la requête CRUD invoquée.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Controller
@RequestMapping("/report")
public class ReportController {
    /**
     * Service de rapport.
     */
    @Autowired
    private ReportService reportService;
    /**
     * Service de région.
     */
    @Autowired
    private RegionService regionService;

    /**
     * Lors d'une requête <i>GET</i>, retourne l'intégralité des rapports d'effets
     * indésirables selon la région mentionnée.
     * @param model Le modèle de la requête.
     * @param id L'identifiant de la région.
     * @return La réponse de la requête.
     */
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
