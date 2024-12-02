package io.github.publications.publicationsapi.application.publications;

import io.github.publications.publicationsapi.domain.service.PublicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/publications")
@Slf4j
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationService service;
    private final PublicationMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> getPublication(@PathVariable String id){
        var possiblePublication = service.getById(id);
        if (possiblePublication.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var publication = possiblePublication.get();
        return  ResponseEntity.ok(mapper.publicationToDTO(publication));
    }

    @GetMapping
    public ResponseEntity<PublicationPageDTO>  search(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize){

            var publicationPageDTO = service.getByDocument(query,page,pageSize);
            return ResponseEntity.ok(publicationPageDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<PublicationPageDTO> getAllPublications(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize){

        var publicationPageDTO = service.getAllPublications(page,pageSize);

        return ResponseEntity.ok(publicationPageDTO);
    }
}
