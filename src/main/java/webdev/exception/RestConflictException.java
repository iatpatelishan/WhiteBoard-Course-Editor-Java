// Credits : http://www.springboottutorial.com/spring-boot-exception-handling-for-rest-services
package webdev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RestConflictException extends RuntimeException {

    public RestConflictException(String exception) {
        super(exception);
    }

}
