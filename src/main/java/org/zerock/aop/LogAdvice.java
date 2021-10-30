package org.zerock.aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect //해당 클래스의 객체가 Aspect를 구현 한 것임을 나타내기위해 사용한다.
@Log4j
@Component  // aop와 관련이 없지만 스프링에서 빈으로 인식 하기위해 사용한다.
public class LogAdvice {
    //@Before("execution(* org.zerock.service.SampleService*.*(..))")
//    public void LogBefor(){
//        log.info("===============");
//    }
    // beforeAdvice : terget의 joinpoint를 호출하기 전에 실행되는 코드입니다.
    // AsprctJ의 표현식 : execution은 접근제한자와 특정 클래스의 메서드를 지정 할수 있다.
    // 맨앞의 * 는 접근 제한자를 의미하고 맨뒤 * 는 클래스의 메서도를 지정할수 있다.

    // Logadvice가 SampleService의 doadd()를 실행하기 직전에 간단한 로그를 기록하지만 어떤 파라미터 정보를 알고싶을떄 적용하는 코드이다.
    @Before("execution(* org.zerock.service.SampleService*.*(..)) && args(str1,str2)")
   public void logBeforeWithParam(String str1, String str2){
        log.info("str1:"+str1);
        log.info("str2:"+str2);
    }

    @AfterThrowing(pointcut = "execution(* org.zerock.service.SampleService*.*(..))", throwing="exception")
    public void logException (Exception exception){
    log.info("Exception....!!!");
    log.info("exception:"+exception);
    }
    //AfterThrowing 는 pointcut 과 throwing 속성을 지정 해야 한다.

    //Aop를 이용해서 좀 더 구체적인 처리를 하고 싶다면 @Around 와 ProceedingJoinPoint를 이용해야 한다.
    //@Around는 직접 대상 메서드를 실핼 할수 있는 권한을 가지고있고, 메서드 의 실행  전과후에 처리가 가능하다.

    @Around("execution(* org.zerock.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint proceedingJoinPoint){
        long start = System.currentTimeMillis();

        log.info("Target:" + proceedingJoinPoint.getTarget());
        log.info("Param: " + Arrays.toString(proceedingJoinPoint.getArgs()));

        Object result = null;

        try{
            result = proceedingJoinPoint.proceed();

        }catch (Exception e){
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("Time:" + (end -start));

        return result;
    }

    // logTime()은 ProceedingJoinPoint라는 파리미터를 지정하는데 AOP의 대상이 되는 Target이나 파라미터 들을 파락 할 뿐만 아니라
    // 직접 실행을 결정 할수 있다.
    // @before 과 달리 리턴타입이 object로 있으며 , 실행 결과 역시 직접 반환하는 형태로 작성 한다.
}
