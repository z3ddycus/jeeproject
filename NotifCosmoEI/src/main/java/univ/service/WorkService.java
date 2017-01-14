package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Work;
import univ.repository.WorkRepository;

import java.util.List;

@Service
public class WorkService {
    @Autowired
    private WorkRepository workRepository;

    public List<Work> getAll() {
        return workRepository.findAll();
    }
}
