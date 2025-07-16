package dsm.hackaton._8.domain.law.domain.repository;

import dsm.hackaton._8.domain.law.domain.Law;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LawRepository extends JpaRepository<Law, Long> {

    Optional<Law> findByLawSerialNumber(int serialNumber);
}
