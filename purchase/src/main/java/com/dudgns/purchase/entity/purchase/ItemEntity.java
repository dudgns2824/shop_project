package com.dudgns.purchase.entity.purchase;

import com.dudgns.purchase.entity.auth.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenerationTime;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = UserEntity.TABLE_NAME)
public class ItemEntity {
    public static final String TABLE_NAME = "item";

    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    @Column(insertable = false, updatable = false, columnDefinition = "int auto_increment")
    private Integer idx;

    @Id
    @Column(name = "item_guid", nullable = false, updatable = false, columnDefinition = "varBinary(36)")
    private UUID ItemGUID;

    @Column(name = "price")
    private Long price;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "company")
    private String company;

    @Column(name = "description")
    private String description;

    @Column(name = "sale")
    private Boolean sale;
}
