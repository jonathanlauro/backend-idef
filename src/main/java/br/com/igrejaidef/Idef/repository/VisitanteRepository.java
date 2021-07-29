package br.com.igrejaidef.Idef.repository;

import br.com.igrejaidef.Idef.model.VisitanteModel;
import br.com.igrejaidef.Idef.model.VisitantePessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitanteRepository extends JpaRepository<VisitanteModel,Long> {
    VisitanteModel findById(long id);

    @Query("select distinct new br.com.igrejaidef.Idef.model.VisitantePessoa(a.nome,a.telefone) from VisitanteModel a ")
    List<VisitantePessoa> listarDistict();
}
