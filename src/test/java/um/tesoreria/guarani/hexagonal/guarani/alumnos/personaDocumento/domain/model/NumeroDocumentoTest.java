package um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumeroDocumentoTest {

    @Test
    void putsLeadingLettersInPrefijo() {
        var numero = NumeroDocumento.parse("AA1234567").orElseThrow();

        assertThat(numero.getPrefijo()).isEqualTo("AA");
        assertThat(numero.getDigitos()).isEqualTo("1234567");
        assertThat(numero.getPosfijo()).isEmpty();
    }

    @Test
    void putsTrailingLettersInPosfijo() {
        var numero = NumeroDocumento.parse("1234567B").orElseThrow();

        assertThat(numero.getPrefijo()).isEmpty();
        assertThat(numero.getDigitos()).isEqualTo("1234567");
        assertThat(numero.getPosfijo()).isEqualTo("B");
    }

    @Test
    void splitsLettersOnBothSides() {
        var numero = NumeroDocumento.parse("ab1234567cd").orElseThrow();

        assertThat(numero.getPrefijo()).isEqualTo("AB");
        assertThat(numero.getDigitos()).isEqualTo("1234567");
        assertThat(numero.getPosfijo()).isEqualTo("CD");
    }

    @Test
    void keepsPlainNumbersUntouched() {
        var numero = NumeroDocumento.parse("1234567").orElseThrow();

        assertThat(numero.getPrefijo()).isEmpty();
        assertThat(numero.getDigitos()).isEqualTo("1234567");
        assertThat(numero.getPosfijo()).isEmpty();
    }

    @Test
    void ignoresSeparatorsAroundTheNumber() {
        var numero = NumeroDocumento.parse(" 12.345.678-X ").orElseThrow();

        assertThat(numero.getPrefijo()).isEmpty();
        assertThat(numero.getDigitos()).isEqualTo("12345678");
        assertThat(numero.getPosfijo()).isEqualTo("X");
    }

    @Test
    void ignoresBpCharPadding() {
        var numero = NumeroDocumento.parse("1234567   ").orElseThrow();

        assertThat(numero.getDigitos()).isEqualTo("1234567");
        assertThat(numero.getPosfijo()).isEmpty();
    }

    @Test
    void returnsEmptyWhenThereAreNoDigits() {
        assertThat(NumeroDocumento.parse("ABC")).isEmpty();
        assertThat(NumeroDocumento.parse("   ")).isEmpty();
        assertThat(NumeroDocumento.parse(null)).isEmpty();
    }

    @Test
    void returnsEmptyWhenOnlySeparatorsArePresent() {
        assertThat(NumeroDocumento.parse("--..--")).isEmpty();
    }

    @Test
    void extractsDigitsIgnoringAnyFormat() {
        assertThat(NumeroDocumento.soloDigitos("AA12.345-678B")).isEqualTo("12345678");
        assertThat(NumeroDocumento.soloDigitos("ABC")).isEmpty();
        assertThat(NumeroDocumento.soloDigitos(null)).isEmpty();
    }

}
