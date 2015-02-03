package net.rockshore.prototype.customsecurity.core.net.rockshore.prototype.customsecurity.core.extendedproperties;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by neildunlop on 03/02/15.
 * Tests to confirm that Extended Property Definitions work as expected.
 */
public class ExtendedPropertyDefinitionTests {

    @Test
    public void shouldThrowExceptionIfPropertyNameIsNull() {

        String propertyName = null;

        try {
            ExtendedPropertyDefinition definition = new ExtendedPropertyDefinition(propertyName, String.class, "default", true);
            fail("Expected exception was not thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot create an extended property with a null or empty name.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfPropertyNameIsEmpty() {

        String propertyName = "   ";

        try {
            ExtendedPropertyDefinition definition = new ExtendedPropertyDefinition(propertyName, String.class, "default", true);
            fail("Expected exception was not thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot create an extended property with a null or empty name.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenNullDataTypeIsSupplied() {

        Class dataType = null;

        try {
            ExtendedPropertyDefinition definition = new ExtendedPropertyDefinition("Test Property", dataType, "default", true);
            fail("Expected exception was not thrown.");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Cannot create an extended property with a null data type.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenNullDefaultValueIsSuppliedButNullAllowedIsFalse() {

        String defaultValue = null;

        try {
            ExtendedPropertyDefinition definition = new ExtendedPropertyDefinition("Test Property", String.class, defaultValue, false);
            fail("Expected exception was not thrown.");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Cannot set value of extended property to null.  Null is not an allowed value.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionDefaultValueTypeDoesNotMatchSuppliedDataType() {

        String defaultValue = "Hello";
        Class dataType = Integer.class;

        try {
            ExtendedPropertyDefinition definition = new ExtendedPropertyDefinition("Test Property", dataType, defaultValue, false);
            fail("Expected exception was not thrown.");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Cannot set value of extended property.  The value '" + defaultValue + "'is not of the expected type '" + dataType.getName().toString() + "'.", e.getMessage());
        }
    }
}
