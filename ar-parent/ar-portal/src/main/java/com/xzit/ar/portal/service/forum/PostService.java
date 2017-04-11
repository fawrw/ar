package com.xzit.ar.portal.service.forum;

import com.xzit.ar.common.exception.ServiceException;
import com.xzit.ar.common.page.Page;
import com.xzit.ar.common.po.info.Comment;

import java.util.List;
import java.util.Map;

/**
 * TODO ${TODO}
 * Created by 董亮亮 on 2017/4/9.
 */
public interface PostService {

    /**
     * TODO 查询论坛帖子列表
     * @param page 分页类
     * @param queryStr 查询条件
     * @return 帖子list
     * @throws ServiceException
     */
    public List<Map<String, Object>> queryPosts(Page<Map<String, Object>> page, String queryStr) throws ServiceException;

    /**
     * TODO 根据info_id查询帖子详情
     * @param postId
     * @return post
     * @throws ServiceException
     */
    public Map<String, Object> getPostById(Integer postId) throws ServiceException;

    /**
     * TODO 动态加载评论列表
     * @param page
     * @param postId
     * @return
     * @throws ServiceException
     */
    public List<Map<String, Object>> dynamicLoadComment(Page<Map<String, Object>> page, Integer postId) throws ServiceException;

    /**
     * TODO 存储评论内容
      * @param comment
     * @return
     * @throws ServiceException
     */
    public Integer commentPost(Comment comment) throws ServiceException;

    /**
     * TODO 喜欢帖子
     * @param postId 帖子id
     * @return 喜欢此帖子的人数
     * @throws ServiceException
     */
    public Integer lovePost(Integer postId) throws ServiceException;
}