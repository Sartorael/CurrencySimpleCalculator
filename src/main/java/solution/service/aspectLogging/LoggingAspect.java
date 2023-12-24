package solution.service.aspectLogging;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspect {

    @Before("execution(* solution.service.ConverterService.currencyApiCom(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logging before executing method: " + methodName);
    }
}
