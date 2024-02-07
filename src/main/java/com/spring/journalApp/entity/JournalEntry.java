package com.spring.journalApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "jounral-entries")
@Data
public class JournalEntry {
    @Id
    private String id;
    private String title;
    private String content;
    private LocalDateTime date;
}
