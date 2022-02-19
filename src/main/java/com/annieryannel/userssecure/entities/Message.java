package com.annieryannel.userssecure.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message{

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_username")
    private String fromUsername;

    @Column(name = "text")
    private String text;

    @Column(name = "sending_time")
    private Date date;

    @Column(name = "receiver")
    private Long receiver;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromUsername='" + fromUsername + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", receiver=" + receiver +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
