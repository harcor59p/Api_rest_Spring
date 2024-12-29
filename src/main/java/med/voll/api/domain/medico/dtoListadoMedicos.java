package med.voll.api.domain.medico;

public record dtoListadoMedicos(
        Long id ,
        String nombre ,
        String email ,
        String especialidad ,
        String documento
) {
    public dtoListadoMedicos(Medico medico) {
        this(medico.getId() , medico.getNombre() , medico.getEspecialidad().toString() , medico.getDocumento() , medico.getEmail()) ;
    }
}
