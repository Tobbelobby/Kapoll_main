package com.example.Kapoll;

import com.example.Kapoll.Kapoll_db.tables.Kapoller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KapollRepository extends CrudRepository<Kapoller, Long> {
}
