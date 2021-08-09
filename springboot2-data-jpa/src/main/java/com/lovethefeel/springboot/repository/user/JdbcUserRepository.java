package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.dto.user.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepository {

    private final JdbcTemplate jdbcTemplate;

    private int batchSize = 100;

    public void saveAll(List<UserRequest> userList) {
        int batchCount = 0;
        List<UserRequest> user = new ArrayList<>();
        for (int i=0; i < userList.size(); i ++) {
            userList.add(userList.get(i));
            if ((i + 1) % batchSize == 0) {
                batchCount = batchInsert(batchSize,  batchCount, user);
            }
        }
    }

    private int batchInsert(int batchSize, int batchCount, List<UserRequest> userList) {
        final String sql = "UPDATE TN_USER SET COUNT = ? WHERE USER_ID = ? AND USER_NAME = ?";
        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, userList.get(i).getCount());
                        ps.setString(2, userList.get(i).getUserId());
                        ps.setString(3, userList.get(i).getUserName());
                    }

                    @Override
                    public int getBatchSize() {
                        return userList.size();
                    }
                });
        userList.clear();
        batchCount++;
        return batchCount;
    }
}
