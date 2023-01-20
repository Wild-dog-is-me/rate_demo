package org.dog.server.dao;

import org.dog.server.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/1/20 18:34
 * @Description:
 */
public interface CommentDao extends JpaRepository<Comment, Integer> {

  List<Comment> findAllByForeignId(Integer foreignId);

}
