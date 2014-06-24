package cn.zhihang.cm.account.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.zhihang.cm.account.entity.Role;
import cn.zhihang.cm.account.entity.User;
import cn.zhihang.cm.account.repository.RoleDao;
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
    
    @Autowired
    private RoleDao roleDao;
    
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
     * 判断邮箱是否存在
     */
    public boolean existsUserEmail(String userEmail){
    	User user = userDao.findByUserEmailAndIsdelete(userEmail, 0);
    	
    	if(user != null){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    /**
     * 检查User是否符合规定
     * @throws Exception 
     */
    public void checkUser(User user) throws Exception{
        /* 用户名检查 */
        if(user.getUserName() == null || user.getUserName().length() < 4 || user.getUserName().length() > 16){
            throw new Exception("用户名不符合规范");
        }
        Pattern pattern = Pattern.compile("^[a-zA-z]([a-zA-z0-9_]+)$");
        Matcher matcher = pattern.matcher(user.getUserName());
        
        if(!matcher.matches()){
            throw new Exception("用户名不符合规范");
        }
        
        /* 密码检查 */
        if(user.getUserPass() == null || user.getUserPass().length() != 40){
            throw new Exception("密码不符合规范");
        }
        
        /* 昵称检查 */
        if(user.getUserNick() == null || user.getUserNick().length() < 2 || user.getUserNick().length() > 10){
            throw new Exception("昵称不符合规范");
        }
        pattern = Pattern.compile("^([\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w_]+)$");
        matcher = pattern.matcher(user.getUserNick());
        
        if(!matcher.matches()){
            throw new Exception("昵称不符合规范");
        }
        
        /* 邮箱检查 */
        pattern = Pattern.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
        matcher = pattern.matcher(user.getUserEmail());
        
        if(user.getUserEmail() == null || !matcher.matches()){
            throw new Exception("邮箱不符合规范");
        }
        
        /* QQ号码检查 */
        if(user.getUserqq() != null){
            pattern = Pattern.compile("^[1-9]\\d{4,14}$");
            matcher = pattern.matcher(user.getUserqq());
            
            if(!matcher.matches()){
                throw new Exception("QQ号不符合规范");
            }
        }
        
        /* 手机号码检查 */
        if(user.getUserPhone() != null){
            pattern = Pattern.compile("^1\\d{10}$");
            matcher = pattern.matcher(user.getUserPhone());
            
            if(!matcher.matches()){
                throw new Exception("手机号码不符合规范");
            }
        }
    }
    
    /**
      * 根据登录名查询用户
      * @param u_name
      * @return
     */
    public User getUserByUserName(String u_name, boolean isdelete){
    	if(isdelete){
    		return userDao.findByUserNameAndIsdelete(u_name, 0);
    	}else{
    		return userDao.findByUserName(u_name);
    	}
        
    }
    
    /**
     * 根据用户邮箱查找用户
     */
    public User getUserByUserEmail(String userEmail, boolean isdelete){
    	if(isdelete){
    		return userDao.findByUserEmailAndIsdelete(userEmail, 0);
    	}else{
    		return userDao.findByUserEmail(userEmail);
    	}
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
            }else if(existsUserEmail(user.getUserEmail())){
            	throw new ServiceException(String.format("邮箱%s已经被注册",user.getUserEmail()));
            }
            
            user.setIslock(0);
            user.setIsverify(0);
            
            // 填充注册时间
            user.setUserRegDate(new Date());
            
            /* 插入用户角色 */
            List<Role>roleList = new ArrayList<Role>();
            roleList.add(roleDao.findOne(Role.USER_ROLE_ID));
            user.setRoleList(roleList);
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
