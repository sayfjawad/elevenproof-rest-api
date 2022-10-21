package nl.multicode.elevenproof.controller;

import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.service.BankAccountElevenProofService;
import nl.multicode.elevenproof.service.BurgerServiceNummerElevenProofService;
import nl.multicode.elevenproof.service.UnknownElevenProofService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ElevenProofControllerTest {

    @Mock
    BankAccountElevenProofService bankAccountElevenProofService = new BankAccountElevenProofService();
    @Mock
    BurgerServiceNummerElevenProofService burgerServiceNummerElevenProofService = new BurgerServiceNummerElevenProofService();
    @Mock
    UnknownElevenProofService unknownElevenProofService = new UnknownElevenProofService();

    ElevenProofController controller;

    @BeforeEach
    public void before() {
        controller = new ElevenProofController(Map.of(
                ProofType.BANK_ACCOUNT, bankAccountElevenProofService,
                ProofType.BSN, burgerServiceNummerElevenProofService,
                ProofType.UNKNOWN, unknownElevenProofService
        ));
    }

    @Test
    void handleRequest_generate() {

        controller.handleRequest(ProofType.BSN, Command.GENERATE, null);
        verify(burgerServiceNummerElevenProofService).generate();
    }

    @Test
    void handleRequest_validate() {

        String number = "null";
        controller.handleRequest(ProofType.BSN, Command.VALIDATE, number);
        verify(burgerServiceNummerElevenProofService).isValid(number);
    }
}