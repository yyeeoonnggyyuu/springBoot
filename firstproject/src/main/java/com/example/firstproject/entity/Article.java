package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동생성
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    // api의 ArticleApiController 에서 PatchMapping의 target.Patch(article)을 실행하기 위해서 만든것
    // 중용한거 이걸 사용함으로서 title이든 content든 하나만 바꿔도 바꿀수 있음 단일로 수정 가능
    public void patch(Article article){
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}
