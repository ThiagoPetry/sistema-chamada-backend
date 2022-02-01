package br.com.senai.aluno.domain.utils;

import br.com.senai.aluno.api.model.input.ChamadaInputDTO;
import br.com.senai.aluno.api.model.output.ChamadaOutputDTO;
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
public class ChamadaUtils {
    private ChamadaService chamadaService;
    private AlunoService alunoService;

    private Chamada editar(ChamadaInputDTO chamadaInputDTO) throws Exception {
        Chamada novaChamada = chamadaService.buscarPorAluno(
                alunoService.buscarPorNome(chamadaInputDTO.getNome()).get().getMatricula()).get();

        novaChamada.setPresenca(chamadaInputDTO.isPresenca());

        return chamadaService.editar(novaChamada.getId(), novaChamada);
    }

    public List<ChamadaOutputDTO> fazerChamada(List<ChamadaInputDTO> chamadaInputDTOS) throws Exception {
        List<ChamadaOutputDTO> chamadaOutputDTOS = new ArrayList<>();

        for (ChamadaInputDTO chamadaInputDTO : chamadaInputDTOS) {
            Chamada chamada = editar(chamadaInputDTO);

            ChamadaOutputDTO chamadaOutputDTO = new ChamadaOutputDTO();
            chamadaOutputDTO.setNome(alunoService.buscar(chamada.getMatricula_aluno()).get().getNome());
            chamadaOutputDTO.setPresenca(chamadaInputDTO.isPresenca());
            chamadaOutputDTO.setData_hora(LocalDateTime.now());

            chamadaOutputDTOS.add(chamadaOutputDTO);
        }

        return chamadaOutputDTOS;
    }
}
