package org.example.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component  //  в spring аспекты должны быть бинами
@Log4j2
public class FirstAspect {

    /** @within - аннотации над классами */
    @Pointcut("@within(org.springframework.stereotype.Controller)")  // проверяет аннотацию над классом
    public void isControllerLayer() {}

    /** within */
    @Pointcut("within(org.example.service.*)")  // этот срез сработает для всех классов в указанном пакете
//    @Pointcut("within(org.example.service.*Service)")  // этот срез сработает для всех классов в указанном пакете, к-е заканчиваются на 'Service'
//    @Pointcut("within(org.example.service..*)")  // этот срез сработает для всех классов в указанном пакете и его подпакетах (две точки)
    public void isServiceLayer() {}


    /** this, target */
    @Pointcut("this(org.springframework.data.repository.Repository)") // this обращается к AOP-proxy (срез применится ко всем реализациям интерфейса Repository)
//    @Pointcut("target(org.springframework.data.repository.Repository)") // target обращается к объекту, вокруг к-го создан AOP-proxy (срез применится ко всем реализациям интерфейса Repository)
    public void idRepositoryLayer() {}

    /** @annotation - аннотации над методами */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")  // проверяет аннотацию над методом
//    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")  // 2 условия через '&&'
    public void hasGetMapping() {}

    /** args */
    @Pointcut("args(org.springframework.ui.Model,..)")  // первый параметр метода должен быть типа Model, дальше может быть 0 или больше параметров
//    @Pointcut("args(org.springframework.ui.Model,*)")  // первый параметр метода должен быть типа Model, дальше должен быть еще одни параметр
//    @Pointcut("args(org.springframework.ui.Model,*,*)")  // первый параметр метода должен быть типа Model, дальше должен быть еще 2 параметра
    public void hasModelParam() {}

    /** @args */
    @Pointcut("@args(javax.persistence.Entity,..)") // первый параметр метода содержит над своим классом аннотацию @Entity (см.класс Chat)
    public void hasEntityAnnotation() {}

    /** bean */
    @Pointcut("bean(*Service)")
    public void isServiceBean() {}  // проверяет имя бина

    /** execution (modifier? return-type, класс,в к-м находится метод?имя метода(типы параметров) выбрасываемое_исключение? */
    /** для private методов не сработает срез */
//    @Pointcut("execution(public Long)")
//    @Pointcut("execution(public UserReadDto create(Integer)") // public метод, с названием 'create', возвращаемый тип - UserReadDto, один параметр метода типа Integer
    @Pointcut("execution(public * org.example.service.*Service.findById(*))")    // public метод, любой возвращаемый тип, класс из пакета 'org.example.service' с окончанием 'Service', метод (findById), кол-во аргументов метода должно быть 1 (любого типа)
//    @Pointcut("execution(public * org.example.service.CompanyService.findById(..)") // public метод, любой возвращаемый тип, любое кол-во и тип аргументов метода
//    @Pointcut("execution(public * org.example.service.CompanyService.findById(..) throws IndexOutOfBoundsException") // public метод, любой возвращаемый тип, любое кол-во и тип аргументов метода и выбрасывает исключение IndexOutOfBoundsException
    public void anyFindByIsServiceMethod(){}


    /** Advices */
    @Before("anyFindByIsServiceMethod() && args(id) && target(service)")   // добавится логирование перед вызовом метода findById в классах сервиса (см.Pointcut anyFindByIsServiceMethod)
    public void addLogging(JoinPoint joinPoint, Object id, Object service) {    // есть доступ к параметру метода (id), объекту сервиса(service) - названия должны быть такими же, как в аннотации @Before -> args(id), target(service)
        log.info("Before log from aop-advice addLogging in class {} with id {}", service, id);
    }

    @AfterReturning(value ="anyFindByIsServiceMethod() && target(service)", returning = "result") // сработает при успешном завершении метода
    public void addLoggingAfterReturning(Object service, Object result) {
        log.info("AfterReturning log from aop-advice in class {} with result {}", service, result);
    }

    @AfterThrowing(value ="anyFindByIsServiceMethod() && target(service)", throwing = "ex")  // сработает при выбрасывании исключения
    public void addLoggingAfterThrowing(Throwable ex, Object service) {
        log.info("AfterThrowing log from aop-advice in class {} with throwing {}", service, ex.getMessage());
    }

    @After(value ="anyFindByIsServiceMethod() && target(service)")  // сработает всегда (независимо было или нет исключение)
    public void addLoggingAfter(Object service) {
        log.info("After log from aop-advice in class {}", service);
    }

    @Around("anyFindByIsServiceMethod() && args(id) && target(service)")    // может заменить все вышеуказанные (@Before, @AfterReturning, @AfterThrowing, @After)
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object id, Object service) throws Throwable {
        log.info("AROUND Before log from aop-advice addLogging in class {} with id {}", service, id);
        try {
            Object result = joinPoint.proceed();
            log.info("AROUND AfterReturning log from aop-advice in class {} with result {}", service, result);
            return result;
        } catch (Throwable ex) {
            log.info("AROUND AfterThrowing log from aop-advice in class {} with throwing {}", service, ex.getMessage());
            throw ex;   // из Around advice всегда нужно пробрасывать exception выше
        } finally {
            log.info("AROUND After log from aop-advice in class {}", service);
        }
    }
}
