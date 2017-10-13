package zcu.pia.bohmannd.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class KivbookExceptionHandler {

	@ExceptionHandler(KivbookException.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		    // If the exception is annotated with @ResponseStatus rethrow it and let
		    // the framework handle it - like the OrderNotFoundException example
		    // at the start of this post.
		    // AnnotationUtils is a Spring Framework utility class.
		    if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
		    	throw e;
		    }
		    
		    return "error";
   }
}
