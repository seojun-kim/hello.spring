package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //key는 long 값은 Member
    private static long sequence = 0L; //키값을 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) { //이름
        return store.values().stream() //루프로 돌린다
                .filter(member -> member.getName().equals(name)) //member.setName()이 파라미터로 넘어온 name과 같은지 확인
                .findAny(); //이름을 하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store의 member들을 반환
    }
}
