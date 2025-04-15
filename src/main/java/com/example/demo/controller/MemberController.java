package com.example.demo.controller;

import com.example.demo.entities.Address;
import com.example.demo.entities.Member;
import com.example.demo.repositories.MemberRepository;
import com.example.demo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    //Postman
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    //Postman
    @GetMapping("/member/{id}")
    @ResponseBody
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        return ResponseEntity.ok(member);
    }

    //Postman
    @PostMapping("/addmember")
    @ResponseBody
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        System.out.println("Member: " + member);
        Member savedMember = memberRepository.save(member);
        return ResponseEntity.ok(savedMember);
    }

    //Postman
    @PutMapping("/updatemember/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Member existingMember = optionalMember.get();

        existingMember.setFirstName(memberDetails.getFirstName());
        existingMember.setLastName(memberDetails.getLastName());
        existingMember.setEmail(memberDetails.getEmail());
        existingMember.setPhone(memberDetails.getPhone());
        existingMember.setDateOfBirth(memberDetails.getDateOfBirth());

        if (memberDetails.getAddress() != null) {
            Address newAddress = memberDetails.getAddress();
            Address existingAddress = existingMember.getAddress();

            if (existingAddress == null) {
                existingMember.setAddress(newAddress);
            } else {
                existingAddress.setStreet(newAddress.getStreet());
                existingAddress.setCity(newAddress.getCity());
                existingAddress.setPostalCode(newAddress.getPostalCode());
            }
        }

        Member updatedMember = memberService.updateMember(existingMember);

        return ResponseEntity.ok(updatedMember);
    }

    //Postman
    @DeleteMapping ("/deletememberbyid/{Id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long Id) {
        memberService.deleteMemberById(Id);
        return ResponseEntity.noContent().build();
    }

    //Thymeleaf
    @GetMapping("/deletemember")
    public String showAllMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "deletemember";
    }

    //Thymeleaf address
    @GetMapping("/deletemember/{Id}")
    public String deleteThymeMember(@PathVariable Long Id) {
        memberService.deleteMemberById(Id);
        return "redirect:/admin/deletemember";
    }

}
