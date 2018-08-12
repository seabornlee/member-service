package com.seabornlee.springboot.memberservice.service;

import com.github.pagehelper.PageInfo;
import com.potato.cloud.common.CallResult;
import com.potato.cloud.common.dao.PageResult;
import com.seabornlee.springboot.memberservice.domain.WarehouseBin;
import com.seabornlee.springboot.memberservice.domain.querycondition.WarehouseBinQueryCondition;

public interface IWarehouseBinService {

    /**
     * 根据条件查询库位
     *
     * @param params
     * @return
     */
    PageResult<WarehouseBin> query(WarehouseBinQueryCondition params);

    /**
     * 库位绑定RFID
     *
     * @param binNo
     * @param rfid
     * @return
     */
    CallResult<WarehouseBin> bindRfid(String binNo, String rfid);

    public PageInfo<WarehouseBin> getListByPage(WarehouseBin query, int page, int size);

    public Boolean existWarehouseBin(WarehouseBin warehouseBin);

    public void saveWarehouseBin(WarehouseBin warehouseBin);

    public void saveOrUpdate(WarehouseBin warehouseBin);

    public void updateWarehouseBin(WarehouseBin warehouseBin);

    public void deleteWarehouseBin(WarehouseBin warehouseBin);
}
