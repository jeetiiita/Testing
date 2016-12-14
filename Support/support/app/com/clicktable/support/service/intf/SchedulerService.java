package com.clicktable.support.service.intf;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.clicktable.response.BaseResponse;

/**
 * @author p.vishwakarma
 */
@Service
public interface SchedulerService {

    public BaseResponse runInvoiceGenrationScheduler(Map<String, Object> params);

}
