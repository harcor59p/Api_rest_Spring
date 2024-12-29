package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.Direccion;

public record dtoActualizarMedico(@NotNull Long id , String nombre , String documento , Direccion direccion) {

}
