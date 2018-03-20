package guru.springframework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class LifeCycleDemoBean implements InitializingBean, DisposableBean, BeanNameAware
                            , BeanFactoryAware, ApplicationContextAware{

    public LifeCycleDemoBean() {
        System.out.println("## I'm in the LifeCycleBean Constructor");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("## Bean Factory Has Been Set");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("## Bean name is " + name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("## Lifecycle of the bean is terminated");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("## LifecycleBean's has its properties set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("## Application Context has been set");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("## The post Construct annotated method has been called");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("## The PreDestroy annotated method has been called");
    }

    public void beforeInit(){
        System.out.println("## - Before Init - Called by Bean Post processor");
    }

    public void afterInit(){
        System.out.println("## - After Init called by the bean Post processor");
    }
}
















