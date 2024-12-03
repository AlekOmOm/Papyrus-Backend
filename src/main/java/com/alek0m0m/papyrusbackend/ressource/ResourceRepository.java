package com.alek0m0m.papyrusbackend.ressource;

import com.Alek0m0m.library.spring.web.mvc.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ResourceRepository extends BaseRepository<Resource> {
    @Modifying
    @Query(value = "ALTER TABLE Ressource AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
