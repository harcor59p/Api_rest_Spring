package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository ;

    @PostMapping
    public void registrarMedico(@RequestBody @Valid dtoDatosRegistroMedico datosRegistroMedico){
        System.out.println("El request llega correctamente");
        medicoRepository.save(new Medico(datosRegistroMedico) ) ;
    }

    @GetMapping
    public Page<dtoListadoMedicos> listadoMedicos(@PageableDefault(size = 4 ) Pageable paginacion){
        return medicoRepository.findByActivoTrue(paginacion).map(dtoListadoMedicos::new);
//        return medicoRepository.findAll(paginacion).map(dtoListadoMedicos::new);

    }

    @PutMapping
    @Transactional
    public void actualizarMedico(@RequestBody @Valid dtoActualizarMedico dtoActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(dtoActualizarMedico.id());
        medico.actualizarDatos(dtoActualizarMedico) ;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }

    @PutMapping("/inactivar/{id}")
    @Transactional
    public void inactivarMedico(@RequestBody @Valid dtoInactivarMedico dtoInactivarMedico){
        Medico medico = medicoRepository.getReferenceById(dtoInactivarMedico.id());
        medico.inactivarMedico(dtoInactivarMedico); ;
    }


}
