package zhewu.me.code.patterns.before.classwithfactory.descriptors;

/**
 * TODO: How to apply generics?
 */

public class AttributeDescriptor<M, F> {
    public final String descriptorName;
    public final Class<M> mapperType;
    public final Class<F> forType;

    public AttributeDescriptor(String descriptorName, Class<M> mapperType, Class<F> forType) {
        this.descriptorName = descriptorName;
        this.mapperType = mapperType;
        this.forType = forType;
    }
}
