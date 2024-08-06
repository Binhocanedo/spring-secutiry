package com.devsuperior.demo.repositories;

import com.devsuperior.demo.entities.Product;
import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.projections.UserDetailsProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT tb_user.email AS username, tb_user.password, tb_role.id AS roleId, tb_role.authority " +
            "\t\t\tFROM tb_user " +
            "\t\t\tINNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id " +
            "\t\t\tINNER JOIN tb_role ON tb_role.id = tb_user_role.role_id " +
            "\t\t\tWHERE tb_user.email = :email ")
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
