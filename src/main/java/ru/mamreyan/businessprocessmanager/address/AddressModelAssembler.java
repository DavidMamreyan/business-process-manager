package ru.mamreyan.businessprocessmanager.address;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class AddressModelAssembler implements RepresentationModelAssembler<Address, EntityModel<Address>> {
    @NonNull
    @Override
    public EntityModel<Address> toModel(
            @NonNull
                    Address address
    ) {
        return EntityModel.of(
                address,
                linkTo(methodOn(AddressController.class).one(address.getId())).withSelfRel(),
                linkTo(methodOn(AddressController.class).all()).withRel("addresses")
        );
    }
}