package com.projecto.serviconotas.exception;

import java.util.List;

public class RespostaErro {
    
    private List<String> message;


    public RespostaErro(List<String> message) {
        this.message = message;
    }

    public List<String> getMessage() {
        return this.message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

}
