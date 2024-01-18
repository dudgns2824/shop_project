package com.dudgns.purchase.item.service;

import com.dudgns.purchase.common.UuidStringUtil;
import com.dudgns.purchase.entity.ItemEntity;
import com.dudgns.purchase.item.dto.RequestItemInfoDto;
import com.dudgns.purchase.item.dto.ResponseItemInfoDto;
import com.dudgns.purchase.repository.ItemRepository;
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

        } else {
            throw
        }

        return ResponseItemInfoDto.builder()

                .build();
    }
}
