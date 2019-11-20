package com.aopdemo.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
@Lazy(false)//容器初始化时创建本对象
public class MyAop {

    /**
     * 定义切入点：对要拦截的方法进行定义与限制，如包、类
     * <p>
     * 1、execution(public * *(..)) 任意的公共方法
     * 2、execution（* set*（..）） 以set开头的所有的方法
     * 3、execution（* com.lingyejun.annotation.LoggerApply.*（..））com.lingyejun.annotation.LoggerApply这个类里的所有的方法
     * 4、execution（* com.lingyejun.annotation.*.*（..））com.lingyejun.annotation包下的所有的类的所有的方法
     * 5、execution（* com.lingyejun.annotation..*.*（..））com.lingyejun.annotation包及子包下所有的类的所有的方法
     * 6、execution(* com.lingyejun.annotation..*.*(String,?,Long)) com.lingyejun.annotation包及子包下所有的类的有三个参数，第一个参数为String类型，第二个参数为任意类型，第三个参数为Long类型的方法
     * 7、execution(@annotation(com.lingyejun.annotation.Lingyejun))
     */
    @Pointcut("execution(* com.aop.controller.*.*(..))")
    public void test0() {
    }


    /**
     * 前置通知(方法执行前)
     */
    @Before("test0()")
    public void test1() {
//        System.out.println("前置通知");
    }

    /**
     * 后置通知（方法执行后，中间有异常不通知）
     */
    @AfterReturning("test0()")
    public void test2() {
//        System.out.println("后置通知");
    }

    /**
     * 最终通知（方法执行后，及时有异常）
     */
    @After("test0()")
    public void test3() {
//        System.out.println("最终通知");
    }

    /**
     * 异常通知（发生异常时）
     */
    @AfterThrowing("test0()")
    public void test4() {
//        System.out.println("异常通知");
    }

    @Autowired
    private HttpServletRequest request;


    /**
     * 环绕通知
     */
    @Around("test0()")
    public Object test5(ProceedingJoinPoint joinPoint) throws NoSuchMethodException, InterruptedException {
        //获取方法名  //test2
        String methodName = joinPoint.getSignature().getName();

        //获取对象   //class com.aop.controller.MyController
        Class<?> aClass = joinPoint.getTarget().getClass();

        //获取方法      //public void com.aop.controller.MyController.test2(int,java.lang.String)
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        //获取方法上的注解   interface org.springframework.web.bind.annotation.GetMapping
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        GetMapping annotation = method.getAnnotation(GetMapping.class);//获取指定注解

        //获取参数上的注解(一个参数可以有多个注解)   interface org.springframework.web.bind.annotation.RequestParam
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (Annotation[] parameterAnnotation : parameterAnnotations) {//一个参数为一个注解数组
            for (Annotation annotation1 : parameterAnnotation) {//遍历参数上的所有注解
                RequestParam requestParam1 = annotation1 instanceof RequestParam ? ((RequestParam) annotation1) : null;//判断是否是指定注解
                if (requestParam1 != null) {
                    RequestParam requestParam = (RequestParam) annotation1;
                    //System.out.println(requestParam.value());//获取注解的值
                }
            }
        }

        //获取方法参数个数  //2
        int parameterCount = method.getParameterCount();
        //获取方法参数类型    //int     class java.lang.String
        Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        //获取请求入参(只会获取方法形参对应的参数)
        Object[] args = joinPoint.getArgs();
        //获取方法参数名
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        String params = "";
        for (int i = 0; i < parameterNames.length; i++) {
            params += parameterNames[i] + " = " + args[i] + "(" + parameterTypes[i].getTypeName() + "), ";
        }
        System.out.println("请求IP= "+getIpAddress(request)+", 请求路径= " + request.getServletPath() + ", 请求参数{ " + params + "}");




        //执行方法
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();

        } catch (Throwable throwable) {
            //throwable.printStackTrace();
        }


        System.out.println(request.hashCode());

        return proceed;//返回数据到前端
    }

    /**
     * 获取真实ip地址
     * @param request
     * @return
     */
    public String getIpAddress(HttpServletRequest request) {
        String[] strings = {"X-forwarded-for","Proxy-Client-IP","WL-Proxy-Client-IP","HTTP_CLIENT_IP","HTTP_X_FORWARDED_FOR"};
        String ip;
        for (String header : strings) {
            ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        ip = request.getRemoteAddr();
        return ip;
    }

}
