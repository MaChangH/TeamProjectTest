package com.teamproject.teamproject.member;

import java.util.List;

public interface MemberMapper {

	// Id 가져오기
	public abstract List<Member> getById(Member m);
	
	// 닉네임 가져오기
	public abstract List<Member> getByNickname(Member m);
	
	// 회원 가입하기
	public abstract int join(Member m);
	
	// 회원 탈퇴하기
	public abstract int bye(Member m);
	
	// 정보 수정하기
	public abstract int update(Member m);
}
