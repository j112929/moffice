/**
 * 宝龙电商
 * com.plocc.im.web
 * SdkController.java
 * <p/>
 * 2016-01-29
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.web;

import com.plocc.framework.entity.JsonResult;
import com.plocc.framework.support.DataHelper;
import com.plocc.framework.support.DateHelper;
import com.plocc.framework.support.Md5Helper;
import com.plocc.framework.support.ValidHelper;
import com.plocc.im.entity.ImContactEntity;
import com.plocc.im.entity.ImUserEntity;
import com.plocc.im.repository.ImGroupRepository;
import com.plocc.im.repository.ImHistoryRepository;
import com.plocc.im.repository.ImMallRepository;
import com.plocc.im.repository.ImUserRepository;
import com.plocc.im.service.ImServer;
import com.plocc.im.support.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SdkController
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 18:08
 * @email zhanggj@powerlong.com
 * @description im sdk http 接口
 */
@RestController
public class SdkController {
    @Autowired
    private ImServer imServer;
    @Autowired
    private ImHistoryRepository imHistoryRepository;
    @Autowired
    private ImUserRepository imUserRepository;
    @Autowired
    private ImGroupRepository imGroupRepository;
    @Autowired
    private ImMallRepository imMallRepository;

    /**
     * 获取最近60条会话记录
     *
     * @param mall
     * @return
     */
    @RequestMapping(value = "/sdk/im/sessions")
    public JsonResult getSessions(@RequestParam("mall") String mall) {
        JsonResult result = new JsonResult();
        ImUserEntity imUserEntity = SessionHelper.getLoginUser();
        List<ImContactEntity> imContactEntityList = imServer.getContactList(DataHelper.getString(imUserEntity.getId()), mall, 60);
        for (ImContactEntity imContactEntity : imContactEntityList) {
            if (DataHelper.getLong(imContactEntity.getTid()) > 0) {
                // 未绑定的微信用户 无头像、昵称信息 忽略详情查询
                ImUserEntity ucUser = imUserRepository.findOne(DataHelper.getLong(imContactEntity.getTid()));
                if (null != ucUser) {
                    imContactEntity.setNickname(ValidHelper.isEmpty(ucUser.getNickname()) ? DataHelper.getString(ucUser.getId()) : ucUser.getNickname());
                    imContactEntity.setHead(ucUser.getHead());
                    imContactEntity.setSex(DataHelper.getInt(ucUser.getSex()));
                }
            } else {
                imContactEntity.setNickname("微信用户");
            }
        }
        result.setData(imContactEntityList);
        return result;
    }

    /**
     * 获取单个会话详细信息
     *
     * @param uid
     * @return
     */
    @RequestMapping(value = "/sdk/im/session")
    public JsonResult getSession(@RequestParam("uid") String uid) {
        JsonResult result = new JsonResult();
        if (DataHelper.getLong(uid) > 0) {
            ImUserEntity ucUser = imUserRepository.findOne(DataHelper.getLong(uid));
            if (null != ucUser) {
                result.setSuccess(true);
                Map ucUserInfo = new HashMap();
                ucUserInfo.put("nickname", ValidHelper.isEmpty(ucUser.getNickname()) ? DataHelper.getString(ucUser.getId()) : ucUser.getNickname());
                ucUserInfo.put("head", ucUser.getHead());
                ucUserInfo.put("sex", ucUser.getSex());
                result.setData(ucUserInfo);
            } else {
                result.setSuccess(false);
            }
        } else {
            Map ucUserInfo = new HashMap();
            ucUserInfo.put("nickname", "微信用户");
            result.setData(ucUserInfo);
        }
        return result;
    }

    /**
     * 获取历史聊天记录
     *
     * @param tid
     * @param mall
     * @param timer
     * @param index
     * @return
     */
    @RequestMapping(value = "/sdk/im/history")
    public JsonResult getImHistoryList(@RequestParam("tid") String tid, @RequestParam("mall") String mall, @RequestParam("timer") long timer, @RequestParam("index")
    int index) {
        JsonResult result = new JsonResult();
        ImUserEntity imUserEntity = SessionHelper.getLoginUser();
        Pageable pageable = new PageRequest(index - 1, 6);
        List history = imHistoryRepository.findByMidAndTidAndMallAndSendLessThanOrderBySendDesc(String.valueOf(imUserEntity.getId()), tid, mall, new Date(timer), pageable);
        if (history.size() == 0) {
            result.setSuccess(false);
        } else {
            result.setSuccess(true);
        }
        result.setData(history);
        return result;
    }

    /**
     * 群组管理
     *
     * @return
     */
    @RequestMapping(value = "/sdk/im/groups")
    public JsonResult getGroups() {
        JsonResult result = new JsonResult();
        result.setData(imGroupRepository.findAll());
        return result;
    }

    /**
     * 群组管理
     *
     * @return
     */
    @RequestMapping(value = "/sdk/im/malls")
    public JsonResult getMalls() {
        JsonResult result = new JsonResult();
        result.setData(imMallRepository.findAll());
        return result;
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/sdk/im/login")
    public JsonResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        ImUserEntity imUserEntity = imUserRepository.findByUsername(username);
        if (null == imUserEntity) {
            result.setCode(10001);
            result.getConfig().setErrorMessage("用户不存在");
        } else if (!Md5Helper.checkPassword(password, imUserEntity.getPassword())) {
            result.setCode(10002);
            result.getConfig().setErrorMessage("密码错误");
        } else {
            result.setSuccess(true);
            result.setData(imUserEntity);
            SessionHelper.setLogin(imUserEntity);
        }
        return result;
    }

    /**
     * 检测登录状态
     *
     * @return
     */
    @RequestMapping(value = "/sdk/im/login/check")
    public JsonResult loginCheck() {
        long timer = System.currentTimeMillis();
        JsonResult result = new JsonResult();
        ImUserEntity imUserEntity = SessionHelper.getLoginUser();
        if (null != imUserEntity) {
            result.setSuccess(true);
            result.setData(imUserEntity);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping(value = "/sdk/im/logout")
    public JsonResult logout() {
        JsonResult result = new JsonResult();
        if (SessionHelper.isLogin()) {
            SessionHelper.logout();
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(DateHelper.format("yyy-MM-dd hh:mm:ss", new Date(1454223463668l)));
        System.out.println(DateHelper.format("yyy-MM-dd hh:mm:ss", new Date(1454339221000l)));
    }
}
