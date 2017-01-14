package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String getAll(Model model) {
        model.addAttribute("reports", reportService.getReportsWithComponentNames());
        model.addAttribute("regions", regionService.getAll());
        return "allReport";
    }
}
