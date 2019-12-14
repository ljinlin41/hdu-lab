package cn.ljlin233.user.service;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/14 15:40
 */
public interface UserNicknameService {

    /**
     * 更新所有表的nickname
     *
     * @param userId userId
     * @param nickname nickname
     */
    void updateNickname(int userId, String nickname);
}
