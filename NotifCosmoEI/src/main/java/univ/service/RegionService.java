package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Region;
import univ.repository.RegionRepository;

import java.util.Collection;

/**
 * Le service des régions.
 */
@Service
public class RegionService {

    // ATTRIBUTES

    /**
     * Le repository des régions.
     */
    @Autowired
    private RegionRepository regionRepository;

    // REQUESTS

    /**
     * La liste de toutes les régions.
     */
    public Collection<Region> getAll() {
        return regionRepository.findAll();
    }

    /**
     * La région associé à l'id id.
     */
    public Region get(long id) {
        return regionRepository.findOne(id);
    }
}
