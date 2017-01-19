package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Work;
import univ.repository.WorkRepository;

import java.util.List;


/**
 * <b>Service des works.</b>
 * Fonctionnalités de CRUD des works utilisés par l'application.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Service
public class WorkService {

    /**
     * Le repository des works.
     */
    @Autowired
    private WorkRepository workRepository;

    /**
     * La liste des works.
     * @return Une liste de tous les works.
     */
    public List<Work> getAll() {
        return workRepository.findAll();
    }
}
