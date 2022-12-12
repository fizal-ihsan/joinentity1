package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Release;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ReleaseRepositoryWithBagRelationships {
    Optional<Release> fetchBagRelationships(Optional<Release> release);

    List<Release> fetchBagRelationships(List<Release> releases);

    Page<Release> fetchBagRelationships(Page<Release> releases);
}
