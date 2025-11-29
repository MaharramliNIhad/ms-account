package org.company.msaccount.repository;
import org.company.msaccount.dao.Users;
import org.company.msaccount.enums.ActiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByIdAndActive(Long id, ActiveStatus  status);
}
