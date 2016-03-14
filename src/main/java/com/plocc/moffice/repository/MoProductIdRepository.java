package com.plocc.moffice.repository;

import com.plocc.moffice.entity.MoProductIdEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.math.BigInteger;
import java.util.List;

public interface MoProductIdRepository extends CrudRepository<MoProductIdEntity, String> {

    @Modifying
    @Transactional
    @Query(value = "delete from TB_MO_PRODUCT_ID where APP_ID= ?1 and VERSION < ?2", nativeQuery = true)
    void deleteByVersion(String appId, Integer version);

    @Query(value = "select m.PRODUCT_ID from TB_MO_PRODUCT_ID m where m.APP_ID = ?1 and m.VERSION = ?2 and m.PRODUCT_ID not in " +
            "(select mo.PRODUCT_ID from TB_MO_PRODUCT_ID mo where mo.APP_ID = ?1 and mo.VERSION = ?3 )", nativeQuery = true)
    List<BigInteger> findDeleteIdList(String appId, Integer oldVersion, Integer newVersion);

    @Query(value = "select m.VERSION from TB_MO_PRODUCT_ID m where m.APP_ID = ?1 order by m.VERSION desc limit 1", nativeQuery = true)
    Integer findMaxVersionByAppId(String appId);

}
