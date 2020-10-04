package zhewu.me.code.patterns.before.classwithfactory.descriptors;

public class BooleanDescriptor<M, F> extends AttributeDescriptor<M, F> {
    public BooleanDescriptor(String descriptorName, Class<M> mapperType, Class<F> forType) {
        super(descriptorName, mapperType, forType);
    }
}
