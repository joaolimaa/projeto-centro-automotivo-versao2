package com.example.oficinaco.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import com.example.oficinaco.jpa.util.validadores.Validadores;

public class ValidadoresTest {

    @Test
    public void cpfDeveSerValidoParaCpfExistente() throws Exception {
        boolean cpfValido = Validadores.isCPF("70760583161");

        assertTrue(cpfValido);
    }

    @Test
    public void cpfDeveSerInvalidoParaCpfInexistente() throws Exception{
        boolean cpfInvalido = Validadores.isCPF("12839746192");

        assertFalse(cpfInvalido);
    }

    @Test
    public void cpfDeveSerInvalidoParaCpfComNumeroRepetidos() throws Exception {
        boolean cpfInvalido = Validadores.isCPF("00000000000");

        assertFalse(cpfInvalido);
    }

    @Test
    public void placaDeveSerValidaParaPlacaExistente() throws Exception {
        boolean placaValida = Validadores.validaPlaca("BRA0S19");

        assertTrue(placaValida);
    }

    @Test
    public void placaDeveSerInvalidaParaPlacaComCaracteresForaDoPadrao() throws Exception {
        boolean placaInvalida = Validadores.validaPlaca("IU2OI3112");

        assertFalse(placaInvalida);
    }

    @Test
    public void emailDeveSerValidoParaEmailExistente() throws Exception {
        boolean emailValido = Validadores.validaEmail("magnolevi2011@gmail.com");

        assertTrue(emailValido);
    }

    @Test
    public void emailDeveSerInvalidoParaEmailForaDoPadrao() throws Exception {
        boolean emailInvalido = Validadores.validaEmail("magnolevi.com");

        assertFalse(emailInvalido);
    }

    @Test
    public void telefoneDeveSerValidoParaTelefoneExistente() throws Exception {
        boolean telefoneValido = Validadores.validaTelefone(62, 993369755);

        assertTrue(telefoneValido);
    }

    @Test
    public void telefoneDeveSerInvalidoParaDddInvalido() throws Exception {
        boolean telefoneInvalido = Validadores.validaTelefone(100, 993369755);

        assertFalse(telefoneInvalido);
    }

    @Test
    public void telefoneDeveInvalidoParaNumeroDeTelefoneInvalido() throws Exception {
        boolean telefoneInvalido = Validadores.validaTelefone(100, 9975513);

        assertFalse(telefoneInvalido);
    }

}
