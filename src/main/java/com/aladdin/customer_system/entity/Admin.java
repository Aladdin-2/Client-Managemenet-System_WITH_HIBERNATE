    package com.aladdin.customer_system.entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "admin")
    public class Admin {
        @Id
        @Column(name = "id")
        private Integer id;

        @Column(name = "firstName")
        private String firstName;

        @Column(name = "lastName")
        private String lastName;

        @OneToOne(mappedBy = "entrepreneur")
        private Customer customer;
    }