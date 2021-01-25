package com.markusdel.todolist;

import com.markusdel.todolist.exception.InvalidEmailException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Utils {

    public void validateEmail(String email) throws InvalidEmailException {
        // validar se possui o arroba
        String[] separandoOEmail = email.split("@");
        if(separandoOEmail.length != 2) new InvalidEmailException(email);

        //validar se o provedor é valido ou se terminar com o .com
        String[] tipoDeEmail = separandoOEmail[2].split(".");
        Boolean possuiPontoComNoFinal =  tipoDeEmail[tipoDeEmail.length-1].equals("com");

        if(tipoDeEmail.length != 2 || !possuiPontoComNoFinal) new InvalidEmailException(email);

        // array com provedores de email valido
        String[] provedoresDeEmailValidos = {"gmail", "hotmail", "outlook", "yahoo"};

        for (String emailValido: provedoresDeEmailValidos) {
            if(!tipoDeEmail[1].equals(emailValido)) {
                new InvalidEmailException(email);
            }
        }
    }
}
