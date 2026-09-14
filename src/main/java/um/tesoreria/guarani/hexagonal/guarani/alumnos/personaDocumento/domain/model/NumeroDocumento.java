package um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.domain.model;

import lombok.*;

import java.util.Locale;
import java.util.Optional;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NumeroDocumento {

    private final String prefijo;
    private final String digitos;
    private final String posfijo;

    public static Optional<NumeroDocumento> parse(String nroDocumento) {
        if (nroDocumento == null || nroDocumento.isBlank()) {
            return Optional.empty();
        }
        String valor = nroDocumento.trim();

        int desde = 0;
        while (desde < valor.length() && Character.isLetter(valor.charAt(desde))) {
            desde++;
        }
        int hasta = valor.length();
        while (hasta > desde && Character.isLetter(valor.charAt(hasta - 1))) {
            hasta--;
        }

        String digitos = soloDigitos(valor.substring(desde, hasta));
        if (digitos.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(NumeroDocumento.builder()
                .prefijo(valor.substring(0, desde).toUpperCase(Locale.ROOT))
                .digitos(digitos)
                .posfijo(valor.substring(hasta).toUpperCase(Locale.ROOT))
                .build());
    }

    public static String soloDigitos(String valor) {
        if (valor == null || valor.isEmpty()) {
            return "";
        }
        StringBuilder digitos = new StringBuilder(valor.length());
        valor.chars().filter(Character::isDigit).forEach(codigoPunto -> digitos.append((char) codigoPunto));
        return digitos.toString();
    }

}
