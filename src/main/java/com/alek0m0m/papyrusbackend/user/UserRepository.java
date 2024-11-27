package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.spring.web.mvc.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {
    @Modifying
    @Query(value = "ALTER TABLE User AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}