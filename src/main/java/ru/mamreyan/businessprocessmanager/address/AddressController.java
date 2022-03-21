package ru.mamreyan.businessprocessmanager.address;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mamreyan.businessprocessmanager.ObjectNotValidException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AddressController {
    private final AddressRepository     addressRepository;
    private final AddressModelAssembler assembler;

    AddressController(
            AddressRepository addressRepository,
            AddressModelAssembler assembler
    ) {
        this.addressRepository = addressRepository;
        this.assembler         = assembler;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public AddressModelAssembler getAssembler() {
        return assembler;
    }

    @GetMapping ("/addresses")
    CollectionModel<EntityModel<Address>> all() {
        List<EntityModel<Address>> addresses = StreamSupport.stream(
                addressRepository.findAll().spliterator(),
                false
        ).map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(
                addresses,
                linkTo(methodOn(AddressController.class).all()).withSelfRel()
        );
    }

    @GetMapping ("/addresses/{id}")
    EntityModel<Address> one(
            @PathVariable
                    Long id
    ) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));

        return assembler.toModel(address);
    }

    @PostMapping ("/addresses")
    ResponseEntity<?> newAddress(
            @RequestBody
                    Address newAddress
    ) {
        if (newAddress.isNotValid()) {
            throw new ObjectNotValidException();
        }

        EntityModel<Address> entityModel = assembler.toModel(addressRepository.save(newAddress));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping ("/addresses/{id}")
    ResponseEntity<?> replaceAddress(
            @PathVariable
                    Long id,
            @RequestBody
                    Address newAddress
    ) {
        if (newAddress.isNotValid()) {
            throw new ObjectNotValidException();
        }

        Address updatedAddress = addressRepository.findById(id).map(address -> {
            address.setCountry(newAddress.getCountry());
            address.setRegion(newAddress.getRegion());
            address.setLocality(newAddress.getLocality());
            address.setStreet(newAddress.getStreet());
            address.setBuilding(newAddress.getBuilding());
            address.setApartment(newAddress.getApartment());
            return addressRepository.save(address);
        }).orElseGet(() -> {
            newAddress.setId(id);
            return addressRepository.save(newAddress);
        });

        EntityModel<Address> entityModel = assembler.toModel(updatedAddress);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping ("/addresses/{id}")
    ResponseEntity<?> deleteAddress(
            @PathVariable
                    Long id
    ) {
        return addressRepository.findById(id).map(address -> {
            addressRepository.delete(address);
            return ResponseEntity.ok().body("Address " + id + " was deleted");
        }).orElseThrow(() -> new AddressNotFoundException(id));
    }
}