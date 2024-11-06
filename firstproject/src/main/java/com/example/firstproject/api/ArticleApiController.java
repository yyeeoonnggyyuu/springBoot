package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {



    //책 페이지 313
    //빈 등록
    @Autowired
    private ArticleRepository articleRepository;

//  GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
//  POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);

    }

//  PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        //1. DTO -> 엔티티 변환
       Article article= dto.toEntity();
       // article 은 내가 수정하고 싶은 녀석의 값

       log.info("DTO-> entity 변환 = id: {}, article: {}", id, article.toString());

        //2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        // target 은 이녀석이 원래 가지고 있던 데이터

        //3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()){

            //400. 잘못된 용청 응답
            log.info("잘못된 요청! id : {}, article : {}",id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }           //ResponseEntity 요청된결과를 가져가는 매서드
                    // status(HttpStatus.BAD_REQUEST) 내가 강제로 만든 요청(여기선 400)
                    //.body(null); body엔 null값 반환

        //4. 업데이트 및 정상응답(200) 하기
        target.patch(article);
        //조회를 한녀석을 article로 패치(수정)해줘

        Article updated = articleRepository.save(target);
                            //최종적으로 save해줘
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }           //ResponseEntity 요청된결과를 가져가는 매서드
                // status(HttpStatus.OK) 내가 강제로 만든 요청(여기선 200) 정상
                //.body(updated); 바디를 업데이트해줘


}
