package vitorbessoni.Clinica.services;

import vitorbessoni.Clinica.dao.ConsultaDAO;
import vitorbessoni.Clinica.model.Consulta;
import vitorbessoni.Clinica.model.Dentista;
import vitorbessoni.Clinica.model.Paciente;
import vitorbessoni.Clinica.repository.ConsultaRepository;
import vitorbessoni.Clinica.repository.DentistaRepository;
import vitorbessoni.Clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.apache.log4j.Logger;

@Service
public class ConsultaService {

    private static final Logger logger = Logger.getLogger(ConsultaService.class.getName());

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public Consulta insert(ConsultaDAO dao){
        Dentista dentista = dentistaRepository.getById(dao.getDentistaId());
        Paciente paciente = pacienteRepository.getById(dao.getPacienteId());

        Consulta consulta = new Consulta(dao.getData(), dao.getHora(), dentista, paciente);
        logger.info("Consulta criada!");

        return consultaRepository.save(consulta);
    }

    public List<Consulta> buscarConsultas(){
        logger.info("Busca por todas as consultas");
        return consultaRepository.findAll();
    }

    public Consulta buscarPorID(Integer id){
        logger.info("Buscar Consulta pelo ID concluida");
        return consultaRepository.getById(id);
    }

    public Consulta edit(ConsultaDAO dao){
        Consulta consulta = consultaRepository.getById(dao.getId());

        consulta.setData(dao.getData());
        consulta.setHora(dao.getHora());

        consultaRepository.save(consulta);
        logger.info("Consulta alterada");
        return consulta;
    }
    public String delete(Integer id){
        consultaRepository.deleteById(id);
        logger.info("Consulta excluida");
        return "Excluido com sucesso";
    }

}
