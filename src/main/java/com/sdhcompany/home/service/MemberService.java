package com.sdhcompany.home.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdhcompany.home.entity.Member;
import com.sdhcompany.home.repository.MemberRepository;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	private void validateDuplicateMember(Member member) {
		
		Member resultMember = memberRepository.findByMid(member.getMid());
		
		if(resultMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다!");
		}
		
	}
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		Member member1=memberRepository.save(member);
		 
		return memberRepository.save(member); //member1
		
	}
}
