import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    // ================= UC11 TESTS =================

    @Test
    public void testRegex_ValidTrainID() {
        String input = "TRN-1234";
        assertTrue(Pattern.matches("TRN-\\d{4}", input));
    }

    @Test
    public void testRegex_InvalidTrainIDFormat() {
        assertFalse(Pattern.matches("TRN-\\d{4}", "TRAIN12"));
        assertFalse(Pattern.matches("TRN-\\d{4}", "TRN12A"));
        assertFalse(Pattern.matches("TRN-\\d{4}", "1234-TRN"));
    }

    @Test
    public void testRegex_ValidCargoCode() {
        String input = "PET-AB";
        assertTrue(Pattern.matches("PET-[A-Z]{2}", input));
    }

    @Test
    public void testRegex_InvalidCargoCodeFormat() {
        assertFalse(Pattern.matches("PET-[A-Z]{2}", "PET-ab"));
        assertFalse(Pattern.matches("PET-[A-Z]{2}", "PET123"));
        assertFalse(Pattern.matches("PET-[A-Z]{2}", "AB-PET"));
    }

    @Test
    public void testRegex_TrainIDDigitLengthValidation() {
        assertFalse(Pattern.matches("TRN-\\d{4}", "TRN-123"));
        assertFalse(Pattern.matches("TRN-\\d{4}", "TRN-12345"));
    }

    @Test
    public void testRegex_CargoCodeUppercaseValidation() {
        assertFalse(Pattern.matches("PET-[A-Z]{2}", "PET-Ab"));
        assertFalse(Pattern.matches("PET-[A-Z]{2}", "PET-aB"));
    }

    @Test
    public void testRegex_EmptyInputHandling() {
        assertFalse(Pattern.matches("TRN-\\d{4}", ""));
        assertFalse(Pattern.matches("PET-[A-Z]{2}", ""));
    }

    @Test
    public void testRegex_ExactPatternMatch() {
        assertFalse(Pattern.matches("TRN-\\d{4}", "TRN-1234XYZ"));
        assertFalse(Pattern.matches("PET-[A-Z]{2}", "PET-ABCD"));
    }
}