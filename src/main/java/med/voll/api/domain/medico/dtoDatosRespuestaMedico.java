package med.voll.api.domain.medico;


import med.voll.api.domain.direccion.dtoDatosDireccion;

public record dtoDatosRespuestaMedico(long id , String nombre ,  String documento , String email , String telefono , String especialidad ,
                                       dtoDatosDireccion direcion ) {
}
