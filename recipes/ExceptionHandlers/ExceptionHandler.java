package recipes.ExceptionHandlers;
// of no use currently
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.ConstraintViolationException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice//ye lagaya hai to response 400 gaya h instead of 500
public class ExceptionHandler {
    //for 500 server error to bad request conversion

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler({ConstraintViolationException.class, JdbcSQLIntegrityConstraintViolationException.class })
//    @org.springframework.web.bind.annotation.ExceptionHandler
// @ExceptionHandler({<exeptions>)
    //jab DB me empty list ya null list jati h to constraint wali exception generate hoti hai (q k hamen @not blank use kia hua) so with code 500 , this will change it to 400
    //jab json me ghlat data ata hai aur validate(for req parm and pathvar or for validating obj within obj) krne pe gahlti nklti hai to Contraint wali exception generate hoti hia with code 500 , this will change it to 400
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),"Either Viladitor Exception or Unique Emp-Period Exception(May already exist)");
    }
    //===================
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
}
/*
//@ validated , as it returns 500 error code and we want 400
*  @ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    ErrorMessage exceptionHandler(ValidationException e) {
        return new ErrorMessage("400", e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ErrorMessage exceptionHandler(ConstraintViolationException e) {
        return new ErrorMessage("400", e.getMessage());
    }

}
*
*
* */