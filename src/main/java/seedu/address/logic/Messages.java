package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.patient.Patient;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n%1$s";
    public static final String MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX = "The patient index "
            + "provided is invalid! \nPlease provide a valid patient index from the current "
            + "patient list.";
    public static final String MESSAGE_PATIENTS_LISTED_OVERVIEW = "%1$d patients listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date format or date! Date must be provided "
            + "in the following format: YYYY-MM-DD. Example: 2025-02-02";
    public static final String MESSAGE_FUTURE_LAST_VISIT_DATE = "Invalid date value! Last"
            + " visit date should not be in the future";
    public static final String MESSAGE_INVALID_NUMBER_OF_DATES = "Invalid! Only one date should be added as last "
            + "visit. Format: lastVisit d/YYYY-MM-DD";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code patient} for display to the user.
     */
    public static String format(Patient patient) {
        final StringBuilder builder = new StringBuilder();
        String lastVisitStr = patient.getLastVisit() == null ? "" : "" + patient.getLastVisit();
        builder.append(patient.getName())
                .append("; Phone: ")
                .append(patient.getPhone())
                .append("; Email: ")
                .append(patient.getEmail())
                .append("; Address: ")
                .append(patient.getAddress())
                .append("; Last Visit: ")
                .append(lastVisitStr)
                .append("; Tags: ");
        patient.getTags().forEach(builder::append);
        builder.append("; Medicines: ");
        patient.getMedicines().forEach(builder::append);
        return builder.toString();
    }

}
