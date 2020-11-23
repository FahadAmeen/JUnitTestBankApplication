package JUnitTests;

import com.FahadAmeen.Bank;
import com.FahadAmeen.BankAccount;
import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {

    private static final double AMOUNT = 5;

    private static final double INITIAL_BALANCE = 10;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Bank bank;

    // the collaborator of Bank that we manually instrument and inspect
    private List<BankAccount> bankAccounts;

    @BeforeEach
    public void setup() {
        bankAccounts = new ArrayList<>();
        bank = new Bank(bankAccounts);
    }

    @Test
    public void testOpenNewAccountShouldReturnAPositiveIdAndStoreTheAccount() {
        int newAccountId = bank.openNewBankAccount(0);
        assertTrue(newAccountId > 0, "Unexpected non positive id: " + newAccountId);
        assertEquals(newAccountId, bankAccounts.get(0).getId());
    }

//    @Test
//    public void testDepositWhenAccountIsNotFoundShouldThrow() {
//        thrown.expect(NoSuchElementException.class);
//        thrown.expectMessage("No account found with id: 1");
//        bank.deposit(1, INITIAL_BALANCE);
//    }

    @Test
    public void testDepositWhenAccountIsFoundShouldIncrementBalance() {
        // setup
        BankAccount testAccount = createTestAccount(INITIAL_BALANCE);
        bankAccounts.add(testAccount);
        // exercise
        bank.deposit(testAccount.getId(), AMOUNT);
        // verify
        assertEquals(INITIAL_BALANCE+AMOUNT, testAccount.getBalance(), 0);
    }

//    @Test
//    public void testWithdrawWhenAccountIsNotFoundShouldThrow() {
//        thrown.expect(NoSuchElementException.class);
//        thrown.expectMessage("No account found with id: 1");
//        bank.withdraw(1, AMOUNT);
//    }

    @Test
    public void testWithdrawWhenAccountIsFoundShouldDecrementBalance() {
        // setup
        BankAccount testAccount = createTestAccount(INITIAL_BALANCE);
        bankAccounts.add(testAccount);
        // exercise
        bank.withdraw(testAccount.getId(), AMOUNT);
        // verify
        assertEquals(INITIAL_BALANCE-AMOUNT, testAccount.getBalance(), 0);
    }

    /**
     * Utility method for creating a BankAccount for testing.
     */
    private @NotNull
    BankAccount createTestAccount(double initialBalance) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(initialBalance);
        return bankAccount;
    }
}
