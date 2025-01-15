package foro.hub.api.infra.errores;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class TratadorDeErrores {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException e) {
        var mensajes400 = e.getFieldErrors().stream()
                .map(DatosErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(mensajes400);
    }
    // Manejador específico para SQLIntegrityConstraintViolationException
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity tratarError500(SQLIntegrityConstraintViolationException e) {
        // Obtener el mensaje de la excepción
        String error = e.getMessage().toLowerCase();
        // Log para depuración
//        System.err.println("Error en la base de datos: " + error);
        // Analizar el tipo de violación de integridad
        if (error.contains("duplicate entry")) {
            // Usamos una expresión regular para extraer el valor duplicado
            String campoDuplicado = extractDuplicatedValue(error);
            return ResponseEntity.badRequest().body(new DatosErrorCampoUnicoDuplicado("El registro con el valor '" + campoDuplicado + "' ya existe."));
        }
        // Si el error no es uno de los anteriores, devolver error 500
        return ResponseEntity.internalServerError().body("Error interno del servidor.");
    }
    // Método para extraer el valor duplicado del mensaje de error
    private String extractDuplicatedValue(String errorMessage) {
        // Definir una expresión regular para extraer el valor duplicado
        Pattern pattern = Pattern.compile("duplicate entry '([^']+)'");
        Matcher matcher = pattern.matcher(errorMessage);
        if (matcher.find()) {
            return matcher.group(1); // El valor duplicado es el primer grupo en la expresión regular
        }
        return "valor desconocido"; // En caso de que no se pueda extraer el valor
    }

    private record DatosErrorValidation(String campo, String mensaje) {
        public DatosErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    private record DatosErrorCampoUnicoDuplicado(String mensaje) {

    }





}
//MethodArgumentNotValidException