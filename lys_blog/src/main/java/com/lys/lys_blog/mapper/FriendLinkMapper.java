package com.lys.lys_blog.mapper;

import com.lys.lys_blog.pojo.FriendLink;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendLinkMapper {
    List<FriendLink> listFriendLinks();

    FriendLink getFriendLink(String blogAddress);

    int saveFriendLink(FriendLink friendLink);

    int updateFriendLink(FriendLink friendLink);

    FriendLink getFriendLinkById(Long id);

    int delete(Long id);
}
