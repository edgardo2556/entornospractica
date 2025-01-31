package SimpleBankingSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de proves per a Account
 * @author Flor Martinez
 */
public class AccountTest {

    @Test
    public void testDepositAmount() {
        // Crear un compte amb saldo inicial
        Account account = new Account("Flor Martinez", "1000-1234-56-123456789", 2500);

        // Prova d'un dipòsit vàlid
        try {
            account.depositAmount(500);
            assertEquals(3000, account.getBalance(), "El saldo hauria de ser 3000 després del dipòsit.");
        } catch (Exception e) {
            fail("No hauria d'haver llançat cap excepció per un dipòsit vàlid.");
        }

        // Prova d'un dipòsit negatiu
        Exception exception = assertThrows(Exception.class, () -> {
            account.depositAmount(-100);
        });
        assertEquals("No es pot ingressar una quantitat negativa.", exception.getMessage());
    }

    @Test
    public void testWithdrawAmount() {
        // Crear un compte amb saldo inicial
        Account account = new Account("Flor Martinez", "1000-1234-56-123456789", 2500);

        // Prova d'una retirada vàlida
        try {
            account.withdrawAmount(2000);
            assertEquals(500, account.getBalance(), "El saldo hauria de ser 500 després de la retirada.");
        } catch (Exception e) {
            fail("No hauria d'haver llançat cap excepció per una retirada vàlida.");
        }

        // Prova d'una retirada superior al saldo
        Exception exceptionInsufficientFunds = assertThrows(Exception.class, () -> {
            account.withdrawAmount(3000);
        });
        assertEquals("No hi ha suficient saldo", exceptionInsufficientFunds.getMessage());

        // Prova d'una retirada negativa
        Exception exceptionNegativeAmount = assertThrows(Exception.class, () -> {
            account.withdrawAmount(-100);
        });
        assertEquals("No es pot retirar una quantitat negativa.", exceptionNegativeAmount.getMessage());
    }
}
