package com.suddiyo.springboot.dto;

import com.suddiyo.springboot.web.dto.HelloResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);  //생성자 check

        //then
        assertThat(dto.getName()).isEqualTo(name);  //assertj라는 테스트 검증 라이브러리의 검증 메소드. getter check
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}
