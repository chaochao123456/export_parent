package cn.itcast.support;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 检测controller方法耗时的切面
 * @author ex_zhangxiaoliang
 *
 */
@Aspect
@Component
public class ChecKTimeInterceptor {

    private static Logger logger = LoggerFactory.getLogger(ChecKTimeInterceptor.class);
    //com.axatp.enonmotor.controller.CargoInsuranceManageController
    public static final String POINT = "execution (* cn.itcast.web.controller.*.*(..))";

    /**
     * 统计方法执行耗时Around环绕通知
     * @param joinPoint
     * @return
     */
    @Around(POINT)
    public Object timeAround(ProceedingJoinPoint joinPoint) {
        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();

        try {
            obj = joinPoint.proceed(args);
        } catch (Throwable e) {
            logger.error("统计某方法执行耗时环绕通知出错", e);
        }
        // 获取执行的方法名
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String simpleName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        // 打印耗时的信息
        this.printExecTime(methodName, startTime, endTime,simpleName);
        return obj;
    }

    /**
     * 打印方法执行耗时的信息
     * @param methodName
     * @param startTime
     * @param endTime
     */
    private void printExecTime(String methodName, long startTime, long endTime,String simpleName) {
        long diffTime = endTime - startTime;
            logger.info("-----" + methodName + " 方法执行耗时：" + diffTime + " ms");
            logger.info("-----" + simpleName + " 方法执行耗时：" + diffTime + " ms");
    }
}