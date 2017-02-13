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
 * <b>Service de rapports d'alertes.</b>
 * Contruction des différents rapports d'alertes
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
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
     * @param p Le product
     * @return La collection des report associé au produit p
     */
    public Collection<Report> getReports(Product p) {
        return getReportsByDescription(p.getEffects());
    }

    /**
     * Récupère les rapports associés à un composant triés par poids décroissants.
     * @param c Le composant
     * @return La collection des report associé au composant c
     */
    public Collection<Report> getReports(Component c) {
        return getReportsByDescription(c.getInheritanceEffects());
    }

    /**
     * Récupère les rapports associés à une région triés par poids décroissants.
     * @param region La région
     * @return La collection des report associé à la région.
     */
    public SortedMap<Report, Set<String>> getReportsByRegion(Region region) {
        SortedMap<Report, Set<String>> result = new TreeMap<>();
        for (Component c : componentService.getAllByRegion(region)) {
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
     * La liste tous les reports séparés par composant.
     * @return La liste tous les reports séparés par composant.
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
     * Actualise un rapport d'alerte avec un effet.
     * @param report le rapport a mettre à jour
     * @param effect l'effet servant a mettre à jour
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
     * La collection des rapports d'alertes générés selon une collection d'effets indésirables.
     * @param effects Les effets servant à la génération
     * @return La liste des rapports générés.
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
