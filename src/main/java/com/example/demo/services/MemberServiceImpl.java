package com.example.demo.services;

import com.example.demo.entities.Member;
import com.example.demo.repositories.MemberRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public List<Member> getAllMembers() { return memberRepository.findAll(); }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member with id: " + id + " not found"));
    }

    @Override
    public void deleteMemberById(Long id) {
        if(!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Member with id: " + id + " not found");
        }
        memberRepository.deleteById(id);
    }

    @Override
    public Member updateMember(Member member) {
        if (member.getId() == null || memberRepository.existsById(member.getId())) {
            throw new ResourceNotFoundException("Member with id: " + member.getId() + " not found");
        }
        return memberRepository.save(member);
    }
}
