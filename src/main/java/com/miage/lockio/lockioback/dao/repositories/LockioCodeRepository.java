package com.miage.lockio.lockioback.dao.repositories;

import com.miage.lockio.lockioback.entities.LockioCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockioCodeRepository extends JpaRepository<LockioCode, Long> {
}
