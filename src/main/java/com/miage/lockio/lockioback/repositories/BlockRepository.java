package com.miage.lockio.lockioback.repositories;

import com.miage.lockio.lockioback.entities.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

}
