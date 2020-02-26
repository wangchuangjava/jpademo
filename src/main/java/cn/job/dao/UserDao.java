package cn.job.dao;

import cn.job.pojo.C_user;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author: 仓库层
 * @Date: 2019-09-24 13:07
 * @Description: < 描述 >
 */
@Repository
public interface UserDao extends JpaRepository<C_user, Integer>, JpaSpecificationExecutor<C_user> {

    @Query("select u from C_user u where u.id=?1")
    C_user querybyid(Integer id);

    @Override
    List<C_user> findAll(Specification<C_user> spec);

    @Override
    Optional<C_user> findOne(Specification<C_user> spec);


}
