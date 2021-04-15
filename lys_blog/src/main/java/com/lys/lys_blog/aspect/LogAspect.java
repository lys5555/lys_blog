package com.lys.lys_blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class LogAspect {
    //获取日志类对象
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //定义切面方法
    @Pointcut("execution(* com.lys.lys_blog.controller.*.*(..))")
    public void log(){
    }
    //定义前置通知
    @Before("log()")
    public void doBefore(JoinPoint jp) {
    //通过获取request上下文对象来获取request对象
        ServletRequestAttributes  attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
       //获取id地址
        String id = request.getRemoteAddr();
        //获取url路径
        String url = request.getRequestURI().toString();
        //通过切面对象获取切面类名和方法名字
       String classMethod =  jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName();
        //获取参数
        Object[] args = jp.getArgs();
        //封装到实体类对面，打印成日志
        RequestLog requestLog = new RequestLog(url,id,classMethod,args);
        logger.info("Request : {}",requestLog);
    }

    //定义后置通知
    @After("log()")
    public void doAfter(){

    }
    //定义后置增强方法，相当于AfterReturningAdvice，方法正常退出时执行
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("Request : {}",result);
    }


//    定义日志封装实体类
    private class  RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;


    public RequestLog(String url, String ip, String classMethod, Object[] args) {
        this.url = url;
        this.ip = ip;
        this.classMethod = classMethod;
        this.args = args;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "RequestLog{" +
                "url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
        }
    }
}

