package br.com.senai.aluno.domain.services;

import br.com.senai.aluno.domain.entities.Chamada;
import br.com.senai.aluno.domain.exceptions.ExceptionTratement;
import br.com.senai.aluno.domain.repositories.ChamadaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ChamadaService {

    private ChamadaRepository chamadaRepository;

    public Chamada cadastrar(Chamada chamada) throws Exception {
        try {
            return chamadaRepository.save(chamada);
        } catch (Exception ex) {
            throw new ExceptionTratement("Não foi possível cadastrar a frequência!");
        }
    }

    public Optional<Chamada> buscarPorAluno(long matricula) throws Exception {
        try {
            return chamadaRepository.findByMatriculaAluno(matricula);
        } catch (Exception ex) {
            throw new ExceptionTratement("Não foi possível buscar a frequência do aluno!");
        }
    }

    public Chamada editar(long id, Chamada chamada) throws Exception {
        try {
            chamada.setId(id);

            return chamadaRepository.save(chamada);
        } catch (Exception ex) {
            throw new ExceptionTratement("Não foi possível editar a frequência!");
        }
    }

    public void deletar(long id) throws Exception {
        try {
            chamadaRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ExceptionTratement("Não foi possível remover a frequência!");
        }
    }

}
