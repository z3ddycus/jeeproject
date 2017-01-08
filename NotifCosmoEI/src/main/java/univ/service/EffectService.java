package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Effect;
import univ.repository.EffectRepository;

import java.util.Collections;
import java.util.List;


@Service
public class EffectService {

    @Autowired
    EffectRepository effectRepository;

    Effect get(long id) {
        return effectRepository.findOne(id);
    }
    List<Effect> getAll() {
        List<Effect> result = effectRepository.findAll();
        Collections.sort(result);
        return result;
    }
    void save(Effect e) {
        effectRepository.save(e);
    }
}