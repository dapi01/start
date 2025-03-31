package org.codenova.start.repository;

import org.apache.ibatis.annotations.Mapper;
import org.codenova.start.entity.Comment;


import java.util.List;

@Mapper
public interface CommentRepository {
    int create(Comment comment);
    List<Comment> findByIsoCode(String isoCode);
    int countByIsoCode(String isoCode);
}
