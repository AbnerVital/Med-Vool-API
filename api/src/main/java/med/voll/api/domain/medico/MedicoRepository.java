package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT m FROM Medico m
            WHERE 
            m.ativo = true 
            AND 
            m.especialidade = :especialidade
            AND
            m.id not in(
                select c.medico.id from Consulta c where c.data = :data 
            )
            ORDER BY RANDOM()
            LIMIT 1
            """)
    Medico escolherMedicoAleatorioNaData(Especialidade especialidade, @NotNull @Future LocalDateTime data);
}
