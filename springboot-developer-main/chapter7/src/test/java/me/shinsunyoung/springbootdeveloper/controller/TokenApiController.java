package me.shinsunyoung.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.shinsunyoung.springbootdeveloper.config.jwt.JwtFactory;
import me.shinsunyoung.springbootdeveloper.config.jwt.JwtProperties;
import me.shinsunyoung.springbootdeveloper.domain.RefreshToken;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.dto.CreateAccessTokenRequest;
import me.shinsunyoung.springbootdeveloper.repository.RefreshTokenRepository;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenApiController {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        userRepository.deleteAll();
    }
    //초기화
    //테스트 때마다 앞에 남아 있는 정보로 인해 오류 발생 또는 내가 생각하지 못한 결과가 반환됨 그러므로 BeforEach문으로 초기화

    @DisplayName("createNewAccessToken: 새로운 액세스 토큰을 발급한다.")
    @Test
    public void createNewAccessToken() throws Exception {
        //given

        final String url = "/api/token";

        User testUser = userRepository.save(User.builder()
                .email("user@gamil.com")
                .password("test")
                .build());

        String refreshToken = JwtFactory.builder()
                            //config.jwt 의 JwtFactory에 매서드 있음
                .claims(Map.of("id", testUser.getId()))
                .build()
                .createToken(jwtProperties);

        refreshTokenRepository.save(new RefreshToken(testUser.getId(), refreshToken));

        CreateAccessTokenRequest request = new CreateAccessTokenRequest();
        request.setRefreshToken(refreshToken);
        final String requestBody =objectMapper.writeValueAsString(request);
                                    //Json값으로 Mapping

        //when

        ResultActions resultActions = mockMvc.perform(post(url)
                                                    //final String url = "/api/token";
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));



        //then
       resultActions
               .andExpect(status().isCreated())
                            //OK 201번이 뜨느냐
               .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }
}
