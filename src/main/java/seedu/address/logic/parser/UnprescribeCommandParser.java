package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.UnprescribeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.medicine.Medicine;

/**
 * Parses input arguments and creates a new {@code UnprescribeCommand} object
 */
public class UnprescribeCommandParser implements Parser<UnprescribeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code UnprescribeCommand}
     * and returns a {@code UnprescribeCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnprescribeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEDICINE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnprescribeCommand.MESSAGE_USAGE), ive);
        }

        if (!argMultimap.getValue(PREFIX_MEDICINE).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnprescribeCommand.MESSAGE_USAGE));
        }

        Medicine medToRemove = ParserUtil.parseMed(argMultimap.getValue(PREFIX_MEDICINE).get());

        return new UnprescribeCommand(index, medToRemove);
    }
}
