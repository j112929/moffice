package com.plocc.moffice.repository;

import com.plocc.moffice.entity.MoSKUEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

public interface MoSKURepository extends CrudRepository<MoSKUEntity, String> {
    @Query(value = "select m.SKU_ID from TB_MO_SKU m where m.APP_ID = ?1 and m.VERSION = ?2 and m.SKU_ID not in " +
            "(select mo.SKU_ID from TB_MO_SKU mo where mo.APP_ID = ?1 and mo.VERSION = ?3 )", nativeQuery = true)
    List<BigInteger> findDeleteIdList(String appId, Integer oldVersion, Integer newVersion);

    @Modifying
    @Transactional
    @Query(value = "delete from TB_MO_SKU where APP_ID= ?1 and VERSION < ?2", nativeQuery = true)
    void deleteByVersion(String appId, Integer version);

    MoSKUEntity findFirstByAppIdAndSkuIdAndVersion(String appId, Long skuId, Integer version);

    @Query(value = "select m.VERSION from TB_MO_SKU m where m.APP_ID = ?1 order by m.VERSION desc limit 1", nativeQuery = true)
    Integer findMaxVersionByAppId(String appId);
}
