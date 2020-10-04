package zhewu.me.code.patterns.before.classwithfactory.descriptors;

public class DefaultDescriptor<M, F> extends AttributeDescriptor<M, F> {
    public DefaultDescriptor(String descriptorName, Class<M> mapperType, Class<F> forType) {
        super(descriptorName, mapperType, forType);
    }
}
