package io.github.publications.publicationsapi.application.publications;

import io.github.publications.publicationsapi.domain.entity.Publication;
import io.github.publications.publicationsapi.domain.service.PublicationService;
import io.github.publications.publicationsapi.infra.repostiory.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository repository;

    private final PublicationMapper mapper;

    @Override
    @Transactional
    public Publication save(Publication publication) {
        return repository.save(publication);
    }

    @Override
    public Optional<Publication> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public PublicationPageDTO getByDocument(String query, int pageNumber, int pageSize) {
        Page<Publication> pagePublication = repository.findByDocumentTsQuery(query,PageRequest.of(pageNumber,pageSize));
        List<PublicationDTO> publications = pagePublication.get().map(mapper::publicationToDTO).collect(Collectors.toList());

        return new PublicationPageDTO(publications, pagePublication.getTotalElements(), pagePublication.getTotalPages());
    }

    @Override
    public List<Publication> getByDescription(String description) {
        return repository.findByDescriptionLike(description);
    }

    @Override
    public PublicationPageDTO getAllPublications(int pageNumber, int pageSize) {
        Page<Publication> pagePublication = repository.findAll(PageRequest.of(pageNumber,pageSize));
        List<PublicationDTO> publications = pagePublication.get().map(mapper::publicationToDTO).collect(Collectors.toList());

        return new PublicationPageDTO(publications, pagePublication.getTotalElements(), pagePublication.getTotalPages());
    }
}
