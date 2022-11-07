package devtools.crud.service;

import devtools.crud.model.NovellLine;
import devtools.crud.repository.NovellLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Responsible for saving novell lines
 */
@Service
public class NovellLineService {
    private NovellLineRepository novellLineRepository;

    @Autowired
    public NovellLineService(NovellLineRepository novellLineRepository) {
        this.novellLineRepository = novellLineRepository;
    }

    /**
     *
     * @param novellLine Line to save
     */
    public void save(NovellLine novellLine) {
        novellLineRepository.save(novellLine);
    }

    /**
     *
     * @param id
     * @return
     */
    public NovellLine findById(Long id) {
        return novellLineRepository.findById(id).orElse(new NovellLine());
    }
}
