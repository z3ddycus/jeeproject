package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Component;
import univ.domain.Effect;
import univ.domain.Product;
import univ.domain.Region;
import univ.model.Report;

import java.util.*;

/**
 * Le service des rapports d'alertes.
 */
@Service
public class ReportService {

    // ATTRIBUTES

    /**
     * Le service des produits.
     */
    @Autowired
    private ProductService productService;

    /**
     * Le service des régions.
     */
    @Autowired
    private RegionService regionService;

    /**
     * Le service des effets indésirables.
     */
    @Autowired
    private EffectService effectService;

    /**
     * Le service des composants.
     */
    @Autowired
    private ComponentService componentService;


    // REQUESTS

    /**
     * Récupère les rapports associés à un produit triés par poids décroissants.
     */
    public Collection<Report> getReports(Product p) {
        return getReportsByDescription(p.getEffects());
    }

    /**
     * Récupère les rapports associés à un composant triés par poids décroissants.
     */
    public Collection<Report> getReports(Component c) {
        return getReportsByDescription(c.getInheritanceEffects());
    }

    /**
     * Récupère les rapports associés à une région triés par poids décroissants.
     */
    public Collection<Report> getReportsByRegion(Region region) {
        return getReportsByDescription(effectService.getByRegion(region));
    }

    /**
     * Récupère tous les rapports associés aux noms de composants.
     */
    public SortedMap<Report, Set<String>> getReportsWithComponentNames() {
        SortedMap<Report, Set<String>> result = new TreeMap<>();
        for (Component c : componentService.getAll()) {
            Collection<Report> reports = getReports(c);
            for (Report report : reports) {
                Set<String> composants = result.get(report);
                if (composants == null) {
                    composants = new TreeSet<>();
                    result.put(report, composants);
                }
                composants.add(c.getName());
            }
        }
        return result;
    }

    /**
     * Modifie un rapport d'alerte selon un rapport.
     */
    private void actualizeReport(Report report, Effect effect) {
        if (report.getLastDeclare() == null || report.getLastDeclare().before(effect.getDate())) {
            report.setLastDeclare(effect.getDate());
        }
        if (report.getFirstDeclare() == null || report.getFirstDeclare().after(effect.getDate())) {
            report.setUserDeclare(effect.getUser());
            report.setFirstDeclare(effect.getDate());
        }
        report.getRegions().add(effect.getUser().getRegion());
        report.setWeight(report.getWeight() + effect.getUser().getWork().getWeight());
    }

    /**
     * Construit un rapport selon une liste d'effets indésirables.
     */
    private Collection<Report> getReportsByDescription(Collection<Effect> effects) {
        Map<String, Report> descriptionToReport = new HashMap<>();
        for (Effect effect : effects) {
            Report r = descriptionToReport.get(effect.getDescription());
            if (r == null) {
                r = new Report(effect.getDescription());
                descriptionToReport.put(effect.getDescription(), r);
            }
            actualizeReport(r, effect);
        }
        return descriptionToReport.values();
    }

}
