package me.shinsunyoung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.dto.AddUserRequest;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    // 이메일과 비밀번호로 사용자를 찾는 메서드
    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        // 사용자가 있으면 비밀번호 비교
        if (user.isPresent() && bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
            return user;  // 비밀번호가 일치하면 사용자 반환
        } else {
            return Optional.empty();  // 일치하지 않으면 빈 Optional 반환
        }
    }
}