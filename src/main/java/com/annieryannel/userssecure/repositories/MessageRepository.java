package com.annieryannel.userssecure.repositories;

import com.annieryannel.userssecure.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Set<Message> findAllByReceiverOrderByDateDesc(Long receiver);
}
