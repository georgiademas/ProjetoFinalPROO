package br.com.smartlifeti.estagioweb.model.vo;

import java.util.Calendar;

public class Convenio {
    private Calendar assinatura, encerramento;

    public Convenio(Calendar assinatura) {
        this.assinatura = assinatura;
        this.encerramento = assinatura;
        encerramento.set(Calendar.YEAR, this.encerramento.get(Calendar.YEAR) + 1);
    }
}
