package cn.job.controller;

import cn.job.dao.UserDao;
import cn.job.pojo.C_user;
import cn.job.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author: 控制器
 * @Date: 2019-09-24 13:10
 * @Description: < 描述 >
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<C_user> queryAll() {
        return userService.queryAll();
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Integer id, String name, String address) {
        C_user c_user = new C_user();
        c_user.setId(id);
        c_user.setName(name);
        c_user.setAddress(address);
        //修改和添加用的都是save方法，根据id更改其他的字段
        c_user = userService.add(c_user);
        String msg;
        if (null != c_user) {
            msg = "添加成功！";
        } else {
            msg = "添加失败！";
        }
        return msg;
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del(Integer id) {
        userDao.deleteById(id);
        String msg = "删除成功！";
        return msg;
    }

    @RequestMapping("/selbyid")
    @ResponseBody
    public C_user sel(Integer id) {
        return userDao.querybyid(id);
    }

    @RequestMapping("/queryByspec")
    @ResponseBody
    public List<C_user> queryByspec(String username) {
        /**
         * root:查询对象的实体 get("属性名")获取实体中的属性
         * query：查询表达式的载体，很少使用
         * cb:用于构造条件查询
         */
        Specification<C_user> specification = new Specification<C_user>() {
            @Override
            public Predicate toPredicate(Root<C_user> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //Path<Object> name = root.get("name");
                Predicate aadas = cb.like(root.get("address"),"%"+username+"%");
                Predicate and = cb.and(aadas);
                return and;
            }
        };
        List<C_user> c_users = userService.queryByspec(specification);
        return c_users;
    }
}
