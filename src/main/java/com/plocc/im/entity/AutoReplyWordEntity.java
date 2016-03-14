/**
 * 宝龙电商
 * com.plocc.console.entity.ask
 * AskAutoReplyWordEntity.java
 * <p>
 * 2016-01-04
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.entity;

import com.plocc.framework.entity.Pojo;

import javax.persistence.Entity;
import java.util.Map;

/**
 * AskAutoReplyWordEntity
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 17:29
 * @email zhanggj@powerlong.com
 * @description 问一问自动回复词库
 */
@Entity(name = "tb_im_arw")
public class AutoReplyWordEntity extends Pojo {
    private Integer mall;
    private String word;
    private Integer weight;
    private String reply;

    public AutoReplyWordEntity() {
    }

    public AutoReplyWordEntity(Map entity) {
        putAll(entity);
    }

    public Integer getMall() {
        return getInt("mall");
    }

    public void setMall(Integer mall) {
        set("mall", mall);
    }

    public String getWord() {
        return getString("word");
    }

    public void setWord(String word) {
        set("word", word);
    }

    public Integer getWeight() {
        return getInt("weight");
    }

    public void setWeight(Integer weight) {
        set("weight", weight);
    }

    public String getReply() {
        return getString("reply");
    }

    public void setReply(String reply) {
        set("reply", reply);
    }
}
