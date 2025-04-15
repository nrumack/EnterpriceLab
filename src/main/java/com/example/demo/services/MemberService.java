package com.example.demo.services;

import com.example.demo.entities.Member;

import java.util.List;


public interface MemberService {
    List<Member> getAllMembers();
    Member getMemberById(Long id);
    void deleteMemberById(Long id);
    Member updateMember(Member member);
}
