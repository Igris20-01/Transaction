package uz.booker.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CallTracker {

    @Pointcut("within(uz..service.*) || within(uz..repository.*)")
    public void logMethodPointcut(){

    }


    @Around("logMethodPointcut()")
    public Object logBeforeMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {

        String name = joinPoint.getSignature().getName();

        //before
        System.out.println("Method start : " + name);

        //call to method
        Object returnValue = joinPoint.proceed();//saveProductInfo

        //after the method executed
        System.out.println("Method completed : " + name);

        return returnValue;
    }








//    @After("logMethodPointcut()")
//    public void logAfterMethodCall(){
//        System.out.println("Method execution completed...");
//    }




}
