package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article; //PK와 매칭되므로 article의 id와 매칭

    @Column
    private String nickname;

    @Column
    private String body;

    //2. 댓글 생성(Create)
    public static Comment createComment(CommentDto dto, Article article) {
        //예외 발생
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못 됐습니다.");
        //엔티티 생성 및 반환
        return new Comment(
                dto.getId(),        // 댓글 아이디
                article,            // 댓글 게시글
                dto.getNickname(),  // 댓글 닉네임
                dto.getBody()       // 댓글 본문
        );
    }

    //3. 댓글 수정(Update)
    public void patch(CommentDto dto) {
        // 예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 잘못된 id가 입력됐습니다.");
        //객체 갱신
        if (dto.getNickname() != null) this.nickname = dto.getNickname();
        if (dto.getBody() != null) this.body = dto.getBody();
    }
}
