package br.com.senai.aluno.domain.services;

import br.com.senai.aluno.domain.entities.Frequencia;
import br.com.senai.aluno.domain.exceptions.ExceptionTratement;
import br.com.senai.aluno.domain.repositories.FrequenciaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class FrequenciaService {

    private FrequenciaRepository frequenciaRepository;

    public Frequencia cadastrar(Frequencia frequencia) throws Exception {
        try {
            return frequenciaRepository.save(frequencia);
        } catch (Exception ex) {
            throw new ExceptionTratement("Não foi possível cadastrar a frequência!");
        }
    }

    public Optional<Frequencia> buscarPorAluno(long matricula) throws Exception {
        try {
            return frequenciaRepository.findByAlunoMatricula(matricula);
        } catch (Exception ex) {
            throw new ExceptionTratement("Não foi possível buscar a frequência do aluno!");
        }
    }

    public Frequencia editar(long id, Frequencia frequencia) throws Exception {
        try {
            frequencia.setId(id);

            return frequenciaRepository.save(frequencia);
        } catch (Exception ex) {
            throw new ExceptionTratement("Não foi possível editar a frequência!");
        }
    }

    public void deletar(long id) throws Exception {
        try {
            frequenciaRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ExceptionTratement("Não foi possível remover a frequência!");
        }
    }

}
