/*
 * @(#)LoggingAdvice.java   1.0 2008/03/11
 */
package org.uengine.visualmashup.common.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uengine.visualmashup.model.*;

import javax.xml.bind.JAXB;
import java.io.StringWriter;

/**
 * 스프링 프레임웍 AOP : 로깅처리 Advisor 클래스
 *
 * @author 이승백
 */
public class LoggingAdvice implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Object invoke(MethodInvocation invocation) throws Throwable {

        String className = invocation.getThis().getClass().getName();

        // before
        StringBuffer params = new StringBuffer();
        Object[] args = invocation.getArguments();
        if ((args != null) && (args.length > 0)) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null && (args[i] instanceof CreateRequest ||
                        args[i] instanceof UpdateRequest ||
                        args[i] instanceof SearchRequest ||
                        args[i] instanceof ViewRequest ||
                        args[i] instanceof DeleteRequest)) {
                    StringWriter xmlWriter = new StringWriter();
                    JAXB.marshal(args[i], xmlWriter);
                    params.append("Argument[" + i + "] : " + xmlWriter.toString());
                } else {
                    params.append("Argument[" + i + "] : " + args[i]);
                }
                if (i < args.length - 1)
                    params.append("\n");
            }
        }

        logger.info(className + "." + invocation.getMethod().getName()
                + "() start!\n" + params);

        // execute
        Object retVal = invocation.proceed();

        // after
        if (retVal != null && (retVal instanceof CreateResponse ||
                retVal instanceof UpdateResponse ||
                retVal instanceof SearchResponse ||
                retVal instanceof ViewResponse ||
                retVal instanceof DeleteResponse)) {
            StringWriter xmlWriter = new StringWriter();
            JAXB.marshal(retVal, xmlWriter);
            logger.info(className + "." + invocation.getMethod().getName()
                    + "() finish!\n" + "Return : " + xmlWriter.toString());
        } else {
            logger.info(className + "." + invocation.getMethod().getName()
                    + "() finish!\n" + "Return : " + retVal);
        }

        return retVal;
    }
}
