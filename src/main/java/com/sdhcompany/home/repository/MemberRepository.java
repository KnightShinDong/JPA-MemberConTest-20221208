package com.sdhcompany.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdhcompany.home.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	public Member findByMid(String mid);
	
	
}
