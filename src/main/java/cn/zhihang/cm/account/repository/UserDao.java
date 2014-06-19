package cn.zhihang.cm.account.repository;

import cn.zhihang.cm.account.entity.User;
import cn.zhihang.cm.base.repository.BaseDao;

/**
  * UserDao
  * @author cnlyml
  * @date 2014-6-18
 */
public interface UserDao extends BaseDao<User, Integer> {
    /**
      * 根据用户名查找用户
      * @param u_name 用户名
      * @param is_delete 指定是否删除
      * @return
     */
    public User findByUserNameAndIsdelete(String u_name, Integer is_delete);
    
    /**
      * 根据用户名查找用户
      * @param u_name 用户名
      * @return
     */
    public User findByUserName(String u_name);
}
