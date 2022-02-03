package com.suddiyo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  //JPA Auditing 활성화
//main class
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        //내장 웹 애플리케이션 셔버(WAS) 실행 -> '언제 어디서나 같은 환경에서 스프링 부트를 배포' 할 수 있음
        SpringApplication.run(Application.class, args);
    }
}
