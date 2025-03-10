package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.LastVisitCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.LastVisit;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class LastVisitCommandParser implements Parser<LastVisitCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LastVisitCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_VISIT);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LastVisitCommand.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getValue(PREFIX_VISIT).orElse("");

        return new LastVisitCommand(index, new LastVisit(remark));
    }
}
