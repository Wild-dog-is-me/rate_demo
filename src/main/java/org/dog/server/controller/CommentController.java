package org.dog.server.controller;

import org.dog.server.dao.CommentDao;
import org.dog.server.entity.Comment;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Odin
 * @Date: 2023/1/20 18:35
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

  @Resource
  CommentDao commentDao;

  @PostMapping
  public void save(@RequestBody Comment comment) {
    comment.setCreatetime(LocalDateTime.now());
    commentDao.save(comment);
  }

  @GetMapping
  public Map<String, Object> list(@RequestParam Integer foreignId) {
    Map<String, Object> map = new HashMap<>();

    List<Comment> comments = commentDao.findAllByForeignId(foreignId);

    map.put("rate", BigDecimal.ZERO);
    List<Comment> commentList = comments.stream().filter(comment -> comment.getRate() != null).collect(Collectors.toList());
    commentList.stream().map(Comment::getRate).reduce(BigDecimal::add).ifPresent(res -> {
      map.put("rate", res.divide(BigDecimal.valueOf(commentList.size()), 1, RoundingMode.HALF_UP));
    });

    List<Comment> rootComments = comments.stream().filter(comment -> comment.getPid() == null).collect(Collectors.toList());
    for (Comment rootComment : rootComments) {
      rootComment.setChildren(comments.stream().filter(comment -> rootComment.getId().equals(comment.getPid())).collect(Collectors.toList()));
    }
    map.put("comments", rootComments);
    return map;
  }
}
