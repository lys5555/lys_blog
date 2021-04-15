package com.lys.lys_blog.service;

import com.lys.lys_blog.pojo.FriendLink;

import java.util.List;

public interface FriendLinkService {
    List<FriendLink> listFriendLinks();
    FriendLink getFriendLink(String blogAddress);
    int saveFriendLink(FriendLink friendLink);

    int updateFriendLink(FriendLink friendLink);

    FriendLink getFriendLinkById(Long id);

    int delete(Long id);
}
