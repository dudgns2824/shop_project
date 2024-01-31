package com.dudgns.purchase.item.service;

import com.dudgns.purchase.annotation.RedissonLock;
import com.dudgns.purchase.common.UuidStringUtil;
import com.dudgns.purchase.entity.auth.UserEntity;
import com.dudgns.purchase.entity.purchase.ItemEntity;
import com.dudgns.purchase.exception.ItemNotExistException;
import com.dudgns.purchase.exception.UserNotExistException;
import com.dudgns.purchase.item.dto.RequestItemInfoDto;
import com.dudgns.purchase.item.dto.RequestItemPurchaseDto;
import com.dudgns.purchase.item.dto.ResponseItemInfoDto;
import com.dudgns.purchase.item.dto.ResponseItemPurchaseDto;
import com.dudgns.purchase.repository.auth.UserRepository;
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

    private final UserRepository userRepository;

    public ResponseItemInfoDto info(RequestItemInfoDto req) {

        UUID itemGUID = UuidStringUtil.convertFromString(req.getItemGuid());

        Optional<ItemEntity> itemEntityOptional = itemRepository.findById(itemGUID);

        if (itemEntityOptional.isPresent()) {

            ItemEntity item = itemEntityOptional.get();

            return ResponseItemInfoDto.builder()
                    .itemGuid(item.getItemGuid().toString())
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

    @RedissonLock(name = "item-purchase", uuid = "#itemGuid") /*아직 reddison 개발 중*/
    public ResponseItemPurchaseDto purchase(String itemGuid, String userGuid) {
        Optional<ItemEntity> itemEntityOptional = itemRepository.findById(UuidStringUtil.convertFromString(itemGuid));

        if (itemEntityOptional.isPresent()) {
            Optional<UserEntity> userEntityOptional = userRepository.findById(UuidStringUtil.convertFromString(userGuid));

            if (userEntityOptional.isPresent()) {
                UserEntity userEntity = userEntityOptional.get();


            } else {
                throw new UserNotExistException();
            }


            return null;
        } else {
            throw new ItemNotExistException();
        }
    }
}
