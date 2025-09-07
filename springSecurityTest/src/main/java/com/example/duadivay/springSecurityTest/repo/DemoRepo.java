package com.example.duadivay.springSecurityTest.repo;

import com.example.duadivay.springSecurityTest.entities.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepo extends JpaRepository<DemoEntity, Long> {
}
