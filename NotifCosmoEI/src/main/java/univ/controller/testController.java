package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import univ.repository.ComponentRepository;
import univ.domain.Component;

import java.util.Iterator;

@Controller
public class testController {

    @Autowired
    ComponentRepository componentRepo;

    @RequestMapping("/addComponentTest")
    @ResponseBody
    public String addTestValue() {
        Component pere = new Component("A", null);
        componentRepo.save(pere);
        componentRepo.save(new Component("A fils", pere));
        componentRepo.save(new Component("A fils2", pere));
        componentRepo.save(new Component("B", null));

        return "It's done.";
    }

    @RequestMapping("/component/")
    @ResponseBody
    public String getComponents() {
        StringBuilder res = new StringBuilder();
        Iterator<Component> it = componentRepo.findAll().iterator();
        while(it.hasNext()) {
            Component element = it.next();
            res.append(element.getId() + " " + element.getName() + " | Père : "
                    + (element.getParent() == null ? "/" : element.getParent().getName()) + "<br/>");
        }
        return res.toString();
    }
}