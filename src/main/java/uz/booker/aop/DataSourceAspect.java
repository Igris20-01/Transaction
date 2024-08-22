package uz.booker.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.jdbc.datasource.ConnectionProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.Connection;

@Aspect
@Component
public class DataSourceAspect {

    @Around("target(javax.sql.DataSource)")
    public Object logDataSourceConnectionInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("DataSource tracker : " + proceedingJoinPoint.getSignature());
        Object returnValue = proceedingJoinPoint.proceed(); //getConnection //connection
        if (returnValue instanceof Connection) {
            Connection con = (Connection) Proxy.newProxyInstance(
                    ConnectionProxy.class.getClassLoader(),
                    new Class[]{Connection.class},
                    new ConnectionInvocationHandler((Connection) returnValue)
            );
            return con;
        }
//        System.out.println(returnValue);
        return returnValue;
    }


}
