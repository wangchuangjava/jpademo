package cn.job.service.impl;

import cn.job.dao.UserDao;
import cn.job.pojo.C_user;
import cn.job.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: 实现层
 * @Date: 2019-09-24 13:09
 * @Description: < 描述 >
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<C_user> queryAll() {
        return userDao.findAll();
    }

    @Override
    public C_user add(C_user c_user) {
        return userDao.save(c_user);
    }

    @Override
    public C_user quertByid(Integer id) {
        return userDao.querybyid(id);
    }

    @Override
    public List<C_user> queryByspec(Specification<C_user> specification) {
        return userDao.findAll(specification);
    }


}
