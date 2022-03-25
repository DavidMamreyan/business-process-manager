package ru.mamreyan.businessprocessmanager.bank;

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
public class BankController {
    private final BankRepository     bankRepository;
    private final BankModelAssembler assembler;

    BankController(
            BankRepository bankRepository,
            BankModelAssembler assembler
    ) {
        this.bankRepository = bankRepository;
        this.assembler      = assembler;
    }

    public BankRepository getBankRepository() {
        return bankRepository;
    }

    public BankModelAssembler getAssembler() {
        return assembler;
    }

    @GetMapping ("/banks")
    CollectionModel<EntityModel<Bank>> all() {
        List<EntityModel<Bank>> banks = StreamSupport.stream(
                bankRepository.findAll().spliterator(),
                false
        ).map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(
                banks,
                linkTo(methodOn(BankController.class).all()).withSelfRel()
        );
    }

    @GetMapping ("/banks/{id}")
    EntityModel<Bank> one(
            @PathVariable
                    Long id
    ) {
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new BankNotFoundException(id));

        return assembler.toModel(bank);
    }

    @PostMapping ("/banks")
    ResponseEntity<?> newBank(
            @RequestBody
                    Bank newBank
    ) {
        if (newBank.isNotValid()) {
            throw new BankNotValidException();
        }

        EntityModel<Bank> entityModel = assembler.toModel(bankRepository.save(newBank));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping ("/banks/{id}")
    ResponseEntity<?> replaceBank(
            @PathVariable
                    Long id,
            @RequestBody
                    Bank newBank
    ) {
        if (newBank.isNotValid()) {
            throw new BankNotValidException();
        }

        Bank updatedBank = bankRepository.findById(id).map(bank -> {
            bank.setName(newBank.getName());
            bank.setBik(newBank.getBik());
            bank.setAddress(newBank.getAddress());
            return bankRepository.save(bank);
        }).orElseGet(() -> {
            newBank.setId(id);
            return bankRepository.save(newBank);
        });

        EntityModel<Bank> entityModel = assembler.toModel(updatedBank);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping ("/banks/{id}")
    ResponseEntity<?> deleteBank(
            @PathVariable
                    Long id
    ) {
        return bankRepository.findById(id).map(bank -> {
            bankRepository.delete(bank);
            return ResponseEntity.ok().body("Bank " + id + " was deleted");
        }).orElseThrow(() -> new BankNotFoundException(id));
    }
}