package br.com.senai.aluno.domain.utils;

import br.com.senai.aluno.api.model.output.AlunoOutputDTO;
import br.com.senai.aluno.domain.entities.Aluno;
import br.com.senai.aluno.domain.entities.Frequencia;
import br.com.senai.aluno.domain.services.AlunoService;
import br.com.senai.aluno.domain.services.FrequenciaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AlunoUtils {
    private AlunoService alunoService;
    private FrequenciaService frequenciaService;

    public Aluno cadastrar(Aluno aluno) throws Exception {
        Frequencia frequencia = new Frequencia();

        frequencia.setStatus(false);
        frequencia.setMatricula_aluno(aluno.getMatricula());

        frequenciaService.cadastrar(frequencia);

        return alunoService.cadastrar(aluno);
    }

    public void deletar(long matricula) throws Exception {
        Frequencia frequencia = frequenciaService.buscarPorAluno(matricula).get();

        frequenciaService.deletar(frequencia.getId());

        alunoService.deletar(matricula);
    }

    public List<AlunoOutputDTO> listar() throws Exception {
        List<AlunoOutputDTO> alunoOutputDTOs = new ArrayList<>();

        List<Aluno> alunos = alunoService.listar();

        for (Aluno aluno : alunos) {
            AlunoOutputDTO alunoOutputDTO = new AlunoOutputDTO();

            alunoOutputDTO.setCpf(aluno.getCpf());
            alunoOutputDTO.setMatricula(aluno.getMatricula());
            alunoOutputDTO.setNome(aluno.getNome());
            alunoOutputDTO.setFrequencia(
                    frequenciaService.buscarPorAluno(aluno.getMatricula()).get().isStatus()
            );

            alunoOutputDTOs.add(alunoOutputDTO);
        }

        return alunoOutputDTOs;
    }
}
