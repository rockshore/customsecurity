package net.rockshore.prototype.customsecurity.core.testobjects;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by neildunlop on 31/01/15.
 * Simple domain object that is simply used for testing.  This is the domain object that is secured by ACL's.
 */
public class TestObject implements Serializable {

    private long id;
    private String name;
    private String path;
    private List<TestObject> childObjects;
    private TestObject parentObject;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public List<TestObject> getChildObjects() {
        return Collections.unmodifiableList(childObjects);
    }

    public TestObject getParentObject() {
        return parentObject;
    }

    public TestObject(long id, String name, String path, List<TestObject> childObjects, TestObject parentObject) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.childObjects = childObjects;
        this.parentObject = parentObject;
    }
}
