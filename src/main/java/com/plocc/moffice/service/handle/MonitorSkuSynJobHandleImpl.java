package com.plocc.moffice.service.handle;

import com.plocc.framework.support.DataHelper;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.Monitor;
import com.plocc.moffice.entity.SKUInfoEntity;
import com.plocc.moffice.entity.TaskEntity;
import com.plocc.moffice.repository.MoSKURepository;
import com.plocc.moffice.repository.MofficeRedisRepository;
import com.plocc.moffice.service.MonitorJobHandle;
import com.plocc.moffice.support.ChannelEnum;
import com.plocc.moffice.support.TaskTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

/**
 * sku同步 具体job 逻辑
 */
public class MonitorSkuSynJobHandleImpl implements MonitorJobHandle {

    @Autowired
    private MoSKURepository moSKURepository;
    @Autowired
    private MofficeRedisRepository mofficeRedisRepository;

    public void handle(Monitor monitor, CredentialEntity credential, Monitor.Task task) throws Exception {
        Integer maxVersion = DataHelper.getInt(moSKURepository.findMaxVersionByAppId(credential.getAppId()));
        List<BigInteger> list = moSKURepository.findDeleteIdList(credential.getAppId(), maxVersion - 1, maxVersion);
        for (final BigInteger skuId : list) {
            SKUInfoEntity skuInfoEntity = new SKUInfoEntity();
            skuInfoEntity.put("id", skuId);
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setAppId(credential.getAppId());
            taskEntity.setType(TaskTypeEnum.DROP);
            taskEntity.setResult(skuInfoEntity);
            mofficeRedisRepository.createTask(ChannelEnum.SKU_CHANNEL, taskEntity);
        }
    }

}
