package com.clicktable.support.service.intf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clicktable.support.model.State;

@Service
public interface StateService {

    List<State> getState(Map<String, Object> params);

    State addState(State state);

    Boolean disable(Integer id);

}
