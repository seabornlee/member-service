package com.seabornlee.springboot.memberservice.domain.querycondition;

import com.potato.cloud.common.dao.QueryCondition;
import lombok.Data;

import java.util.Map;

/**
 * 库位查询条件
 *
 * Created by sh on 2018/8/10.
 */
@Data
public class WarehouseBinQueryCondition extends QueryCondition {

    public Integer tenant_id; // 租户id
    public String bin_no; // 库位编号
    public Integer bind_rfid; // 是否已绑定RFID

    public WarehouseBinQueryCondition(Map params) {
        super(params);
    }
}
