package com.plocc.moffice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.VendorsClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.VendorEntity;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.VendorsService;
import com.plocc.moffice.support.Constants;

/**
 * 供应商操作
 *
 * @author xing_biao
 *         2016年2月23日
 * @version 1.0.0
 */
@Service
public class VendorsServiceImpl implements VendorsService {

    private final Logger LOGGER = LoggerFactory.getLogger(VendorsServiceImpl.class);
    
    @Autowired
    private VendorsClient vendorsClient;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    @Qualifier("mofficeStringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * @see com.plocc.moffice.service.VendorsService#add(java.util.List)
     */
    @Override
    public void add(CredentialEntity credentialEntity) throws Exception {
        CredentialEntity credentialToken = null;
        try {
            credentialToken = credentialService.getMastCredential();
            String vendorCode = "";
            VendorEntity vendorInfoEntity = null;
            vendorCode = credentialEntity.getAppId();
            vendorInfoEntity = this.findVendorByCode(credentialToken, vendorCode);
            if (null == vendorInfoEntity) {
                vendorInfoEntity = new VendorEntity();
                vendorInfoEntity.setVendorCode(vendorCode); // appId 作为供应商的code
                vendorInfoEntity.setVendorName(credentialEntity.getTitle());
                vendorInfoEntity.setStatus("ACTIVE");
                vendorInfoEntity.setTaxType("LIABLE");
                vendorsClient.add(credentialToken, vendorInfoEntity);
            }
        }
        catch (Exception e) {
            LOGGER.error("add 出错:credential=" + credentialEntity + ", credentialToken=" + credentialToken, e);
        }
    }

    /**
     * @see com.plocc.moffice.service.VendorsService#findById(com.plocc.moffice.entity.CredentialEntity, java.lang.Integer)
     */
    @Override
    public VendorEntity findById(CredentialEntity credential, Integer id) throws Exception {
        VendorEntity vendorInfoEntity = null;
        try {
            vendorInfoEntity = vendorsClient.findById(credential, id);
        }
        catch (Exception e) {
            LOGGER.error("findById 出错:credential=" + credential + ", id=" + id, e);
        }
        return vendorInfoEntity;
    }

    /**
     * @see com.plocc.moffice.service.VendorsService#findVendorByCode(com.plocc.moffice.entity.CredentialEntity, java.lang.String)
     */
    @Override
    public VendorEntity findVendorByCode(CredentialEntity credential, String vendorCode) throws Exception {
        VendorEntity vendorInfoEntity = null;
        try {
            // 供应商信息放到redis
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String key = Constants.MOFFICE_REDIS_VENDOR + vendorCode;
            String vendorStr = operations.get(key);
            // 如果redis里面没有，则放信息进入
            if (null == vendorStr) {
                vendorInfoEntity = vendorsClient.findVendorByCode(credential, vendorCode);
                vendorStr = JsonpHelper.toString(vendorInfoEntity);
                operations.set(key, vendorStr);
            }
            vendorInfoEntity = JsonpHelper.toObject(vendorStr, VendorEntity.class);
        }
        catch (Exception e) {
            LOGGER.error("findVendorByCode 出错:credential=" + credential + ", vendorCode=" + vendorCode, e);
        }
        return vendorInfoEntity;
    }

}
