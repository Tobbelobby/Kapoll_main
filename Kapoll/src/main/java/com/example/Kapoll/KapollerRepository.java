package com.example.Kapoll;

import Kapoll_db.tables.Kapoller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface KapollerRepository extends JpaRepository<Kapoller, Long> {
}
