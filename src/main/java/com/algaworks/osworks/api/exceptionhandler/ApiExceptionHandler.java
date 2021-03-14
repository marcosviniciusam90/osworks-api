package com.algaworks.osworks.api.exceptionhandler;

import com.algaworks.osworks.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//diz que é componente do Spring e vai fazer tratamento para exceptions de todas as classes Controller
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    //Faz a ligação com o messages.properties (onde especifica as mensagens dos erros)
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionError exceptionError = newExceptionError(status, ex.getMessage(), null);
        return handleExceptionInternal(ex, exceptionError, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ExceptionError.Campo> campos = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            //No getLocale() poderia passar outras linguagens caso o sistema dê suporte e especicar no messages.properties
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            campos.add(new ExceptionError.Campo(nome, mensagem));
        }

        String titulo = "Um ou mais campos inválidos. Preencha corretamente e tente novamente";
        ExceptionError exceptionError = newExceptionError(status, titulo, campos);
        return handleExceptionInternal(ex, exceptionError, headers, status, request);
    }

    private ExceptionError newExceptionError(HttpStatus status, String titulo, List<ExceptionError.Campo> campos) {
        ExceptionError exceptionError = new ExceptionError();
        exceptionError.setStatus(status.value());
        exceptionError.setTitulo(titulo);
        exceptionError.setDataHora(LocalDateTime.now());
        exceptionError.setCampos(campos);
        return exceptionError;
    }
}
