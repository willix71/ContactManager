package w.contactmgr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND )
public class RessourceNotFoundException  extends RuntimeException {

	private static final long serialVersionUID = 3691642349894840127L;

	public RessourceNotFoundException() {
        super();
    }

}
