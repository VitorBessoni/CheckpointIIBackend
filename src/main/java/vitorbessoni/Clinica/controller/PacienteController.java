package vitorbessoni.Clinica.controller;

import vitorbessoni.Clinica.dao.PacienteDAO;
import vitorbessoni.Clinica.model.Paciente;
import vitorbessoni.Clinica.services.ConsultaService;
import vitorbessoni.Clinica.services.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    private static final Logger logger = Logger.getLogger(ConsultaService.class.getName());

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<Paciente> postPaciente(@RequestBody Paciente paciente){
        try {
        return ResponseEntity.ok(service.insert(paciente));

        }catch (Exception e){
            logger.error(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getPacientes(){
        try{
            return ResponseEntity.ok(service.buscarTodos());
        }catch (Exception e){
            logger.error(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteByID(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(service.buscarPorID(id));
        }catch (Exception e){
            logger.error(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Paciente> putPaciente(@RequestBody PacienteDAO dao){
        try {
            return ResponseEntity.ok(service.edit(dao));
        }catch (Exception e){
            logger.error(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(service.delete(id));
        }catch (Exception e){
            logger.error(e);
            return ResponseEntity.badRequest().build();
        }
    }
}
