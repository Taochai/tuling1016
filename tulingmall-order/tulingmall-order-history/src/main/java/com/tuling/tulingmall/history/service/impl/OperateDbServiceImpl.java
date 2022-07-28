package com.tuling.tulingmall.history.service.impl;

import com.tuling.tulingmall.history.dao.PortalOrderDao;
import com.tuling.tulingmall.history.domain.*;
import com.tuling.tulingmall.history.service.OperateDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 订单迁移管理Service实现
 */
@Service
@Slf4j
public class OperateDbServiceImpl implements OperateDbService {

    @Autowired
    private PortalOrderDao portalOrderDao;

    @Override
    public List<OmsOrderDetail> getOrders(long maxOrderId,int tableCount,Date endDate,int fetchRecordNumbers) {
        String omsOrderTableName = OrderConstant.OMS_ORDER_NAME_PREFIX + tableCount;
        String omsOrderItemTableName = OrderConstant.OMS_ORDER_ITEM_NAME_PREFIX + tableCount;
        return portalOrderDao.getRangeOrders(omsOrderTableName,omsOrderItemTableName,maxOrderId,
                endDate,fetchRecordNumbers);
    }

    @Override
    public void deleteOrders(int tableCount,Date endDate,long maxOrderId) {
        String omsOrderTableName = OrderConstant.OMS_ORDER_NAME_PREFIX + tableCount;
        String omsOrderItemTableName = OrderConstant.OMS_ORDER_ITEM_NAME_PREFIX + tableCount;
        int deleteCount = portalOrderDao.deleteMigrateOrdersItems(omsOrderTableName,omsOrderItemTableName,maxOrderId,
                endDate);
        log.info("已删除表{}中{}条数据，参数endDate={},maxOrderId={}",
                omsOrderItemTableName,deleteCount,endDate,maxOrderId);
        deleteCount = portalOrderDao.deleteMigrateOrders(omsOrderTableName,maxOrderId,endDate);
        log.info("已删除表{}中{}条数据，参数endDate={},maxOrderId={}",
                omsOrderTableName,deleteCount,endDate,maxOrderId);
    }

}
