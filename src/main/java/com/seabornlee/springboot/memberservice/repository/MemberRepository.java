package com.seabornlee.springboot.memberservice.repository;

import com.seabornlee.springboot.memberservice.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
