package cn.job.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: 实体类
 * @Date: 2019-09-23 10:01
 * @Description: < 描述 >
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "c_user")
@Entity
public class C_user implements Serializable {
    @Id
    private Integer id;

    private String name;

    private String address;


}
