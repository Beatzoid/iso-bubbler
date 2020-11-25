package io;

public interface Persistable {

    String DELIMITER = ":";
    String SECTION_DELIMITER = System.lineSeparator() + "###" + System.lineSeparator();
    String LIST_DELIMITER = ", ";
    String COLUMN_DELIMITER = System.lineSeparator();

    /**
     * Serialize a class or object
     */
    String serialize();

    /**
     * Apply the serialized data
     * @param serializedData The serialized data to apply
     */
    void applySerializedData(String serializedData);
}
