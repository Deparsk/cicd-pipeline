package com.paschal.blogTask.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Comment_Table")
@Builder
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String comment;

    @CreationTimestamp
    private LocalDateTime commentDate;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post postEntity;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userEntity;

    @Transient
    @ManyToOne (fetch = FetchType.LAZY)
    private Admin AdminEntity;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User;

//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;







//    @Transient
//    @ManyToOne (fetch = FetchType.LAZY)
//    private Admin AdminEntity;

    // Constructors, getters, setters, and other methods
}
