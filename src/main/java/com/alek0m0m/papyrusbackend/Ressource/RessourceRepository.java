package com.alek0m0m.papyrusbackend.Ressource;

import com.Alek0m0m.library.spring.web.mvc.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RessourceRepository extends BaseRepository<Ressource> {
    @Modifying
    @Query(value = "ALTER TABLE Ressource AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
