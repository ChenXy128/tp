package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.ArrayList;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields

    private final EntryList entryList = new EntryList();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Entry name, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        entryList.add(name);
        this.tags.addAll(tags);
    }

    public void addEntry(Entry entry) {
        entryList.add(entry);
    }

    public Entry getEntry(String category) {
        return entryList.get(category);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getEntry("Name").equals(getEntry("Name"));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return getEntry("Name").equals(otherPerson.getEntry("Name"))
                && getEntry("Phone").equals(otherPerson.getEntry("Phone"))
                && getEntry("Email").equals(otherPerson.getEntry("Email"))
                && getEntry("Address").equals(otherPerson.getEntry("Address"))
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getEntry("Name"), getEntry("Phone"), getEntry("Email"), getEntry("Address"), tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", getEntry("Name"))
                .add("phone", getEntry("Phone"))
                .add("email", getEntry("Email"))
                .add("address", getEntry("Address"))
                .add("tags", tags)
                .toString();
    }

}
