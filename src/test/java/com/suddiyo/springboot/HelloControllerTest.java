package com.suddiyo.springboot;

import com.suddiyo.springboot.web.HelloController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//스프링부트테스트와 JUnit 사이의 연결자 역할. JUnit에 내장된 실행자 외에 SpringExtension이라는 스프링 실행자를 사용.
@ExtendWith(SpringExtension.class)
/*
    여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션.
    -> 선언하면 @Controller, @ControllerAdvice 사용 O
    -> @Service, @Component, @Repository 사용 X
*/
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    //스프링이 관리하는 Bean을 주입
    @Autowired
    private MockMvc mvc;    //웹 API를 테스트할 때 사용. 스프링 MVC 테스트의 시작점. HTTP GET, POST 등에 대한 API 테스트를 할 수 있음

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))  //MockMvc를 통해 /hello로 HTTP GET 요청
                .andExpect(status().isOk()) //mvc.perform의 결과를 검증. HTTP Header의 Status를 검증. 200(OK), 404, 500 등의 상태를 검증.
                .andExpect(content().string(hello));    //mvc.perform의 결과를 검증. 응답 본문의 내용을 검증. Controller에서 "hello" 리턴하는 값이 맞는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))  //API 테스트할 때 사용될 요청 파라미터 설정. 값은 String만 허용. 숫자/날짜도 문자열로 변경해야함
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));   //jsonPath -> JSON 응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시. 여기에선 name과 amount 검증
    }
}
