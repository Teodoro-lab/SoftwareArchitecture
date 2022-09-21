package org.PipesAndFilters.exceptions;

import org.json.simple.parser.ParseException;

public class JSONParseException extends ParseException {
    private String parseExceptionMsg;

    public JSONParseException(int position, int errorType, Object unexpectedObject, String msg) {
        super(position, errorType, unexpectedObject);
        parseExceptionMsg = msg;
    }

    public String getMessage() {
        return parseExceptionMsg;
    }

}
