package com.clicktable.support.service.intf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clicktable.support.model.Item;

/**
 * @author j.yadav
 */

@Service
public interface ItemService {

    //From Admin console only

    /**
     *
     */
    Item create(Item item);

    /**
     *
     */
    void deactivate();

    /**
     *
     */
    void activate();

    List<Item> getItems(Map<String, Object> params);

    Item updateItem(Item item);

}
