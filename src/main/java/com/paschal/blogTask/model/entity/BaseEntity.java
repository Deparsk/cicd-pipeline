package com.paschal.blogTask.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@DynamicUpdate
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    private static final long serialversionUID = 1L;

    @CreationTimestamp
    private LocalDateTime creationAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;
}
