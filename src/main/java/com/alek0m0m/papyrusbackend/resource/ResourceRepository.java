package com.alek0m0m.papyrusbackend.resource;

import com.Alek0m0m.library.spring.web.mvc.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends BaseRepository<Resource> {


    List<ResourceDTO> findByNameContainingIgnoreCase(String query);

}
