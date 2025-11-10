package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Counter;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CountersMapper {

  @Select("SELECT id, count FROM Counters WHERE id = #{id}")
  Counter getCounter(@Param("id") Integer id);

  @Insert({
          "<script>",
          "INSERT INTO Counters (id, count) VALUES (#{id}, #{count})",
          "ON DUPLICATE KEY UPDATE count = #{count}",
          "</script>"
  })
  void upsertCount(Counter counter);

  @Delete("DELETE FROM Counters WHERE id = #{id}")
  void clearCount(@Param("id") Integer id);
}
