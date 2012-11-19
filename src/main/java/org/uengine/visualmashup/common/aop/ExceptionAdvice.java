/*
 * @(#)ExceptionAdvice.java   1.0 2008/03/11
 */
package org.uengine.visualmashup.common.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

/**
 * 스프링 프레임웍 AOP : 예외처리 Advisor 클래스
 *
 * @author 이승백
 */
public class ExceptionAdvice implements ThrowsAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * <code>Exception</code> 예외 발생시 실행되는 메소드
     *
     * @param ex
     * @throws Throwable
     */
    public void afterThrowing(Exception ex) throws Throwable {
        logger.error(ex.getMessage(), ex);
    }
}