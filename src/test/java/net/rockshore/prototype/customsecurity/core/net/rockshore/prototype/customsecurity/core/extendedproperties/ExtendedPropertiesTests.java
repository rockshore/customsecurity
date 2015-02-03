package net.rockshore.prototype.customsecurity.core.net.rockshore.prototype.customsecurity.core.extendedproperties;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by neildunlop on 02/02/15.
 * Tests to confirm the ExtendedProperties collection behaves as expected.
 */
public class ExtendedPropertiesTests {

    @Test
    public void canCreateExtendedPropertiesWithDefaultValues() {

        String shoeSizeKey = "Shoe Size";
        String hairColourKey = "Hair Colour";
        int defaultShoeSize = 14;

        //Arrange
        List<ExtendedPropertyDefinition> propertyDefinitions = new ArrayList<ExtendedPropertyDefinition>();

        ExtendedPropertyDefinition definition1 = new ExtendedPropertyDefinition(shoeSizeKey, Integer.class, defaultShoeSize, false);
        ExtendedPropertyDefinition definition2 = new ExtendedPropertyDefinition(hairColourKey, String.class, null, true);

        propertyDefinitions.add(definition1);
        propertyDefinitions.add(definition2);

        //Act
        ExtendedProperties extendedProperties = new ExtendedProperties(propertyDefinitions);

        //Assert
        assertEquals(2, extendedProperties.size());
        assertTrue(extendedProperties.containsKey(shoeSizeKey));
        assertEquals(defaultShoeSize, extendedProperties.get(shoeSizeKey));
        assertTrue(extendedProperties.containsKey(hairColourKey));
        assertNull(extendedProperties.get(hairColourKey));
    }

    @Test
    public void canUpdateTheValueOfAnExtendedProperty() {

        String shoeSizeKey = "Shoe Size";
        String hairColourKey = "Hair Colour";
        int defaultShoeSize = 14;

        //Arrange
        List<ExtendedPropertyDefinition> propertyDefinitions = new ArrayList<ExtendedPropertyDefinition>();

        ExtendedPropertyDefinition definition1 = new ExtendedPropertyDefinition(shoeSizeKey, Integer.class, defaultShoeSize, false);
        ExtendedPropertyDefinition definition2 = new ExtendedPropertyDefinition(hairColourKey, String.class, null, true);

        propertyDefinitions.add(definition1);
        propertyDefinitions.add(definition2);

        ExtendedProperties extendedProperties = new ExtendedProperties(propertyDefinitions);

        assertEquals(2, extendedProperties.size());
        assertTrue(extendedProperties.containsKey(shoeSizeKey));
        assertEquals(defaultShoeSize, extendedProperties.get(shoeSizeKey));

        //Act
        int newShoeSize = 22;
        extendedProperties.set(shoeSizeKey, newShoeSize);

        //Assert
        assertEquals(2, extendedProperties.size());
        assertEquals(newShoeSize, extendedProperties.get(shoeSizeKey));
    }


}
