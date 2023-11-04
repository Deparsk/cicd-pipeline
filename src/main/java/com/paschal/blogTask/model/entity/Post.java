package com.paschal.blogTask.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Post_Table")
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;

    private String content;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "postEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> commentEntity;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> likeEntity;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userEntity;

    @Transient
    @ManyToOne (fetch = FetchType.LAZY)
    private Admin AdminEntity;

    @Transient
    private Long commentCount;

    @Transient
    private Long likeCount;

    // Constructors, getters, setters, and other methods
}
