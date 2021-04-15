package com.lys.lys_blog.service.impl;

import com.lys.lys_blog.mapper.FriendLinkMapper;
import com.lys.lys_blog.pojo.FriendLink;
import com.lys.lys_blog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Transactional
@Service
public class FriendLinkServiceImpl implements FriendLinkService {
    @Autowired
  private FriendLinkMapper mapper;
    @Override
    public List<FriendLink> listFriendLinks() {
        return mapper.listFriendLinks();
    }

    @Override
    public FriendLink getFriendLink(String blogAddress) {
        return mapper.getFriendLink(blogAddress);
    }

    @Override
    public int saveFriendLink(FriendLink friendLink) {
        friendLink.setCreateTime(new Date());
        return mapper.saveFriendLink(friendLink);
    }

    @Override
    public int updateFriendLink(FriendLink friendLink) {
        return mapper.updateFriendLink(friendLink);
    }

    @Override
    public FriendLink getFriendLinkById(Long id) {
        return mapper.getFriendLinkById(id);
    }

    @Override
    public int delete(Long id) {
        return mapper.delete(id);
    }
}
