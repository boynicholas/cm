package cn.zhihang.cm.account.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.zhihang.cm.account.entity.User;
import cn.zhihang.cm.account.repository.UserDao;
import cn.zhihang.cm.base.common.Coder;
import cn.zhihang.cm.base.common.Logs;
import cn.zhihang.cm.base.repository.BaseDao;
import cn.zhihang.cm.base.security.ShiroDbRealm;
import cn.zhihang.cm.base.service.BaseService;
import cn.zhihang.cm.base.service.ServiceException;

@Component
@Transactional(readOnly = true)
public class UserService extends BaseService<User, Integer> {
    private static Logger logger = Logs.get();
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private ShiroDbRealm shiroDbRealm;
    
    /**
     * 判断登录名是否存在
     * @param u_name
     * @return
     */
    public boolean existsUName(String u_name){
        User user = userDao.findByUserName(u_name);
        
        if(user != null){
            return true;
        }else{
            return false;
        }
    }
    
    /**
      * 根据登录名查询用户
      * @param u_name
      * @return
     */
    public User getUserByUName(String u_name){
        return userDao.findByUserNameAndIsdelete(u_name, 0);
    }
    
    /**
     * 保存用户
     * 
     * @param user
     * @return
     * @throws NoSuchAlgorithmException 
     * @throws IOException 
     * @throws InvalidKeyException 
     */
    @Transactional(readOnly = false)
    public User saveUser(User user) throws NoSuchAlgorithmException, InvalidKeyException, IOException{
        user.setIsdelete(0);
        
        if(user.getUserId() == null || user.getUserScode() == null){
            // 生成随机安全码并SHA1加密
            String key = Coder.initHacKey();
            user.setUserScode(key);
            user.setUserPass(Coder.encryptHMAC(user.getUserPass(), key));
        }
        
        if(user.getUserId() == null){ // 用户新注册
            if(existsUName(user.getUserName())){
                throw new ServiceException(String.format("用户名%s已经存在.", user.getUserName()));
            }
        }
        userDao.save(user);
        logger.info("保存用户{}成功.", user.getUserName());
        
        return null;
    }
    
    /**
     * 标记删除用户
     * @param id
     */
    @Transactional(readOnly = false)
    public void deleteUser(User user){
        if(null == user || user.getUserId() == 1){
            return;
        }
        user.setIsdelete(1);
        userDao.save(user);
        logger.info("标记删除用户{}成功.", user.getUserId());
        
        // 清空缓存
        if(shiroDbRealm.getAuthenticationCache() != null){
            shiroDbRealm.getAuthenticationCache().clear();
        }
    }
    
    /**
     * 父类需要的Dao
     */
    @Override
    public BaseDao<User, Integer> dao() {
        // TODO Auto-generated method stub
        return userDao;
    }
    
}
