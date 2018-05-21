package com.session;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class CustomErrorHandler implements org.xml.sax.ErrorHandler {

    private String handleMessage(String level, SAXParseException exception) throws SAXException {
        int lineNumber = exception.getLineNumber();
        int columnNumber = exception.getColumnNumber();
        String message = exception.getMessage();
        throw new SAXException("[" + level + "] line number: " + lineNumber + " column number: " + columnNumber + " message: " + message);
    }

    @Override
    public void warning(org.xml.sax.SAXParseException exception) throws SAXException {
        handleMessage("Warning", exception);
    }

    @Override
    public void error(org.xml.sax.SAXParseException exception) throws SAXException {
        handleMessage("Error", exception);
    }

    @Override
    public void fatalError(org.xml.sax.SAXParseException exception) throws SAXException {
        handleMessage("Fatal Error", exception);
    }
}