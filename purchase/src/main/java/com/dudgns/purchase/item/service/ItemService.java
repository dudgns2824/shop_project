package com.dudgns.purchase.item.service;

import com.dudgns.purchase.annotation.RedissonLock;
import com.dudgns.purchase.common.UuidStringUtil;
import com.dudgns.purchase.entity.purchase.ItemEntity;
import com.dudgns.purchase.exception.ItemNotExistException;
import com.dudgns.purchase.item.dto.RequestItemInfoDto;
import com.dudgns.purchase.item.dto.RequestItemPurchaseDto;
import com.dudgns.purchase.item.dto.ResponseItemInfoDto;
import com.dudgns.purchase.item.dto.ResponseItemPurchaseDto;
import com.dudgns.purchase.repository.purchase.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ItemService {

    private final ItemRepository itemRepository;

    public ResponseItemInfoDto info(RequestItemInfoDto req) {

        UUID itemGUID = UuidStringUtil.convertFromString(req.getItemGuid());

        Optional<ItemEntity> itemEntityOptional = itemRepository.findById(itemGUID);

        if (itemEntityOptional.isPresent()) {
            ItemEntity item = itemEntityOptional.get();
            return ResponseItemInfoDto.builder()
                    .itemGuid(item.getItemGUID().toString())
                    .amount(item.getAmount())
                    .price(item.getPrice())
                    .company(item.getCompany())
                    .description(item.getDescription())
                    .sale(item.getSale())
                    .build();
        } else {
            throw new ItemNotExistException();
        }

    }

    @RedissonLock(name = "item-purchase", uuid = "#itemGuid")
    public ResponseItemPurchaseDto purchase(String itemGuid) {
        UUID itemGUID = UuidStringUtil.convertFromString(itemGuid);

        Optional<ItemEntity> itemEntityOptional = itemRepository.findById(itemGUID);

        if (itemEntityOptional.isPresent()) {
            return null;
        } else {
            throw new ItemNotExistException();
        }
    }
}
