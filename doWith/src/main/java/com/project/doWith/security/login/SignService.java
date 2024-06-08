package com.project.doWith.service;

import com.project.doWith.domain.Member;
import com.project.doWith.dto.SignRequest;
import com.project.doWith.dto.SignResponse;
import com.project.doWith.exception.DuplicatedMemberNameException;
import com.project.doWith.exception.MissingRequestParameterException;
import com.project.doWith.repository.MemberRepository;
import com.project.doWith.security.login.Authority;
import com.project.doWith.security.login.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public SignResponse login(SignRequest request) throws Exception {
//        Member member = memberRepository.findByAccount(request.getAccount()).orElseThrow(() ->
//                new BadCredentialsException("잘못된 아이디"));
//
//        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
//            throw new BadCredentialsException("잘못된 비밀번호");
//        }
        Optional<Member> optionalMember = memberRepository.findByAccount(request.getAccount());
        //아이디 존재 여부 따라
        if (optionalMember.isEmpty()) {
            // 계정이 존재하지 않을 경우
            throw new UsernameNotFoundException("존재하지 않는 계정입니다.");
        }
        Member member = optionalMember.get();//계정 존재하는 경우 getMember

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            // 비밀번호가 일치하지 않을 경우
            throw new BadCredentialsException("잘못된 비밀번호입니다.");
        }

        return SignResponse.builder()
                .id(member.getMember_id())
                .account(member.getAccount())
                .name(member.getName())
                .email(member.getEmail())
                .roles(member.getRoles())
                .token(jwtProvider.createToken(member.getAccount(), member.getRoles()))
                .build();

    }

    @Transactional
    public boolean register(SignRequest request) {
        Optional<Member> optionalMember = memberRepository.findByAccount(request.getAccount());
        try {
            if (optionalMember.isPresent()) {
                throw new DuplicatedMemberNameException();
            }

            if (request.getAccount() == null ||
                    request.getPassword() == null ||
                    request.getName() == null ||
                    request.getEmail() == null )
            {
                throw new MissingRequestParameterException();
            }


            Member member = Member.builder()
                    .profile(request.getProfile())
                    .account(request.getAccount())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .email(request.getEmail())
                    .build();
/*
            Credit credit = Credit.builder()
                    .member(member)
                    .build();

*/
            member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));
            memberRepository.save(member);
/*
            creditRepository.save(credit);

 */

            return true;
        } catch(DuplicatedMemberNameException e) {
            throw new DuplicatedMemberNameException();
        } catch(MissingRequestParameterException e) {
            throw new MissingRequestParameterException();
        }
    }


    public SignResponse getMember(String account) throws Exception {
        Member member = memberRepository.findByAccount(account)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));
        return new SignResponse(member);
    }

}