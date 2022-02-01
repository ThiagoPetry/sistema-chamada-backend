package br.com.senai.aluno.domain.utils;

import br.com.senai.aluno.api.model.output.AlunoOutputDTO;
import br.com.senai.aluno.domain.entities.Aluno;
import br.com.senai.aluno.domain.entities.Chamada;
import br.com.senai.aluno.domain.services.AlunoService;
import br.com.senai.aluno.domain.services.ChamadaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AlunoUtils {
    private AlunoService alunoService;
    private ChamadaService chamadaService;

    public Aluno cadastrar(Aluno aluno) throws Exception {
        Chamada chamada = new Chamada();

        //Cadastra na chamada como não presente, por padrão
        chamada.setPresenca(false);
        chamada.setMatricula_aluno(aluno.getMatricula());
        chamada.setData_hora(LocalDateTime.now());

        chamadaService.cadastrar(chamada);

        return alunoService.cadastrar(aluno);
    }

    public void deletar(long matricula) throws Exception {
        Chamada chamada = chamadaService.buscarPorAluno(matricula).get();

        chamadaService.deletar(chamada.getId());

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
                    chamadaService.buscarPorAluno(aluno.getMatricula()).get().isPresenca()
            );

            alunoOutputDTOs.add(alunoOutputDTO);
        }

        return alunoOutputDTOs;
    }
}
