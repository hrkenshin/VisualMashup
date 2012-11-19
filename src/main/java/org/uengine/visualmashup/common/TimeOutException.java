/*
 * @(#)TimeOutException.java   1.0 2002/10/11
 */
package org.uengine.visualmashup.common;

/**
 * 실행시간 초과시 발생하는 예외
 *
 * @author 이승백
 * @version 1.0, 2002/10/09
 */
public class TimeOutException extends RuntimeException {
    static final long serialVersionUID = 5896067475964398127L;

    public TimeOutException() {
    }

    public TimeOutException(String s) {
        super(s);
    }

    public TimeOutException(Exception e) {
        super(e);
    }

    public TimeOutException(String s, Exception e) {
        super(s, e);
    }
}
