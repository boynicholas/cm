package cn.zhihang.cm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import cn.zhihang.cm.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
}
