package com.cydeo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

private Boolean isDeleted=false; // soft deletion from the database when isDeleted is set to true

    @Column(nullable=false, updatable = false)
    private LocalDateTime insertDateTime; // we do not want these fields null or updated

    @Column(nullable = false,updatable = false)
    private Long insertUserId; // whoever is logged in to the system, their id will be stored in this field

    @Column(nullable = false,updatable = false)
    private LocalDateTime lastUpdateDateTime;

    @Column(nullable = false,updatable = false)
    private Long lastUpdateUserId;


    @PrePersist //whenever we want to persist something this method will be executed first
    private void onPrePersist(){
        this.insertDateTime=LocalDateTime.now();
        this.lastUpdateDateTime=LocalDateTime.now();

        this.insertUserId=1L;
        this.lastUpdateUserId=1L;
    }

    @PreUpdate// whenever we want to update an object this method will be executed
    private void onPreUpdate(){
        // if the following fields are null we will have an error
        this.lastUpdateDateTime=LocalDateTime.now();
        this.lastUpdateUserId=1L;

    }

}
