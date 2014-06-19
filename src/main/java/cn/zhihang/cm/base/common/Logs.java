package cn.zhihang.cm.base.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
  * Logs 
  * @author cnlyml
  * @date 2014-6-18
 */
public class Logs {
    public static Logger get(){
        return LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }
}
