package net.rockshore.prototype.customsecurity.core.net.rockshore.prototype.customsecurity.core.extendedproperties;

import org.springframework.util.StringUtils;

/**
 * Created by neildunlop on 02/02/15.
 * A simple data container for a definition of an extended property.
 */
public class ExtendedPropertyDefinition {

    protected String name;
    protected Class dataType;
    protected Object defaultValue;
    protected boolean nullAllowed = false;


    public String getName() {
        return name;
    }

    public Class getDataType() {
        return dataType;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public boolean isNullAllowed() {
        return nullAllowed;
    }

    public ExtendedPropertyDefinition(String name, Class dataType, Object defaultValue, boolean nullAllowed) {

        validateAndSetName(name);
        validateAndSetDataType(dataType);
        this.dataType = dataType;
        this.nullAllowed = nullAllowed;
        validateAndSetDefaultValue(this.dataType, this.nullAllowed, defaultValue);
    }

    private void validateAndSetName(String name) {

        if (StringUtils.isEmpty(name.trim())) {
            throw new IllegalArgumentException("Cannot create an extended property with a null or empty name.");
        }
        this.name = name;
    }

    private void validateAndSetDataType(Class dataType) {

        if (dataType == null) {
            throw new IllegalArgumentException("Cannot create an extended property with a null data type.");
        }
        this.dataType = dataType;
    }

    private void validateAndSetDefaultValue(Class dataType, boolean nullAllowed, Object defaultValue) {

        if (defaultValue == null && !nullAllowed) {
            throw new IllegalArgumentException("Cannot set value of extended property to null.  Null is not an allowed value.");
        }
        if (defaultValue != null && !dataType.isInstance(defaultValue)) {
            throw new IllegalArgumentException("Cannot set value of extended property.  The value '" + defaultValue + "'is not of the expected type '" + dataType.getName().toString() + "'.");
        }
        this.defaultValue = defaultValue;
    }


}
