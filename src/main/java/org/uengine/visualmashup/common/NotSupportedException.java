/*
 * @(#)NotSupportedException.java   1.0 2002/10/11
 */
package org.uengine.visualmashup.common;

/**
 * 지원하지않는 기능 호출시 발생하는 예외
 *
 * @author 이승백
 */
public class NotSupportedException extends RuntimeException {

    static final long serialVersionUID = -1606492669028787124L;

    public NotSupportedException() {
    }

    public NotSupportedException(String s) {
        super(s);
    }

    public NotSupportedException(Exception e) {
        super(e);
    }

    public NotSupportedException(String s, Exception e) {
        super(s, e);
    }
}