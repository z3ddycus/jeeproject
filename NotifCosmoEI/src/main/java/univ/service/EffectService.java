package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.entity.Effect;
import univ.repository.EffectRepository;

import java.util.List;


@Service
public class EffectService {

    @Autowired
    private EffectRepository effectRepository;

    public Effect get(long id) {
        return effectRepository.findOne(id);
    }
    List<Effect> getAll() {
        List<Effect> result = effectRepository.findAll();
        return result;
    }

    public Effect create(Effect e) {
        return effectRepository.save(e);
    }
}