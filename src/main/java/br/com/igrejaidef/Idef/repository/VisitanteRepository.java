package br.com.igrejaidef.Idef.repository;

import br.com.igrejaidef.Idef.model.VisitanteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitanteRepository extends JpaRepository<VisitanteModel,Long> {
    VisitanteModel findById(long id);
}
