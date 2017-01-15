package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Component;
import univ.domain.Effect;
import univ.domain.Product;
import univ.domain.Region;
import univ.model.Report;

import java.util.*;

@Service
public class ReportService {
    @Autowired
    private ProductService productService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private EffectService effectService;
    @Autowired
    private ComponentService componentService;

    public Collection<Report> getReports(Product p) {
        return getReportsByDescription(p.getEffects());
    }
    public Collection<Report> getReports(Component c) {
        return getReportsByDescription(c.getInheritanceEffects());
    }

    public Collection<Report> getReportsByRegion(Region region) {
        return getReportsByDescription(effectService.getByRegion(region));
    }

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
