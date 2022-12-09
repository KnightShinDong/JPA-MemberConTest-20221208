package com.sdhcompany.home.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.sdhcompany.home.dto.MemberDto;
import com.sdhcompany.home.entity.Member;

@SpringBootTest
//@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberServiceTest {

   @Autowired
   MemberService memberService;
   
   @Autowired
   PasswordEncoder passwordEncoder;
   
   
   public Member createMember() {
      
      MemberDto memberDto = new MemberDto();
      memberDto.setMid("tiger11");
      memberDto.setMpw("12345");
      memberDto.setMname("홍길동1");
      memberDto.setMemail("abc1@abc.com");
      
      return Member.createMember(memberDto, passwordEncoder);
   }
   public Member createMember2() {
	      
	      MemberDto memberDto = new MemberDto();
	      memberDto.setMid("fireDog11");
	      memberDto.setMpw("12345");
	      memberDto.setMname("강감찬1");
	      memberDto.setMemail("abdc1@abc.com");
	      
	      return Member.createMember(memberDto, passwordEncoder);
	   }
   
   @Test
   @DisplayName("회원가입 테스트")
   public void saveMemberTest() {
      
      Member member1 = createMember();
      Member savedMember = memberService.saveMember(member1);
      
      assertEquals(member1.getMid(), savedMember.getMid());
   }
   
   @Test
   @DisplayName("중복 회원가입 테스트")
   public void duplicateMemberTest() {
      
      Member member1 = createMember2();
      Member member2 = createMember2();
      
     memberService.saveMember(member1);//fireDog가입
     
     Throwable e = assertThrows(IllegalStateException.class, ()-> {
     memberService.saveMember(member2);});//테스트의 예외처리
     
     assertEquals("이미 가입된 회원입니다!", e.getMessage());
   }
   
   
}