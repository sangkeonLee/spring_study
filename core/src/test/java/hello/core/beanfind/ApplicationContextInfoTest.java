package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanList = ac.getBeanDefinitionNames();
        for (String beanName : beanList) {
            Object bean = ac.getBean(beanName);
            System.out.println("name = " + beanName + " object = " +bean);
        }

    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanList = ac.getBeanDefinitionNames();
        for (String beanDefName : beanList) {
            BeanDefinition beanDef = ac.getBeanDefinition(beanDefName);

            // ROLE_APPLICATION 외부 라이브러리, 선언한 Bean만 가져오는 코드
            // ROLE_INFRASTRUCTURE 내부 라이브러리 bean만 가져오는 코드
            if (beanDef.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                Object bean = ac.getBean(beanDefName);
                System.out.println("name = " + beanDefName + " object = " +bean);
            }


        }

    }
}
