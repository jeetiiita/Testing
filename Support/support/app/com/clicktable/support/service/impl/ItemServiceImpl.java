package com.clicktable.support.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clicktable.support.controllers.ItemController;
import com.clicktable.support.dao.intf.ItemDao;
import com.clicktable.support.model.Item;
import com.clicktable.support.service.intf.ItemService;
import com.clicktable.support.util.SupportConstants;

import play.Logger;


@Component
public class ItemServiceImpl implements ItemService {

    private static final Logger.ALogger log = Logger.of(ItemController.class);
    @Autowired
    private ItemDao itemDao;

    @Override
    public Item create(Item item) {
        log.info("ItemServiceImpl.create() start");
        Item itemEntity = itemDao.create(item);
        log.info("ItemServiceImpl.create() end");
        return itemEntity;
    }

    @Override
    public void deactivate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void activate() {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Item> getItems(Map<String, Object> params) {
        params.put(SupportConstants.STATUS, SupportConstants.ACTIVE_STATUS);
        log.info(params.toString());
        List<Item> itemList = itemDao.findByFields(Item.class, params);
        log.debug("ItemServiceImpl.getItems() : " + itemList);
        return itemList;
    }

    @Override
    public Item updateItem(Item item) {
        log.info(item.toString());
        Item updatedEntity = itemDao.update(item);
        log.debug("ItemServiceImpl.updateItem : " + updatedEntity.toString());
        return updatedEntity;
    }

}
