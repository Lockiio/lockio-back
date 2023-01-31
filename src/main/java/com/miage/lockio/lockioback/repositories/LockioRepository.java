package com.miage.lockio.lockioback.repositories;

import com.miage.lockio.lockioback.entities.Lockio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockioRepository extends JpaRepository<Lockio, Long> {

}
