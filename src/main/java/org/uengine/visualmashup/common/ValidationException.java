/*
 * @(#)ValidationException.java   1.0 2002/10/11
 */
package org.uengine.visualmashup.common;

/**
 * 유효성 체크 오류시 발생하는 예외
 *
 * @author 이승백
 */
public class ValidationException extends RuntimeException {

    static final long serialVersionUID = -1606492669028787124L;

    public ValidationException() {
    }

    public ValidationException(String s) {
        super(s);
    }

    public ValidationException(Exception e) {
        super(e);
    }

    public ValidationException(String s, Exception e) {
        super(s, e);
    }
}