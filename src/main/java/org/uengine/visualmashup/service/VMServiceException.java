package org.uengine.visualmashup.service;

/**
 * VMService 예외 클래스
 *
 * @author 이승백
 */
public class VMServiceException extends RuntimeException {

    private String code;
    private String locator;
    private String message;

    public VMServiceException(String code, String locator, String message) {
        super(message);
        this.code = code;
        this.locator = locator;
        this.message = message;
    }

    public VMServiceException(String code, String locator, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.locator = locator;
        this.message = message;
    }

    public VMServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public VMServiceException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public VMServiceException(Throwable cause) {
        super(cause);
    }

    public VMServiceException(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getLocator() {
        return locator;
    }

    public String getMessage() {
        return message;
    }
}