package ru.mamreyan.businessprocessmanager.bank;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class BankModelAssembler implements RepresentationModelAssembler<Bank, EntityModel<Bank>> {
    @NonNull
    @Override
    public EntityModel<Bank> toModel(
            @NonNull
                    Bank bank
    ) {
        return EntityModel.of(
                bank,
                linkTo(methodOn(BankController.class).one(bank.getId())).withSelfRel(),
                linkTo(methodOn(BankController.class).all()).withRel("banks")
        );
    }
}