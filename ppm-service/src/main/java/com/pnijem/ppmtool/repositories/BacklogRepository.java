package com.pnijem.ppmtool.repositories;

import com.pnijem.ppmtool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;

public interface BacklogRepository extends CrudRepository<Backlog, Long> {
    
}
