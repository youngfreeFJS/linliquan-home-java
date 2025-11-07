package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Counter;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CountersMapper {

  @Select("SELECT id, count FROM counter WHERE id = #{id}")
  Counter getCounter(@Param("id") Integer id);

  @Insert({
          "<script>",
          "INSERT INTO counter (id, count) VALUES (#{id}, #{count})",
          "ON DUPLICATE KEY UPDATE count = #{count}",
          "</script>"
  })
  void upsertCount(Counter counter);

  @Delete("DELETE FROM counter WHERE id = #{id}")
  void clearCount(@Param("id") Integer id);
}
