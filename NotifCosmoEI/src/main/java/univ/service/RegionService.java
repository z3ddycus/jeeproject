package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Region;
import univ.repository.RegionRepository;

import java.util.Collection;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public Collection<Region> getAll() {
        return regionRepository.findAll();
    }

    public Region get(long id) {
        return regionRepository.findOne(id);
    }
}
