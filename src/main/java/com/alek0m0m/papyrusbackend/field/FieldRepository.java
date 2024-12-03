package com.alek0m0m.papyrusbackend.field;

import com.Alek0m0m.library.spring.web.mvc.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends BaseRepository<Field> {
    @Modifying
    @Query(value = "ALTER TABLE field AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
