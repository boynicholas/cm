package cn.zhihang.cm.base.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import cn.zhihang.cm.base.repository.BaseDao;

public abstract class BaseService<T, ID extends Serializable> {
    public abstract BaseDao<T, ID> dao();
    
    /**
      * 获取对象
      * @param id
      * @return
     */
    public T get(ID id){
        return dao().findOne(id);
    }
    
    /**
     * 是否存在此ID的对象
     * @param id
     * @return
     */
    public boolean exists(ID id){
        return dao().exists(id);
    }
    
    /**
      * 查询多个对象
      * @param ids
      * @return
     */
    public List<T> getAll(List<ID> ids){
        return (List<T>)dao().findAll(ids);
    }
    
    /**
     * 查询所有对象
     * @return
     */
    public List<T> getAll(){
        return (List<T>)dao().findAll();
    }
    
    /**
     * 所有对象数量
     * @return
     */
    public long count(){
        return dao().count();
    }
    
    /**
     * 保存一个对象
     * @param t
     * @return
     */
    @Transactional(readOnly = false)
    public T save(T t){
        return dao().save(t);
    }
    
    /**
     * 保存多个对象
     * @param entities
     * @return
     */
    @Transactional(readOnly = false)
    public List<T> save(List<T> entities){
        return (List<T>)dao().save(entities);
    }
    
    /**
     * 删除对象
     * @param t
     */
    @Transactional(readOnly = false)
    public void delete(T t){
        dao().delete(t);
    }
    
    /**
     * 根据ID删除对象
     * @param id
     */
    @Transactional(readOnly = false)
    public void delete(ID id){
        dao().delete(id);
    }
    
    /**
     * 删除多个对象
     * @param entities
     */
    @Transactional(readOnly = false)
    public void delete(List<T> entities){
        dao().delete(entities);
    }
    
    /**
     * 删除所有对象
     */
    @Transactional(readOnly = false)
    public void deleteAll(){
        dao().deleteAll();
    }
}
