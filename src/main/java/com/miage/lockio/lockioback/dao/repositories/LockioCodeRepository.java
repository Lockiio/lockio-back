package com.miage.lockio.lockioback.dao.repositories;

import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.entities.LockioCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockioCodeRepository extends JpaRepository<LockioCode, Long> {
    LockioCode findByLockio(Lockio lockio);
}
