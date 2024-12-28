package med.voll.api.direccion;

import jakarta.validation.constraints.NotBlank;

public record dtoDatosDireccion(
        @NotBlank
        String calle ,
        @NotBlank
        String distrito ,
        @NotBlank
        String ciudad ,
        @NotBlank
        String numero ,
        @NotBlank
        String complemento,
        String s) {
}
