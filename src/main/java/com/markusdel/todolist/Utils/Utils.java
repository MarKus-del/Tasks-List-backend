package com.markusdel.todolist.Utils;

import com.markusdel.todolist.exception.InvalidEmailException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Utils {

    public void validateEmail(String email) throws InvalidEmailException {
        // validar se possui o arroba
        String[] separandoOEmail = email.split("@");
        if(separandoOEmail.length != 2) throw new InvalidEmailException(email);

        //validar se o provedor é valido ou se terminar com o .com

        String[] tipoDeEmail = separandoOEmail[1].split("\\.");
        Boolean possuiPontoComNoFinal =  tipoDeEmail[tipoDeEmail.length -1].equals("com");

        if(tipoDeEmail.length != 2 || !possuiPontoComNoFinal) throw new InvalidEmailException(email);

        // array com provedores de email valido
        String[] provedoresDeEmailValidos = {"gmail", "hotmail", "outlook", "yahoo"};
        Boolean provedorValido = false;

        for (String emailValido: provedoresDeEmailValidos) {
            if(tipoDeEmail[0].equals(emailValido)) {
                provedorValido = true;
            }
        }

        if(!provedorValido) {
            throw new InvalidEmailException(email);
        }
    }
}
