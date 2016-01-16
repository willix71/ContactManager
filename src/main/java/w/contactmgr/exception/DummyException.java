package w.contactmgr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.I_AM_A_TEAPOT )
public class DummyException extends RuntimeException {

	private static final long serialVersionUID = 3691642349894840127L;
	
	public DummyException(String mesg) {
        super(mesg);
	}
}
