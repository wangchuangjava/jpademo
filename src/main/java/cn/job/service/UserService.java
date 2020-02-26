package cn.job.service;

import cn.job.pojo.C_user;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 服务层
 * @Date: 2019-09-24 13:08
 * @Description: < 描述 >
 */
@Service
public interface UserService {
    List<C_user> queryAll();

    C_user add(C_user c_user);

    C_user quertByid(Integer id);

    List<C_user> queryByspec(Specification<C_user> specification);
}
