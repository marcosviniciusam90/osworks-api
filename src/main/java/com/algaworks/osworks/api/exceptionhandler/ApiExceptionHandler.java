package com.algaworks.osworks.api.exceptionhandler;

import com.algaworks.osworks.domain.exception.EntidadeNaoEncontraException;
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

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

//diz que é componente do Spring e vai fazer tratamento para exceptions de todas as classes Controller
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    //Faz a ligação com o messages.properties (onde especifica as mensagens dos erros)
    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Problem.Campo> campos = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            //No getLocale() poderia passar outras linguagens caso o sistema dê suporte e especicar no messages.properties
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            campos.add(new Problem.Campo(nome, mensagem));
        }

        String titulo = "Um ou mais campos inválidos. Preencha corretamente e tente novamente";
        Problem problem = newProblem(status, titulo, campos);
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request) {
        return handle(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EntidadeNaoEncontraException.class)
    public ResponseEntity<Object> handleNegocioException(EntidadeNaoEncontraException ex, WebRequest request) {
        return handle(ex, HttpStatus.NOT_FOUND, request);
    }

    private ResponseEntity<Object> handle(NegocioException ex, HttpStatus status, WebRequest request) {
        Problem problem = newProblem(status, ex.getMessage(), null);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private Problem newProblem(HttpStatus status, String titulo, List<Problem.Campo> campos) {
        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setTitulo(titulo);
        problem.setDataHora(OffsetDateTime.now());
        problem.setCampos(campos);
        return problem;
    }
}
