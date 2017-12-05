package mapper;

import bean.user;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by mac on 16/7/18.
 */
public interface UserMapper {
    @Select("SELECT * FROM user WHERE userId = #{userId}")
    user getUser(@Param("userId")String userId);
}
