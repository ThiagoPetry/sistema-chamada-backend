package br.com.senai.aluno.api.controller;

import br.com.senai.aluno.api.model.input.ChamadaInputDTO;
import br.com.senai.aluno.api.model.output.ChamadaOutputDTO;
import br.com.senai.aluno.domain.utils.ChamadaUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/chamada")
public class ChamadaController {
    private ChamadaUtils chamadaUtils;

    @PutMapping
    public ResponseEntity<List<ChamadaOutputDTO>> chamada(@RequestBody List<ChamadaInputDTO> chamadaInputDTOS) throws Exception {
        return ResponseEntity.ok(chamadaUtils.fazerChamada(chamadaInputDTOS));
    }
}
