package ru.mamreyan.businessprocessmanager.position;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class PositionModelAssembler implements RepresentationModelAssembler<Position, EntityModel<Position>> {
    @NonNull
    @Override
    public EntityModel<Position> toModel(
            @NonNull
                    Position position
    ) {
        return EntityModel.of(
                position,
                linkTo(methodOn(PositionController.class).one(position.getId())).withSelfRel(),
                linkTo(methodOn(PositionController.class).all()).withRel("positions")
        );
    }
}