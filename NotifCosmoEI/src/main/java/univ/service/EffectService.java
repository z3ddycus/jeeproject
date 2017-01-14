package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import univ.domain.Component;
import univ.domain.Effect;
import univ.domain.Product;
import univ.domain.Region;
import univ.repository.EffectRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class EffectService {

    @Autowired
    private EffectRepository effectRepository;
    @Autowired
    private UserService userService;

    public Effect get(long id) {
        return effectRepository.findOne(id);
    }

    public List<Effect> getAll() {
        return effectRepository.findAll();
    }

    public Set<Effect> getByRegion(Region region) {
        return effectRepository.findByRegion(region);
    }

    public Set<String> getAllDescription() {
        return effectRepository.findAllDescription();
    }
    public Set<Effect> getByProduct(Product product) {
        Set<Effect> result = new HashSet<>();
        for (Component c : product.getComponents()) {
            result.addAll(effectRepository.findByComponent(c));
        }
        return result;
    }

    public Effect create(Effect e) {
        if (e.getUser() == null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            e.setUser(userService.findByMail(((UserDetails)principal).getUsername()));
        }
        return effectRepository.save(e);
    }

}