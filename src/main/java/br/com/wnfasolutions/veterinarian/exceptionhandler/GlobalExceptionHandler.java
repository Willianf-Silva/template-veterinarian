package br.com.wnfasolutions.veterinarian.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.wnfasolutions.veterinarian.exception.AppointmentServiceEmptyException;
import br.com.wnfasolutions.veterinarian.exception.RecursoNaoEstaAtivoException;
import br.com.wnfasolutions.veterinarian.exception.ResourceIndisponivelParaAlteracaoException;
import br.com.wnfasolutions.veterinarian.exception.ResourceNotFoundException;
import br.com.wnfasolutions.veterinarian.exception.RolesNotFoundException;
import lombok.Getter;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
    private MessageSource messageSource;
	
	@ExceptionHandler({RolesNotFoundException.class})
    public ResponseEntity<Object> handleRolesNotFoundException(RolesNotFoundException ex, WebRequest request){
        String mensagemUsuario = messageSource.getMessage("role-inexistente", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
	
	@ExceptionHandler({AppointmentServiceEmptyException.class})
    public ResponseEntity<Object> handleUsuarioNotFoundException(AppointmentServiceEmptyException ex, WebRequest request){
        String mensagemUsuario = messageSource.getMessage("appointment.sem-servico", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
	
	@ExceptionHandler({ResourceIndisponivelParaAlteracaoException.class})
    public ResponseEntity<Object> handleUsuarioNotFoundException(ResourceIndisponivelParaAlteracaoException ex, WebRequest request){
        String mensagemUsuario = messageSource.getMessage("recurso.indisponivel-alteracao", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
	
	@ExceptionHandler({RecursoNaoEstaAtivoException.class})
    public ResponseEntity<Object> handleUsuarioNotFoundException(RecursoNaoEstaAtivoException ex, WebRequest request){
        String mensagemUsuario = messageSource.getMessage("recurso.nao-esta-ativo", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
	
	@ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleUsuarioNotFoundException(ResourceNotFoundException ex, WebRequest request){
        String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
	
	@ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request){
        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
	
	@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));

        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> criarListaDeErros(BindingResult bindingResult){
        List<Erro> erros = new ArrayList<>();

        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario=messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor=fieldError.toString();
            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        }

        return erros;
    }


    @Getter
    public static class Erro{
        private String mensagemUsuario;
        private String mensagemDesenvolvedor;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }
    }
}
