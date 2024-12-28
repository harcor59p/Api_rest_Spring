package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.direccion.dtoDatosDireccion;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository ;

    @PostMapping
    public ResponseEntity<dtoDatosRespuestaMedico> registrarMedico(@RequestBody @Valid dtoDatosRegistroMedico datosRegistroMedico ,  UriComponentsBuilder uriComponetsBuilder){
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico)) ;
        dtoDatosRespuestaMedico dtoDatosRespuestaMedico = new dtoDatosRespuestaMedico(medico.getId() , medico.getNombre(), medico.getDocumento() , medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString()
                , new dtoDatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito() , medico.getDireccion().getDistrito() , medico.getDireccion().getCiudad()
                        , medico.getDireccion().getNumero() , medico.getDireccion().getComplemento()));
        URI url = uriComponetsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(dtoDatosRespuestaMedico) ;
    }

    @GetMapping
    public Page<dtoListadoMedicos> listadoMedicos(@PageableDefault(size = 4 ) Pageable paginacion){
        return medicoRepository.findByActivoTrue(paginacion).map(dtoListadoMedicos::new);
//        return medicoRepository.findAll(paginacion).map(dtoListadoMedicos::new);

    }

    @PutMapping
    @Transactional
    public ResponseEntity<dtoDatosRespuestaMedico> actualizarMedico(@RequestBody @Valid dtoActualizarMedico dtoActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(dtoActualizarMedico.id());
        medico.actualizarDatos(dtoActualizarMedico) ;
        return ResponseEntity.ok(new dtoDatosRespuestaMedico(medico.getId() , medico.getNombre(), medico.getDocumento() , medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString()
        , new dtoDatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito() , medico.getDireccion().getDistrito() , medico.getDireccion().getCiudad()
        , medico.getDireccion().getNumero() , medico.getDireccion().getComplemento())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/inactivar/{id}")
    @Transactional
    public ResponseEntity inactivarMedico(@RequestBody @Valid dtoInactivarMedico dtoInactivarMedico){
        Medico medico = medicoRepository.getReferenceById(dtoInactivarMedico.id());
        medico.inactivarMedico(dtoInactivarMedico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<dtoDatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        var datosmedicos = new dtoDatosRespuestaMedico(medico.getId() , medico.getNombre(), medico.getDocumento() , medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString()
                , new dtoDatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito() , medico.getDireccion().getDistrito() , medico.getDireccion().getCiudad()
                , medico.getDireccion().getNumero() , medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosmedicos);
    }


}
