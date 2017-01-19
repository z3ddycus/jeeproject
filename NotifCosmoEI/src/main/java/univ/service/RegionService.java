package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Region;
import univ.repository.RegionRepository;

import java.util.Collection;


/**
 * <b>Service de régions.</b>
 * Fonctionnalités de CRUD des régions utilisés par l'application.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
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
     * La collection de toutes les régions.
     * @return La collection de toutes les régions.
     */
    public Collection<Region> getAll() {
        return regionRepository.findAll();
    }

    /**
     * La région associé à l'id id.
     * @param id L'id associé
     * @return La région associé à l'id.
     */
    public Region get(long id) {
        return regionRepository.findOne(id);
    }
}
