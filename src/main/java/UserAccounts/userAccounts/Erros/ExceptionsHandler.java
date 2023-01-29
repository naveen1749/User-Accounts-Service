package UserAccounts.userAccounts.Erros;


import UserAccounts.userAccounts.Exceptions.UserException;
import UserAccounts.userAccounts.Model.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionsHandler {


    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public Map<String, String> notValild(MethodArgumentNotValidException e) {
        Map<String, String> errMap = new HashMap<>();
        e.getFieldErrors().forEach(
                (err) -> {
                    errMap.put(err.getField(), err.getDefaultMessage());
                }
        );
        return errMap;
    }

    @ExceptionHandler(UserException.class)
    public Map<String, String> userException(UserException e) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("status", e.getStatus());
        errMap.put("message", e.getMsg());
        return errMap;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String invalidRequest(HttpRequestMethodNotSupportedException e) {
        return "<h2> Request Type Invalid\n try to give name of the user at url end </h2>" +
                "<h3>you can also use bellow method </h3>"+
                "<ul>" +
                "<li><a href=\"/temp\">GET</a></li>" +
                "<li><a href=\"/\">POST</a> (use POSTMAN)</li>" +
                "<li><a href=\"/temp\">PUT</a> (use POSTMAN)</li>" +
                "<li><a href=\"/temp\">DELETE (use POSTMAN)</a></li>" +
                "</ul>" +
                "<h4>Error : " +
                e.getMessage() + "</h4>";
    }
    @ExceptionHandler({ConnectException.class,SocketException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> dbCon(SocketException e){
        Map<String, String> errMap = new HashMap<>();
        if(e instanceof ConnectException){
            errMap.put("status", Status.DB_CONNECTION_CLOSED.getMesssage());
        }else{
            errMap.put("status", Status.DB_CONNECTiON_FAILURE.getMesssage());

        }
        errMap.put("message", e.getMessage());
        return errMap;
    }
}
