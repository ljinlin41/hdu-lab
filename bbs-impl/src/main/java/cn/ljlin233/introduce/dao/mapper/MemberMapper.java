package cn.ljlin233.introduce.dao.mapper;

import org.springframework.stereotype.Repository;

import cn.ljlin233.introduce.entity.Member;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/11/5 13:46
 */
@Repository
public interface MemberMapper extends Mapper<Member> {
}
