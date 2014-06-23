package cn.zhihang.cm.account.service;

import org.springframework.beans.factory.annotation.Autowired;

import cn.zhihang.cm.account.entity.Team;
import cn.zhihang.cm.account.repository.TeamDao;
import cn.zhihang.cm.base.repository.BaseDao;
import cn.zhihang.cm.base.service.BaseService;

public class TeamService extends BaseService<Team, Integer> {
    @Autowired
    private TeamDao teamDao;
    
    @Override
    public BaseDao<Team, Integer> dao() {
        // TODO Auto-generated method stub
        return teamDao;
    }

}
