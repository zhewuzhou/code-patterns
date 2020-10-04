package zhewu.me.code.patterns.before.classwithfactory.descriptors;

public class ReferenceDescriptor<M, F> extends AttributeDescriptor<M, F> {
    public ReferenceDescriptor(String descriptorName, Class<M> mapperType, Class<F> forType) {
        super(descriptorName, mapperType, forType);
    }
}
