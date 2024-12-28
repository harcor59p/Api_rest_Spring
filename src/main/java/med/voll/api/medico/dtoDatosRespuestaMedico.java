package med.voll.api.medico;


import med.voll.api.direccion.dtoDatosDireccion;

public record dtoDatosRespuestaMedico(long id , String nombre ,  String documento , String email , String telefono , String especialidad ,
                                       dtoDatosDireccion direcion ) {
}
