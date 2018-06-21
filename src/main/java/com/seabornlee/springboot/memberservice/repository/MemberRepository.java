package com.seabornlee.springboot.memberservice.repository;

import com.seabornlee.springboot.memberservice.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
