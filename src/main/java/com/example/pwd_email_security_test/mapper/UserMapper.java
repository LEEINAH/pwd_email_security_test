package com.example.pwd_email_security_test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT COUNT(*) FROM user WHERE email = #{email}")
    int checkEmailExists(@Param("email") String email);

    @Update("UPDATE user SET temp_password = #{tempPassword}, temp_expiry = NOW() + INTERVAL 10 MINUTE WHERE email = #{email}")
    void updateTempPassword(@Param("email") String email, @Param("tempPassword") String tempPassword);

    @Select("SELECT temp_password FROM user WHERE email = #{email} AND temp_expiry > NOW()")
    String getTempPassword(@Param("email") String email);

    @Update("UPDATE user SET password = #{newPassword}, temp_password = NULL, temp_expiry = NULL WHERE email = #{email}")
    void updatePassword(@Param("email") String email, @Param("newPassword") String newPassword);

}
