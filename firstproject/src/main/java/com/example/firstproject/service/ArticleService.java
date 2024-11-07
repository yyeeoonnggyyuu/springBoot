package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service //객체선언
public class ArticleService {


    @Autowired
    private ArticleRepository articleRepository; //서비스 객체 주입


//GET
    public List<Article> index() {
        return articleRepository.findAll();
    }

    // GET id
    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    //PATCH
    public Article update(Long id, ArticleForm dto) {
        //1.DTO -> 엔티티 변환
        Article article = dto.toEntity();
        log.info("id : {}, aritcle: {}", id, article.toString());

        //2.타겟 조회하기
            Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()){
            //400번, 잘못된 응답요청
            log.info("잘못된 요청! id: {} , article: {}", id, article.toString());
            return null;
        }

        //4. 업데이트 및 정상 응답(200)하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    //DELETE
    public Article delete(Long id) {
        //1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2. 잘못된 요청 찾기
        if(target == null){
            return null;
        }
        //3.대상 삭제
        articleRepository.delete(target);
        return target;
    }

    //트래젝션 테스트
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {

//         1.DTO 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream() //,stream() 리스트를 하나하나 별도를 묶음으로 처리 순환적으로 처리
                .map(dto -> dto.toEntity())       // 엔티티로 처리한 거를 map에 담기
                .collect(Collectors.toList());    // ???
        //와 이부분 뭐지 ..?
//         2. 엔티티 묶음을 DB에 저장하기
            articleList.stream()  //stream이란 하나하나를 순환? 순차적으로 처리해줌
                    .forEach(article -> articleRepository.save(article));  //forEach문으로 save를 stream을 이용하여 하나하나 save 처리
//         3. 강제 예외 발생시키기
            articleRepository.findById(-1L)
                    .orElseThrow(() -> new IllegalArgumentException("결제 실패!"));
            //orElseThrow 는 올바른값이 넘어오면 넘어가고 올바른값이 아니면 Throw 시킴
//         4. 결과 값 반환하기
            return articleList;
    }
}
