package med.voll.api.medico;

import med.voll.api.direccion.dtoDatosDireccion;

public record dtoDatosRegistroMedico(
        String nombre ,
        String email ,
        String documento ,
        Especialidad especialidad ,
        dtoDatosDireccion direccion
) {
}
