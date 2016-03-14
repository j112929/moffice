/**
 * 宝龙电商
 * com.plocc.console.web.ask
 * AskController.java
 * <p/>
 * 2016-01-04
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.web;

import com.plocc.framework.entity.Model;
import com.plocc.framework.support.WebHelper;
import com.plocc.framework.web.BaseController;
import com.plocc.im.entity.AutoReplyWordEntity;
import com.plocc.im.repository.AutoReplyWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * AskController
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 17:25
 * @email zhanggj@powerlong.com
 * @description 自动回复关键字管理
 */
@Controller
@RequestMapping("/im/arw/")
public class AutoReplyWordController extends BaseController {
    @Autowired
    private AutoReplyWordRepository askReplyWordDbRepository;
    @Autowired
    @Qualifier("imRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "/im/arw/index";
    }

    @RequestMapping(value = "/ajax")
    @ResponseBody
    public Model ajax(HttpServletRequest request, Pageable pageable) {
        Model result = super.ajax();
        AutoReplyWordEntity input = new AutoReplyWordEntity(WebHelper.getInput(request));
        switch (input.getInt("action")) {
            case 1:
                // 查询
                if (input.isEmpty("word")) {
                    result.set("pager", askReplyWordDbRepository.findAllByOrderByCreateDateDesc(pageable));
                } else {
                    result.set("pager", askReplyWordDbRepository.findByWordContainingOrderByCreateDateDesc(input.getString("word"), pageable));
                }
                break;
            case 2:
                // 新增
                Model fieldError = null;// askReplyWordDbRepository.validation(input);
                if (!fieldError.isEmpty()) {
                    result.setErrorField(fieldError);
                    result.setSuccess(false);
                } else {
                    //input.setCreateDate(new Date());
                    askReplyWordDbRepository.save(input);
                }
                break;
            case 3:
                // 删除
                input = askReplyWordDbRepository.findById(input.getId());
                if (null == input) {
                    result.setSuccess(false);
                    result.setMessage("记录不存在");
                } else {
                    askReplyWordDbRepository.delete(input);
                }
                break;
            case 1024:
                // 载入Redis
                stringRedisTemplate.delete("im_auto_reply_word");
                HashOperations hashOperations = stringRedisTemplate.opsForHash();
                Page<AutoReplyWordEntity> page = askReplyWordDbRepository.findAllByOrderByCreateDateDesc(pageable);
                do {
                    for (AutoReplyWordEntity autoReplyWordEntity : page.getContent()) {
                        hashOperations.put("im_auto_reply_word", autoReplyWordEntity.getMall() + ":" + autoReplyWordEntity.getWord(), autoReplyWordEntity.getWeight() + ":" + autoReplyWordEntity.getReply());
                    }
                    page = askReplyWordDbRepository.findAllByOrderByCreateDateDesc(new PageRequest(page.getNumber() + 1, page.getSize()));
                } while (page.hasNext());
                break;
        }
        return result;
    }
}
