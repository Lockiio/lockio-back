package com.miage.lockio.lockioback.dao.repositories;

import com.miage.lockio.lockioback.entities.Lockio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LockioRepository extends JpaRepository<Lockio, Long> {

    Lockio findByBlockIdAndLocalId(Long block_id, Long local_id);

    List<Lockio> findAllByBlockId(Long block_id);
}
