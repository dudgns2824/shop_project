package com.dudgns.purchase.repository.purchase;

import com.dudgns.purchase.entity.purchase.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {
}
