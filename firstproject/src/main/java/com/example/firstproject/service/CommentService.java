package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    //1. 댓글 조회(Read)
    public static List<CommentDto> comments(Long articleId) {
        //1. 댓글 조회
      야ㅕㄱ임ㄴ어ㅣㅁㄴ어ㅣㅏㄴ머이ㅏㄴㅁ어ㅣㅏㄴㅁ어ㅣㅏㄴ머임너이ㅓㄴ미엉니멍니ㅓㅇ니ㅏㄴ맡ㅋㅊㅋㅌㅊㅋㅌㅊㄴㅁㅇ
      야ㅕㄱ임ㄴ어ㅣㅁㄴ어ㅣㅏㄴ머이ㅏㄴㅁ어ㅣㅏㄴㅁ어ㅣㅏㄴ머임너이ㅓㄴ미엉니멍니ㅓㅇ니ㅏㄴ맡ㅋㅊㅋㅌㅊㅋㅌㅊㄴㅁㅇ
      야ㅕㄱ임ㄴ어ㅣㅁㄴ어ㅣㅏㄴ머이ㅏㄴㅁ어ㅣㅏㄴㅁ어ㅣㅏㄴ머임너이ㅓㄴ미엉니멍니ㅓㅇ니ㅏㄴ맡ㅋㅊㅋㅌㅊㅋㅌㅊㄴㅁㅇ
      야ㅕㄱ임ㄴ어ㅣㅁㄴ어ㅣㅏㄴ머이ㅏㄴㅁ어ㅣㅏㄴㅁ어ㅣㅏㄴ머임너이ㅓㄴ미엉니멍니ㅓㅇ니ㅏㄴ맡ㅋㅊㅋㅌㅊㅋㅌㅊㄴㅁㅇ
      야ㅕㄱ임ㄴ어ㅣㅁㄴ어ㅣㅏㄴ머이ㅏㄴㅁ어ㅣㅏㄴㅁ어ㅣㅏㄴ머임너이ㅓㄴ미엉니멍니ㅓㅇ니ㅏㄴ맡ㅋㅊㅋㅌㅊㅋㅌㅊㄴㅁㅇ
      야ㅕㄱ임ㄴ어ㅣㅁㄴ어ㅣㅏㄴ머이ㅏㄴㅁ어ㅣㅏㄴㅁ어ㅣㅏㄴ머임너이ㅓㄴ미엉니멍니ㅓㅇ니ㅏㄴ맡ㅋㅊㅋㅌㅊㅋㅌㅊㄴㅁㅇ
      야ㅕㄱ임ㄴ어ㅣㅁㄴ어ㅣㅏㄴ머이ㅏㄴㅁ어ㅣㅏㄴㅁ어ㅣㅏㄴ머임너이ㅓㄴ미엉니멍니ㅓㅇ니ㅏㄴ맡ㅋㅊㅋㅌㅊㅋㅌㅊㄴㅁㅇ
      야ㅕㄱ임ㄴ어ㅣㅁㄴ어ㅣㅏㄴ머이ㅏㄴㅁ어ㅣㅏㄴㅁ어ㅣㅏㄴ머임너이ㅓㄴ미엉니멍니ㅓㅇ니ㅏㄴ맡ㅋㅊㅋㅌㅊㅋㅌㅊㄴㅁㅇ
        //2. 엔티티 -> DTO 변환
        //3. 결과 반환
    }
}
