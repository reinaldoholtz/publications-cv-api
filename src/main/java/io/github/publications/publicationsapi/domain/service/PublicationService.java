package io.github.publications.publicationsapi.domain.service;

import io.github.publications.publicationsapi.application.publications.PublicationDTO;
import io.github.publications.publicationsapi.application.publications.PublicationPageDTO;
import io.github.publications.publicationsapi.domain.entity.Publication;
import java.util.List;
import java.util.Optional;

public interface PublicationService {

    Publication save(Publication publication);

    Optional<Publication> getById(String id);

    PublicationPageDTO getByDocument(String query,int pageNumber, int pageSize);

    List<Publication> getByDescription(String description);

    PublicationPageDTO getAllPublications(int pageNumber, int pageSize);

}
