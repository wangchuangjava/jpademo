package cn.job.dao;

import cn.job.JpademoApplication;
import cn.job.pojo.C_user;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: dao层的单元测试
 * @Date: 2019-11-20 19:47
 * @Description: < 描述 >
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpademoApplication.class)
public class DaoTest {
    @Autowired
    private UserDao userDao;

    /**
     * 查询
     */
    @Test
    public void queryAll(){
        List<C_user> all = userDao.findAll();
        System.out.println(all+"测试查询所有的信息");
        C_user querybyid = userDao.querybyid(1);
        System.out.println(querybyid+"根据id查询出来的信息");
    }

    /**
     * 增加和更新
     */
    @Test
    public  void insert(){
        C_user c = new C_user();
        c.setId(2);
        c.setName("张三更改");
        c.setAddress("北京");
        C_user save = userDao.save(c);
        System.out.println(save);
    }

    /**
     * 删除，没有返回值
     */
    @Test
    public  void remove(){
        userDao.deleteById(2);
    }

    /**
     * 分页
     */
    @Test
    public  void page(){
        PageRequest pageReques=PageRequest.of(0,2, Sort.Direction.DESC, "id");
        Page<C_user> all = userDao.findAll(pageReques);
        System.out.println("获取总条数"+all.getTotalElements());
        System.out.println("获取总页数"+all.getTotalPages());
        System.out.println("获取当前页的集合"+all.getContent());

    }

    /**
     * 封装动态查询对象spec
     */
    @Test
    public void spec(){
        Specification<C_user> specification=new Specification<C_user>() {
            @Override
            public Predicate toPredicate(Root<C_user> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //获取实体的属性值
                Path<Object> name = root.get("name");
                //
                Predicate predicate = cb.equal(name, "wangchuang");
                Predicate and = cb.and(predicate);
                return and;
            }
        };
        List<C_user> all = userDao.findAll(specification);
        Optional<C_user> one = userDao.findOne(specification);
        System.out.println(all);
        System.out.println(one);
    }

    /**
     * 将需要动态查询的字段封装到集合当中
     */
    @Test
    public void spec2(){
        Specification<C_user> specification=new Specification<C_user>() {
            @Override
            public Predicate toPredicate(Root<C_user> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates=new ArrayList<>();
                predicates.add(cb.equal(root.get("name"),"wangchuang"));
                predicates.add(cb.like(root.get("address"),"%xu%"));
                Predicate[] predicates1=new Predicate[predicates.size()];
                //将集合转数组
                Predicate[] predicates2 = predicates.toArray(predicates1);
                //通过cb对象将数组放进去并返回
                Predicate and = cb.and(predicates2);
                return and;
            }
        };
        //将构造好的spe对象入参
        List<C_user> all = userDao.findAll(specification);
        System.out.println(all);

    }

}
