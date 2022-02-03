package com.suddiyo.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/* Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다!!! (단, 해당 필드의 값 변경이 필요하면 목적, 의도를 나타내는 메소드를 추가)*/

//롬복의 어노테이션(롬복 - 단순화 but 필수는 x)
@Getter //클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor  //기본 생성자 자동 추가 -> public Posts(){}
@Entity //JPA의 어노테이션. 더 중요하니까 가까이 둔 것.
//Posts 클래스 - 실제 DB의 테이블과 매칭될 클래스. = Entity클래스
public class Posts extends BaseTimeEntity{
    @Id //해당 테이블의 PK필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙을 나타냄. GenerationType.IDENTITY 옵션을 추가해야 auto_increment가 됨.
    private Long id;    //Long 추천!

    //테이블의 칼럼을 나타냄. 굳이 선언 안 해도 해당 클래스의 필드는 모두 칼럼. 변경이 필요한 옵션이 있을 경우 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //해당 클래스의 빌더 패턴 클래스를 생성. 생성자에 포함된 필드만 빌더에 포함.
    @Builder    // -> 어느 필드에 어떤 값을 채워야 할지 명확하게 인지할 수 있음!
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
