package net.rockshore.prototype.customsecurity.core.net.rockshore.prototype.customsecurity.core.extendedproperties;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by neildunlop on 02/02/15.
 * A custom wrapper around a hashmap to control access to extended properties.
 */
public class ExtendedProperties {

    protected List<ExtendedPropertyDefinition> propertyDefinitions;
    protected Map<String, Object> properties = new HashMap<>();


    public ExtendedProperties(List<ExtendedPropertyDefinition> propertyDefinitions) {

        if (propertyDefinitions != null && propertyDefinitions.size() > 0) {

            this.propertyDefinitions = propertyDefinitions;

            for (ExtendedPropertyDefinition definition : propertyDefinitions) {

                validateAndSetInitialValue(definition, properties);
            }
        }
    }

    private void validateAndSetInitialValue(ExtendedPropertyDefinition definition, Map<String, Object> properties) {

        //TODO: Need to do some validation against the definition here!  - ideally store the definition against the property
        properties.put(definition.getName(), definition.getDefaultValue());
    }






    public Object get(String key) {

        if (properties.containsKey(key)) {
            return properties.get(key);
        } else {
            throw new IllegalArgumentException("Cannot get the value of unknown property '" + key + "'");
        }

    }

    public void set(String key, Object value) {

        if (properties.containsKey(key)) {
            properties.put(key, value);
        } else {
            throw new IllegalArgumentException("Cannot set the value of unknown property '" + key + "'");
        }
    }

    public int size() {
        return properties.size();
    }

    public boolean containsKey(String key) {
        return properties.containsKey(key);
    }
}
