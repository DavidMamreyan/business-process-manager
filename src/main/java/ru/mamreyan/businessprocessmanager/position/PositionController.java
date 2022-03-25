package ru.mamreyan.businessprocessmanager.position;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PositionController {
    private final PositionRepository     positionRepository;
    private final PositionModelAssembler assembler;

    PositionController(
            PositionRepository positionRepository,
            PositionModelAssembler assembler
    ) {
        this.positionRepository = positionRepository;
        this.assembler          = assembler;
    }

    public PositionRepository getPositionRepository() {
        return positionRepository;
    }

    public PositionModelAssembler getAssembler() {
        return assembler;
    }

    @GetMapping ("/positions")
    CollectionModel<EntityModel<Position>> all() {
        List<EntityModel<Position>> positions = StreamSupport.stream(
                positionRepository.findAll().spliterator(),
                false
        ).map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(
                positions,
                linkTo(methodOn(PositionController.class).all()).withSelfRel()
        );
    }

    @GetMapping ("/positions/{id}")
    EntityModel<Position> one(
            @PathVariable
                    Long id
    ) {
        Position position = positionRepository.findById(id).orElseThrow(() -> new PositionNotFoundException(id));

        return assembler.toModel(position);
    }

    @PostMapping ("/positions")
    ResponseEntity<?> newPosition(
            @RequestBody
                    Position newPosition
    ) {
        if (newPosition.isNotValid()) {
            throw new PositionNotValidException();
        }

        EntityModel<Position> entityModel = assembler.toModel(positionRepository.save(newPosition));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping ("/positions/{id}")
    ResponseEntity<?> replacePosition(
            @PathVariable
                    Long id,
            @RequestBody
                    Position newPosition
    ) {
        if (newPosition.isNotValid()) {
            throw new PositionNotValidException();
        }

        Position updatedPosition = positionRepository.findById(id).map(position -> {
            position.setName(newPosition.getName());
            return positionRepository.save(position);
        }).orElseGet(() -> {
            newPosition.setId(id);
            return positionRepository.save(newPosition);
        });

        EntityModel<Position> entityModel = assembler.toModel(updatedPosition);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping ("/positions/{id}")
    ResponseEntity<?> deletePosition(
            @PathVariable
                    Long id
    ) {
        return positionRepository.findById(id).map(position -> {
            positionRepository.delete(position);
            return ResponseEntity.ok().body("Position " + id + " was deleted");
        }).orElseThrow(() -> new PositionNotFoundException(id));
    }
}