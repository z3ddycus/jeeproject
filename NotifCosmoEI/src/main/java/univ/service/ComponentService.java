package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Component;
import univ.repository.ComponentRepository;


@Service
public class ComponentService {

    @Autowired
    ComponentRepository componentRepository;

    Component get(long id) {
        return null;
    }
    void save(Component e) {
        componentRepository.save(e);
    }
    void delete(Component e) {
        componentRepository.delete(e);
    }
}
