package net.rockshore.prototype.customsecurity.core.net.rockshore.prototype.customsecurity.core.extendedproperties;

import net.rockshore.prototype.customsecurity.core.net.rockshore.prototype.customsecurity.core.extendedproperties.CustomUserDetails;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by neildunlop on 02/02/15.
 */
public class CustomUserDetailsTests {

    @Test
    public void canCreateCustomUserDetailsWithDefinedProperties() {

        int defaultShoeSize = 14;

        List<ExtendedPropertyDefinition> propertyDefinitions = new ArrayList<ExtendedPropertyDefinition>();

        ExtendedPropertyDefinition definition1 = new ExtendedPropertyDefinition("Shoe Size", Integer.class, defaultShoeSize, false);
        ExtendedPropertyDefinition definition2 = new ExtendedPropertyDefinition("Hair Colour", String.class, null, true);

        propertyDefinitions.add(definition1);
        propertyDefinitions.add(definition2);

        ExtendedProperties extendedProperties = new ExtendedProperties(propertyDefinitions);

        CustomUserDetails userDetails = new CustomUserDetails(extendedProperties);

        assertNotNull(userDetails);
        assertNotNull(userDetails.getExtendedProperties());
        assertEquals(defaultShoeSize, userDetails.getExtendedProperties().get("Shoe Size"));
        assertNull(userDetails.getExtendedProperties().get("Hair Colour"));
    }



}
