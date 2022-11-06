package devtools.crud.repository;

import devtools.crud.model.NovellLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NovellLineRepository extends JpaRepository<NovellLine, Long> {
    Optional<NovellLine> findById(Long id);
}
