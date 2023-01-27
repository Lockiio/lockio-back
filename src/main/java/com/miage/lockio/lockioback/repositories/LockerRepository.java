package com.miage.lockio.lockioback.repositories;

import com.miage.lockio.lockioback.entities.Locker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockerRepository extends JpaRepository<Locker, Long> {

}
