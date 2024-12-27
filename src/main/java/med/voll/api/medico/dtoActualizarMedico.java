package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.direccion.Direccion;

public record dtoActualizarMedico(@NotNull Long id , String nombre , String documento , Direccion direccion) {

}
