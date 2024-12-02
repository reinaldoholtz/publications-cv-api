package io.github.publications.publicationsapi.application.publications;

import java.util.List;

public record PublicationPageDTO(List<PublicationDTO> publications, long totalElements, int totalPages) {
}
